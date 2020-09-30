package br.ufmg.reuso.marcelosg.reprova.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
public class SemesterGrade {

    private Integer year;
    private Integer semester;
    private Stats stats;
    private List<StudentGrade> grades;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SemesterGrade that = (SemesterGrade) o;
        return Objects.equals(year, that.year) &&
                Objects.equals(semester, that.semester);
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, semester);
    }
}
