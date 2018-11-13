package com.jd.jr.innovation.epl.ap.guard.common;

/**
 * 系统错误码
 * 000001 ~ 009999 为系统层面预留错误码(不要占用)
 *
 * @author gaojunwei
 * 2018-11-13
 */
public enum SystemCode {

    SUCCESS("000000", "success"),

    EXCEPTION("999999", "系统异常"),
    PARAMETER_ERROR("000001", "参数错误"),

    END("无效","无效");

    private String code;
    private String msg;

    SystemCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
