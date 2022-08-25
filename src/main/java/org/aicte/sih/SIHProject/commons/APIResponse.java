package org.aicte.sih.SIHProject.commons;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class APIResponse<T> {
    private int statusCode;
    private String message;
    private T data;

    public APIResponse() {
        this.statusCode = 200;
        this.message = "OK";
    }

    public APIResponse(T data) {
        this.statusCode = 200;
        this.message = "OK";
        this.data = data;
    }
}
