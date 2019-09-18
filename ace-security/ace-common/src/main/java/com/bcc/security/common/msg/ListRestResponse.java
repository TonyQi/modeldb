package com.bcc.security.common.msg;

import lombok.Data;

/**
 * ${DESCRIPTION}
 *
 * @author wanghaobin
 * @create 2017-06-09 7:32
 */
@Data
public class ListRestResponse<T> {
    String msg;
    T result;
    int count;

    public ListRestResponse<T> count(int count) {
        this.setCount(count);
        return this;
    }

    public ListRestResponse<T> count(Long count) {
        this.setCount(count.intValue());
        return this;
    }

    public ListRestResponse<T> msg(String msg) {
        this.setMsg(msg);
        return this;
    }

    public ListRestResponse<T> result(T result) {
        this.setResult(result);
        return this;
    }

}
