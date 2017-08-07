package com.yaoobs.anotherplay.bean;

import java.io.Serializable;

/**
 * Created by yaoobs on 2017/8/7.
 */

public class BaseBean<T> implements Serializable{

    public static final int SUCCESS=1;
    private int status;

    private String message;

    private T data;

    public boolean success(){

        return  (status==SUCCESS);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
}
