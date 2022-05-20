package br.ufmg.reuso.marcelosg.reprova.model;

import br.ufmg.reuso.marcelosg.reprova.strategies.StrategyType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamGeneratorCriteria {
    private Integer totalQuestions;
    private StrategyType strategyType;
    private Integer year;
    private Integer semester;
    private String title;
    private boolean saveExam;
}
