package br.ufmg.reuso.marcelosg.reprova.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class SemesterGrade {

    private Integer year;
    private Integer semester;
    private Double average;
    private Double median;
    private Double mean;
    private Double standardDeviation;
    private Double min;
    private Double max;
    private List<StudentGrade> grades;
}
