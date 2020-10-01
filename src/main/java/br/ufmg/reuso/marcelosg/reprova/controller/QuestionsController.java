package br.ufmg.reuso.marcelosg.reprova.controller;

import br.ufmg.reuso.marcelosg.reprova.model.Question;
import br.ufmg.reuso.marcelosg.reprova.model.SemesterGrade;
import br.ufmg.reuso.marcelosg.reprova.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/questions")
public class QuestionsController {

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

    @DeleteMapping("/{id}")
    ResponseEntity deleteById(@PathVariable String id) {
        questionService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    ResponseEntity<Question> create(@RequestBody Question inputQuestion) {
        var question = questionService.createQuestion(inputQuestion);
        return ResponseEntity.status(HttpStatus.CREATED).body(question);
    }

    @PutMapping("/{id}")
    Question update(@RequestBody Question question, @PathVariable Integer id) {
        return question;
    }

    @PutMapping("/{id}/grades")
    Question addGrades(@RequestBody SemesterGrade inputGrades, @PathVariable String id) {
        return questionService.addGrades(id, inputGrades);
    }

}
