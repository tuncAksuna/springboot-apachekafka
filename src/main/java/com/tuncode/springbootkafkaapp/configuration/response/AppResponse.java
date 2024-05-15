package com.tuncode.springbootkafkaapp.configuration.response;

import lombok.Getter;

/**
 * @author caksuna on 13.05.2024 23:35
 */
@Getter
public class AppResponse<T> {

    private Integer status;
    private String errorMessage = "With no error(s)";
    private T data;

    public AppResponse(String errorMessage, Integer status) {
        this.errorMessage = errorMessage;
        this.status = status;
    }

    public AppResponse(T data) {
        this.data = data;
        this.status = 200;
    }

    public static AppResponse nullResponse() {
        return new AppResponse(null);
    }

    public void setData(T data) {
        this.data = data;
    }
}
