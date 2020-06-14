package com.example.alumno.cineya.api.response;

import com.google.gson.annotations.SerializedName;

public class BaseResponse<T> {

    boolean success;
    String message;
    T body;

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public T getBody() {
        return body;
    }
}
