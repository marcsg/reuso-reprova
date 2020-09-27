package br.ufmg.reuso.marcelosg.reprova.service;

import br.ufmg.reuso.marcelosg.reprova.exception.QuestionNotFoundException;
import br.ufmg.reuso.marcelosg.reprova.model.Question;
import br.ufmg.reuso.marcelosg.reprova.repository.QuestionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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

        questionRepository.save(question);

        log.info("Question saved. statement=\"{}\" id={}", question.getStatement(), question.getId());
        return question;
    }
}
