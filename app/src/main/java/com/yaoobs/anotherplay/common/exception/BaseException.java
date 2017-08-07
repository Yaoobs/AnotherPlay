package com.yaoobs.anotherplay.common.exception;

/**
 * Created by yaoobs on 2017/8/7.
 */

public class BaseException extends Exception{

    private int code;

    private String displayMessage;

    public BaseException(int code, String displayMessage) {
        this.code = code;
        this.displayMessage = displayMessage;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDisplayMessage() {
        return displayMessage;
    }

    public void setDisplayMessage(String displayMessage) {
        this.displayMessage = displayMessage;
    }
}
