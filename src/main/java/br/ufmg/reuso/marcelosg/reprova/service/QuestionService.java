package br.ufmg.reuso.marcelosg.reprova.service;

import br.ufmg.reuso.marcelosg.reprova.exceptions.ItemNotFoundException;
import br.ufmg.reuso.marcelosg.reprova.exceptions.ValidationException;
import br.ufmg.reuso.marcelosg.reprova.model.Question;
import br.ufmg.reuso.marcelosg.reprova.model.SemesterGrade;
import br.ufmg.reuso.marcelosg.reprova.repository.QuestionRepository;
import br.ufmg.reuso.marcelosg.reprova.utils.StatsCalculator;
import br.ufmg.reuso.marcelosg.reprova.validators.GradesValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Slf4j
@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    public Question findById(String id) {
        return questionRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(id));
    }

    public Collection<Question> find() {
        return questionRepository.findAll(Sort.by("statement").ascending());
    }

    public Boolean delete(String id) {
        questionRepository.deleteById(id);
        return Boolean.TRUE;
    }

    public Question createQuestion(Question question) {

        if (question.getSemesterGrades() == null) {
            question.setSemesterGrades(new ArrayList<>());
        }
        questionRepository.save(question);

        log.info("Question saved. statement=\"{}\" id={}", question.getStatement(), question.getId());
        return question;
    }

    public Question addGrades(String questionId, SemesterGrade inputGrades) {

        var validationResult = GradesValidator.isValidSemesterGrade(inputGrades);
        if (validationResult.isPresent()) {
            throw new ValidationException(validationResult.get());
        }

        var question = questionRepository.findById(questionId).orElseThrow(() -> new ItemNotFoundException(questionId));

        var stats  = StatsCalculator.calculateGradesStatistics(inputGrades.getGrades());
        inputGrades.setStats(stats);

        if (question.getSemesterGrades() == null) {
            question.setSemesterGrades(new ArrayList<>());
        }

        var existingGradeIndex = question.getSemesterGrades().indexOf(inputGrades);

        if (existingGradeIndex >= 0) {
            question.getSemesterGrades().set(existingGradeIndex, inputGrades);
            log.info("Updated existing semester grade year={} semester={} on question={}", inputGrades.getYear(), inputGrades.getSemester(), questionId);

        } else {
            question.getSemesterGrades().add(inputGrades);
            log.info("Semester Grade for year={} semester={} added to question={}", inputGrades.getYear(), inputGrades.getSemester(), questionId);
        }

        questionRepository.save(question);
        return question;
    }
}
