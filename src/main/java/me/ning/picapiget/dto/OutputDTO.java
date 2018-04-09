package me.ning.picapiget.dto;

import java.io.Serializable;

public class OutputDTO implements Serializable {

    private String code = "0";

    private String msg = "操作成功";

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
}
