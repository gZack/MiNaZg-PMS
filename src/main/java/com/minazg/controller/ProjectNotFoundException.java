package com.minazg.controller;

public class ProjectNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 9060751397339719682L;
    private String message = "Requested Project Not Found";

    public ProjectNotFoundException() {}

    public ProjectNotFoundException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
