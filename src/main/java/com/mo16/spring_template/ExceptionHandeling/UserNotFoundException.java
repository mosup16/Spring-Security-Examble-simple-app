package com.mo16.spring_template.ExceptionHandeling;

public class UserNotFoundException extends NotFoundException {

    public UserNotFoundException(){
        super();
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
