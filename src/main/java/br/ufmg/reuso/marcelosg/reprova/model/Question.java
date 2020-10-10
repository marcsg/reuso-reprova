package br.ufmg.reuso.marcelosg.reprova.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("question")
public class Question {

    @Id
    private String id;

    /**
     * The description of the question. Mustn't be null nor empty.
     */
    private String description;
    /**
     * The statement of the question. May be null or empty.
     */
    private String statement;

    private String discipline;
    private Stats stats;

    private List<String> themes;

    private Set<String> tags;

    /**
     * Whether the question is private.
     */
    private Boolean pvt;

    private Integer estimateTimeInMinutes;

    private QuestionDifficulty difficulty;

    // Option path for a file corresponding to the question
    private String filePath;

    List<SemesterGrade> semesterGrades;
}
