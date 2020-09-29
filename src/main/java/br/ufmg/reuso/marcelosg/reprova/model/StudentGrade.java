package br.ufmg.reuso.marcelosg.reprova.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentGrade {

    private String student;
    private Double grade;
}
