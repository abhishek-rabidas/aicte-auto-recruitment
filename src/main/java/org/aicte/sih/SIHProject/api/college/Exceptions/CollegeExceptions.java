package org.aicte.sih.SIHProject.api.college.Exceptions;

public class CollegeExceptions extends RuntimeException {
    String message;
    public CollegeExceptions(String message) {
        super(message);
    }
}