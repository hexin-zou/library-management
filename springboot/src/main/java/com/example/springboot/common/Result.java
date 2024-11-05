package com.example.springboot.common;

import lombok.Data;


@Data
public class Result {
    public static final String SUCCESS_CODE = "200";
    public static final String ERROR_CODE = "-1";

    private String code;
    private Object data;
    private String msg;

    public static Result success() {
        Result result = new Result();
        result.setCode(SUCCESS_CODE);
        return result;
    }

    public static Result success(Object data) {
        Result result = new Result();
        result.setCode(SUCCESS_CODE);
        result.setData(data);
        return result;
    }

    public static Result error(String msg) {
        Result result = new Result();
        result.setCode(ERROR_CODE);
        result.setMsg(msg);
        return result;
    }
}
