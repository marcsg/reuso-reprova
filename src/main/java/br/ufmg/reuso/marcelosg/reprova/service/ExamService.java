package br.ufmg.reuso.marcelosg.reprova.service;

import br.ufmg.reuso.marcelosg.reprova.exception.ItemNotFoundException;
import br.ufmg.reuso.marcelosg.reprova.model.Exam;
import br.ufmg.reuso.marcelosg.reprova.repository.ExamRepository;
import br.ufmg.reuso.marcelosg.reprova.utils.GradesFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j
@Service
public class ExamService {

    @Autowired
    private ExamRepository examRepository;

    public Exam createExam(Exam exam) {
        return examRepository.save(exam);
    }

    public Exam findById(String id, boolean filterGradesBySemester) {
        var exam = examRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(id));

        if (filterGradesBySemester) {
            GradesFilter.filterExamGradesFromSameYearAndSemester(exam);
        }
        return exam;
    }

    public Collection<Exam> find(boolean filterGradesBySemester) {
        var exams = examRepository.findAll(Sort.by("title").ascending());

        if (filterGradesBySemester) {
            exams.forEach(GradesFilter::filterExamGradesFromSameYearAndSemester);
        }
        return exams;
    }

    public Boolean deleteById(String id) {
        examRepository.deleteById(id);
        return Boolean.TRUE;
    }
}
