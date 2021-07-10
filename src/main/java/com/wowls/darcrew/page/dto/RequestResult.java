package com.wowls.darcrew.page.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequestResult {
    public static final RequestResult SUCCESS = new RequestResult();

    private Status status;
    private String message;
    private Object result;

    public enum Status {
        SUCCESS,
        FAIL
    }

    public RequestResult() {
        this.status = Status.SUCCESS;
        this.message = "Success";
    }

    public RequestResult(Object data) {
        this.status = Status.SUCCESS;
        this.message = "Success";
        this.result = data;
    }

    public RequestResult(String errorMessage){
        this.status = Status.FAIL;
        this.message = errorMessage;
    }

    public RequestResult(String errorMessage, Object data) {
        this.status = Status.FAIL;
        this.message = errorMessage;
        this.result = data;
    }
}
