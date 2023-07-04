package com.wxprocedure.dto;

import com.wxprocedure.entity.PageBean;

public class SmallTypeSearchDto {

    private String smallTypeName;
    private PageBean pageBean;

    public String getSmallTypeName() {
        return smallTypeName;
    }

    public void setSmallTypeName(String smallTypeName) {
        this.smallTypeName = smallTypeName;
    }

    public PageBean getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean pageBean) {
        this.pageBean = pageBean;
    }
}
