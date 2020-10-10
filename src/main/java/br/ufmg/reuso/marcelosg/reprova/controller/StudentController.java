package br.ufmg.reuso.marcelosg.reprova.controller;

import br.ufmg.reuso.marcelosg.reprova.model.Question;
import br.ufmg.reuso.marcelosg.reprova.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@Profile("student")
@RequestMapping("/students/questions")
public class StudentController {

    @Autowired
    QuestionService questionService;

    @GetMapping("/{id}")
    Question findById(@PathVariable String id) {
        return questionService.findById(id);
    }

    @GetMapping
    Collection<Question> find() {
        return questionService.find();
    }
}
