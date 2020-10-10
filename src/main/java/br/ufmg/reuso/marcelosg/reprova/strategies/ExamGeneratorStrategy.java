package br.ufmg.reuso.marcelosg.reprova.strategies;

import br.ufmg.reuso.marcelosg.reprova.model.Question;

import java.util.List;

public interface ExamGeneratorStrategy {

    List<Question> generateExamQuestions(int totalQuestions);
}
