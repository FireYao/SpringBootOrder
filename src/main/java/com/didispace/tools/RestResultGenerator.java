package com.didispace.tools;

public class RestResultGenerator {

    public static <T> ResponseResult<T> genResult(T data, String message) {
        ResponseResult<T> result = new ResponseResult<>();
        result.setData(data);
        result.setSuccess(true);
        result.setMessage(message);
        return result;
    }

    public static <T> ResponseResult<T> genResult(T data, String message, boolean success) {
        ResponseResult<T> result = new ResponseResult<>();
        result.setData(data);
        result.setSuccess(success);
        result.setMessage(message);
        return result;
    }
}