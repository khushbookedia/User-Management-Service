package com.neoSoft.User.Management.Exception.handler;

import com.neoSoft.User.Management.Exception.DuplicateEmailIdException;
import com.neoSoft.User.Management.Exception.ResourceUnAvailableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceUnAvailableException.class)
    public final ResponseEntity handleResourceNotFoundException(ResourceUnAvailableException ex){
        Map<String, Object> body= new LinkedHashMap<>();

        body.put("message", ex.getLocalizedMessage());
        body.put("statusCode", ex.getHttpStatus().value());

        return new ResponseEntity(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateEmailIdException.class)
    public final ResponseEntity handleDuplicateEmailIdException(DuplicateEmailIdException ex){
        Map<String, Object> body= new LinkedHashMap<>();

        body.put("message", ex.getLocalizedMessage());
        body.put("statusCode", ex.getHttpStatus().value());

        return new ResponseEntity(body, HttpStatus.BAD_REQUEST);
    }
}
