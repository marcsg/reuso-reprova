package br.ufmg.reuso.marcelosg.reprova.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("exam")
public class Exam {

    @Id
    private String id;

    private String title;
    private String discipline;
    private Integer year;
    private Integer semester;

    private Stats stats;
    private List<StudentGrade> studentGrades;

    private LocalDateTime applicationDate;

    @DBRef
    private List<Question> questions;
}
