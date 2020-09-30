package br.ufmg.reuso.marcelosg.reprova.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Stats {
    private Double average;
    private Double median;
    private Double mean;
    private Double standardDeviation;
    private Double min;
    private Double max;
}
