package com.cxx.exception;

import com.cxx.enums.AppHttpCodeEnum;

/**
 *
 * @author 陈喜喜
 */
public class SystemException extends RuntimeException{

    private static final long serialVersionUID = -3380535789277138332L;
    private int code;

    private String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public SystemException(AppHttpCodeEnum httpCodeEnum) {
        super(httpCodeEnum.getMsg());
        this.code = httpCodeEnum.getCode();
        this.msg = httpCodeEnum.getMsg();
    }

}
