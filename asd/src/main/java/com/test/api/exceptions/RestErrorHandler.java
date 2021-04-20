package com.test.api.exceptions;

import com.test.api.response.GeneralResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestErrorHandler {

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<GeneralResponse<?>> onRunTimeException(RuntimeException ex) {
        return ResponseEntity.unprocessableEntity().body(new GeneralResponse<>("Error", ex.getLocalizedMessage()));
    }


    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<GeneralResponse<?>> onRunTimeException(MethodArgumentNotValidException ex) {
        return ResponseEntity.badRequest().body(new GeneralResponse<>("Error", ex.getFieldError().getDefaultMessage()));
    }




}
