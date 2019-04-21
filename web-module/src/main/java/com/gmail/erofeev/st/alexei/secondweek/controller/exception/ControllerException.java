package com.gmail.erofeev.st.alexei.secondweek.controller.exception;

public class ControllerException extends RuntimeException {
    public ControllerException(String message, RuntimeException e) {
        super(message, e);
    }

    public ControllerException(String message) {
        super(message);
    }
}
