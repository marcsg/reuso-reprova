package br.ufmg.reuso.marcelosg.reprova.exception;

public class QuestionNotFoundException extends RuntimeException {

    public QuestionNotFoundException(Integer id) {
        super("Could not find question " + id);
    }
}
