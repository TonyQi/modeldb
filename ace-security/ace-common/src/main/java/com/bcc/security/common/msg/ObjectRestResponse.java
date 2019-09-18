package com.bcc.security.common.msg;

/**
 * Created by Ace on 2017/6/11.
 */
public class ObjectRestResponse<T> extends BaseResponse {

    T data;
    boolean rel;

    public boolean isRel() {
        return rel;
    }

    public void setRel(boolean rel) {
        this.rel = rel;
    }


    public ObjectRestResponse<T> rel(boolean rel) {
        this.setRel(rel);
        return this;
    }


    public ObjectRestResponse<T> data(T data) {
        this.setData(data);
        return this;
    }
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


}
