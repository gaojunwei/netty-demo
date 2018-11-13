package com.jd.jr.innovation.epl.ap.guard.common.result;

import lombok.Data;

@Data
public class SingleResult<T> extends BasicResult {

    T data;

    public SingleResult(){}

}
