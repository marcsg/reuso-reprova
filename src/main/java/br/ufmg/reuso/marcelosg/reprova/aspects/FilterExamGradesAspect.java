package br.ufmg.reuso.marcelosg.reprova.aspects;

import br.ufmg.reuso.marcelosg.reprova.model.Exam;
import br.ufmg.reuso.marcelosg.reprova.utils.GradesFilter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;

@Slf4j
@Aspect
@Configuration
public class FilterExamGradesAspect {

    @AfterReturning(value = "br.ufmg.reuso.marcelosg.reprova.aspects.ExamServicePointcutConfig.returningSingleExam()", returning = "exam")
    public Exam filterExamGrades(JoinPoint joinPoint, Exam exam) {
        GradesFilter.filterExamGradesFromSameYearAndSemester(exam);
        log.debug("Exam grades filtered after method ExamService.{}", joinPoint.getSignature().getName());
        return exam;
    }

    @AfterReturning(value = "br.ufmg.reuso.marcelosg.reprova.aspects.ExamServicePointcutConfig.returningMultipleExams()", returning = "exams")
    public Collection<Exam> filterExamGrades(JoinPoint joinPoint, Collection<Exam> exams) {
        exams.forEach(GradesFilter::filterExamGradesFromSameYearAndSemester);
        log.debug("Exam grades filtered after method ExamService.{}", joinPoint.getSignature().getName());
        return exams;
    }
}
