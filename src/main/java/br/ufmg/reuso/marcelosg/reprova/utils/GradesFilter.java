package br.ufmg.reuso.marcelosg.reprova.utils;

import br.ufmg.reuso.marcelosg.reprova.model.Exam;
import br.ufmg.reuso.marcelosg.reprova.model.Question;

public class GradesFilter {

    public static Exam filterExamGradesFromSameYearAndSemester(Exam exam) {
        exam.getQuestions().forEach(q -> GradesFilter.filterQuestionGrades(exam.getYear(), exam.getSemester(), q));
        return exam;
    }
    
    public static Question filterQuestionGrades(Integer year, Integer semester, Question question) {
        question.getSemesterGrades().removeIf(grade -> !grade.getYear().equals(year) || !grade.getSemester().equals(semester));
        return question;
    }
}
