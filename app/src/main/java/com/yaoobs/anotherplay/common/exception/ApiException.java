package com.yaoobs.anotherplay.common.exception;

/**
 * Created by yaoobs on 2017/8/7.
 */

public class ApiException extends BaseException{
    public ApiException(int code, String displayMessage) {
        super(code, displayMessage);
    }
}
