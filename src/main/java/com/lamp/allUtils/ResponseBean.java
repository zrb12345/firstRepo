package com.lamp.allUtils;

import lombok.Data;

@Data
public class ResponseBean {
    private Integer status;
    private String message;
    private Object data;

    public static ResponseBean build() {
        return new ResponseBean();
    }

    public static ResponseBean ok(String message) {
        return new ResponseBean(200, message, null);
    }

    public static ResponseBean ok(String message, Object data) {
        return new ResponseBean(200, message, data);
    }

    public static ResponseBean error(String message) {
        return new ResponseBean(500, message, null);
    }

    public static ResponseBean error(String message, Object data) {
        return new ResponseBean(500, message, data);
    }

    public static ResponseBean error(Integer code, String message, Object data) {
        return new ResponseBean(code, message, data);
    }

    private ResponseBean() {

    }

    private ResponseBean(Integer status, String message, Object data) {
        this.status = status;
        this.message = message;
        if(data == null){
            this.data = "";
        }else{
            this.data = data;
        }
    }
}
