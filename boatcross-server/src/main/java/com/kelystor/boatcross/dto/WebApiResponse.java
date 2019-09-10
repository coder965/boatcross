package com.kelystor.boatcross.dto;

public class WebApiResponse<T> {
    private static final int SUCCESS_CODE = 0;

    private int code;
    private String message;
    private T data;

    public static <T> WebApiResponse<T> success(T data) {
        WebApiResponse<T> webApiResponse = new WebApiResponse<>();
        webApiResponse.setCode(SUCCESS_CODE);
        webApiResponse.setData(data);
        return webApiResponse;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "WebApiResponse{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
