package br.ufmg.reuso.marcelosg.reprova.service;

import br.ufmg.reuso.marcelosg.reprova.exception.QuestionNotFoundException;
import br.ufmg.reuso.marcelosg.reprova.exception.ValidationException;
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
        return questionRepository.findById(id).orElseThrow(() -> new QuestionNotFoundException(id));
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

    public Question addGrades(String questionid, SemesterGrade inputGrades) {

        var validationResult = GradesValidator.isValidSemesterGrade(inputGrades);
        if (validationResult.isPresent()) {
            throw new ValidationException(validationResult.get());
        }

        var question = questionRepository.findById(questionid).orElseThrow(() -> new QuestionNotFoundException(questionid));

        StatsCalculator.calculateGradesStatistics(inputGrades);

        if (question.getSemesterGrades() == null) {
            question.setSemesterGrades(new ArrayList<>());
        }

        var existingGrade = question.getSemesterGrades()
                .stream()
                .filter(s -> s.getSemester().equals(inputGrades.getSemester()) && s.getYear().equals(inputGrades.getYear()))
                .findFirst();

        if (existingGrade.isPresent()) {
            // TODO Mix and match

        } else {
            StatsCalculator.calculateGradesStatistics(inputGrades);
            question.getSemesterGrades().add(inputGrades);
            questionRepository.save(question);
        }

        return question;
    }
}
