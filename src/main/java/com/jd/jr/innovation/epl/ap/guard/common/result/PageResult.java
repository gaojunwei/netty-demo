package com.jd.jr.innovation.epl.ap.guard.common.result;

import lombok.Data;

@Data
public class PageResult<T> extends ListResult<T>{

    private int pageNo;
    private int pageSize;
    private long totalCount;

    public PageResult(){
    }

}
