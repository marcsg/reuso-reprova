package br.ufmg.reuso.marcelosg.reprova.exceptions;

import lombok.Data;

import java.util.List;

@Data
public class ValidationException extends RuntimeException {

    private ErrorResponse error;

    public ValidationException(List<String> error) {
        this.error = new ErrorResponse(error);
    }
}
