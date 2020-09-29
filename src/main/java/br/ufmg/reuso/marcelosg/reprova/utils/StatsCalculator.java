package br.ufmg.reuso.marcelosg.reprova.utils;

import br.ufmg.reuso.marcelosg.reprova.model.SemesterGrade;
import br.ufmg.reuso.marcelosg.reprova.model.StudentGrade;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Slf4j
public class StatsCalculator {

    public static void calculateGradesStatistics(SemesterGrade semesterGrade) {

        var grades = semesterGrade.getGrades().stream().mapToDouble(StudentGrade::getGrade).toArray();
        var stats = new DescriptiveStatistics(grades);

        semesterGrade.setMedian(stats.getPercentile(50));
        semesterGrade.setMean(stats.getMean());
        semesterGrade.setStandardDeviation(new BigDecimal(stats.getStandardDeviation()).setScale(2, RoundingMode.HALF_UP).doubleValue());
        semesterGrade.setMin(stats.getMin());
        semesterGrade.setMax(stats.getMax());
    }
}
