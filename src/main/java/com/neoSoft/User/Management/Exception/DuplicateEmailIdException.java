package com.neoSoft.User.Management.Exception;

import org.springframework.http.HttpStatus;

public class DuplicateEmailIdException extends RuntimeException{

    private HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    public HttpStatus getHttpStatus(){
        return httpStatus;
    }

    public DuplicateEmailIdException(HttpStatus httpStatus, String message){
        super(message);
        this.httpStatus = httpStatus;

    }


}
