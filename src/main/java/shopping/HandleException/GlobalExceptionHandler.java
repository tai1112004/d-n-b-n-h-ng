package shopping.HandleException;


import java.util.Collections;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import shopping.exception.DuplicateUserException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateUserException.class)
    public ResponseEntity<?> handleDuplicateUser(DuplicateUserException ex) {
    	return ResponseEntity.status(HttpStatus.BAD_REQUEST)
    	        .body(Collections.singletonMap("error", ex.getMessage()));
    }
}
