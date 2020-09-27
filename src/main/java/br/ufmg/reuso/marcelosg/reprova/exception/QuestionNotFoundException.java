package br.ufmg.reuso.marcelosg.reprova.exception;

public class QuestionNotFoundException extends RuntimeException {

    public QuestionNotFoundException(String id) {
        super("Could not find question " + id);
    }
}
