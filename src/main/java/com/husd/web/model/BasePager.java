package com.husd.web.model;

/**
 * Created by hushengdong on 16/6/3.
 */
abstract class BasePager {

    protected int start = 0;

    protected int pageSize = 20;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    @Override
    public String toString() {
        return "BasePagerCondition{" + "start=" + start + ", pageSize=" + pageSize + '}';
    }
}
