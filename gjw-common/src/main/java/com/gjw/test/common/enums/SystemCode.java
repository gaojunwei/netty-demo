package com.gjw.test.common.enums;

/**
 * 系统错误码
 * @author gaojunwei
 * 2018-12-04
 */
public enum SystemCode {

    /**************************** 000系统级异常码 start *****************************/
    SYSTEM_ERROR("999999", "系统错误!"),
    PARAM_ERROR("000001", "非法请求参数"),
    DATA_NOT_EXIST_ERROR("000002", "数据不存在"),


    END("", "标识结尾无意义");
    private String code;
    private String message;

    SystemCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
