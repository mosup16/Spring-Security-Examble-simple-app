package com.mo16.spring_template.ExceptionHandeling;

public class PostNotFoundException extends NotFoundException {

    public PostNotFoundException() {
        super();
    }

    public PostNotFoundException(String message) {
        super(message);
    }
}
