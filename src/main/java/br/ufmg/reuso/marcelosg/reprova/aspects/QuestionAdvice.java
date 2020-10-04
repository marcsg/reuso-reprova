package br.ufmg.reuso.marcelosg.reprova.aspects;

import br.ufmg.reuso.marcelosg.reprova.exceptions.ItemNotFoundException;
import br.ufmg.reuso.marcelosg.reprova.exceptions.ValidationException;
import com.mongodb.MongoSocketException;
import com.mongodb.MongoSocketOpenException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class QuestionAdvice {

    @ResponseBody
    @ExceptionHandler(ItemNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String handleQuestionNotFound(ItemNotFoundException exception) {
        return exception.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(MongoSocketOpenException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    String handleMongoException(MongoSocketException mongoException) {
        log.error("Mongodb operation failed. message={}", mongoException.getMessage(), mongoException);
        return "Internal error to access database. " + mongoException.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ValidationException handleValidationException(ValidationException validationException) {
        return validationException;
    }
}
