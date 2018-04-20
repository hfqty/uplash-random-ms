package me.ning.picapiget.image.dto;

import java.io.Serializable;

public class OutputDTO<T> implements Serializable {

    private String code = "0";

    private String msg = "操作成功";

    private T data;

    public OutputDTO() {
    }

    public OutputDTO(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
