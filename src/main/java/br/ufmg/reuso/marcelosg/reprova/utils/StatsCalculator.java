package br.ufmg.reuso.marcelosg.reprova.utils;

import br.ufmg.reuso.marcelosg.reprova.model.SemesterGrade;
import br.ufmg.reuso.marcelosg.reprova.model.Stats;
import br.ufmg.reuso.marcelosg.reprova.model.StudentGrade;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Slf4j
public class StatsCalculator {

    public static void calculateGradesStatistics(SemesterGrade semesterGrade) {

        var grades = semesterGrade.getGrades().stream().mapToDouble(StudentGrade::getGrade).toArray();
        var descriptiveStatistics = new DescriptiveStatistics(grades);

        var semesterStats = Stats.builder()
                .median(descriptiveStatistics.getPercentile(50))
                .mean(descriptiveStatistics.getMean())
                .standardDeviation(new BigDecimal(descriptiveStatistics.getStandardDeviation()).setScale(2, RoundingMode.HALF_UP).doubleValue())
                .min(descriptiveStatistics.getMin())
                .max(descriptiveStatistics.getMax())
                .build();

        semesterGrade.setStats(semesterStats);
    }
}
