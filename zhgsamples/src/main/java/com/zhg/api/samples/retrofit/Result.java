package com.zhg.api.samples.retrofit;

/**
 * Created by nyzhang on 2015/12/21.
 */
public class Result {

    private int code;
    private String message;

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

    public Result(int code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public Result() {
        super();
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
