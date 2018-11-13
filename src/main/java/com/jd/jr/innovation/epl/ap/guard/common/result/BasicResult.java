package com.jd.jr.innovation.epl.ap.guard.common.result;

import lombok.Data;

import java.io.Serializable;

@Data
public class BasicResult implements Serializable {

    private static final long serialVersionUID = -5809782578272943999L;

    private String code;
    private String msg;

    public BasicResult() {
    }
}
