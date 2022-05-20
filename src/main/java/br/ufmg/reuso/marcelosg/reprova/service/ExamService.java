package br.ufmg.reuso.marcelosg.reprova.service;

import br.ufmg.reuso.marcelosg.reprova.exceptions.ItemNotFoundException;
import br.ufmg.reuso.marcelosg.reprova.exceptions.ValidationException;
import br.ufmg.reuso.marcelosg.reprova.factories.ExamGeneratorStrategyFactory;
import br.ufmg.reuso.marcelosg.reprova.model.Exam;
import br.ufmg.reuso.marcelosg.reprova.model.ExamGeneratorCriteria;
import br.ufmg.reuso.marcelosg.reprova.repository.ExamRepository;
import br.ufmg.reuso.marcelosg.reprova.utils.StatsCalculator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

@Slf4j
@Service
public class ExamService {

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private ExamGeneratorStrategyFactory strategyFactory;

    public Exam generateExam(ExamGeneratorCriteria criteria) {

        if (Objects.isNull(criteria) || Objects.isNull(criteria.getStrategyType())) {
            throw new ValidationException(Collections.singletonList("An exam generator strategyType must be defined."));
        }

        if (Objects.isNull(criteria.getTotalQuestions())) {
            throw new ValidationException(Collections.singletonList("totalQuestions number must be provided."));
        }

        var strategy = strategyFactory.createStrategy(criteria.getStrategyType());
        var questions = strategy.generateExamQuestions(criteria.getTotalQuestions());

        var generatedExam = Exam.builder()
                .questions(questions)
                .title(criteria.getTitle())
                .year(criteria.getYear())
                .semester(criteria.getSemester())
                .build();

        if (criteria.isSaveExam()) {
            this.createExam(generatedExam);
        }
        return generatedExam;
    }

    public Exam calculateExamGrades(String id) {
        var exam = this.findById(id);

        var studentGrades = StatsCalculator.calculateExamStudentGrades(exam.getQuestions());
        exam.setStudentGrades(studentGrades);

        var examStats = StatsCalculator.calculateGradesStatistics(studentGrades);
        exam.setStats(examStats);

        return examRepository.save(exam);
    }

    public Exam createExam(Exam exam) {
        return examRepository.save(exam);
    }

    public Exam findById(String id) {
        return examRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(id));
    }

    public Collection<Exam> find() {
        return examRepository.findAll(Sort.by("title").ascending());
    }

    public Boolean deleteById(String id) {
        examRepository.deleteById(id);
        return Boolean.TRUE;
    }
}
