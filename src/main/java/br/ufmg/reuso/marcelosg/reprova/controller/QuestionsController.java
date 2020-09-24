package br.ufmg.reuso.marcelosg.reprova.controller;

import br.ufmg.reuso.marcelosg.reprova.exception.QuestionNotFoundException;
import br.ufmg.reuso.marcelosg.reprova.model.Question;
import br.ufmg.reuso.marcelosg.reprova.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/questions")
public class QuestionsController {

    @Autowired
    QuestionRepository questionRepository;

    @GetMapping("/{id}")
    Question findById(@PathVariable Integer id) {
        var question = questionRepository.findById(id);
        if (question.isPresent()) {
            return question.get();
        } else {
            throw new QuestionNotFoundException(id);
        }
    }

    @DeleteMapping("/{id}")
    void deleteById(@PathVariable Integer id) {
    }

    @PostMapping
    Question create(@RequestBody Question question) {
        return question;
    }

    @PutMapping("/{id}")
    Question update(@RequestBody Question question, @PathVariable Integer id) {
        return question;
    }




}
