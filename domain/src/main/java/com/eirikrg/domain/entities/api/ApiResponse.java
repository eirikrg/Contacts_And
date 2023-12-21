package com.eirikrg.domain.entities.api;

public abstract class ApiResponse<T> {
    public T data;
    public String message;
    public Integer code;

    public ApiResponse(T data, String message, Integer code) {
        this.data = data;
        this.message = message;
        this.code = code;
    }

    public static class Success<T> extends ApiResponse<T> {
        public Success(T data) {
            super(data, null, null);
        }
    }

    public static class Error<T> extends ApiResponse<T> {
        public Error(Integer errorCode, String errorMessage) {
            super(null, errorMessage, errorCode);
        }
    }

    public static class Loading<T> extends ApiResponse<T> {
        public Loading() {
            super(null, null, null);
        }
    }
}
