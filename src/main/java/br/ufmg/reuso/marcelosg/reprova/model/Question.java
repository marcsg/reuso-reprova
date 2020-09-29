package br.ufmg.reuso.marcelosg.reprova.model;

import br.ufmg.reuso.marcelosg.reprova.enums.QuestionDifficulty;
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

    private List<String> themes;

    private Set<String> tags;
    /**
     * The description of the question. Mustn't be null nor empty.
     */
    private String description;
    /**
     * The statement of the question. May be null or empty.
     */
    private String statement;
    /**
     * The record of the question per semester per class. Mustn't be null, may be empty.
     */
    // TODO No records in this initial implementation
//    private Map<Semester, Map<String, Float>> record;
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
