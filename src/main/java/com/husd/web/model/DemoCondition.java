package com.husd.web.model;

/**
 * Created by hushengdong on 16/6/3.
 */
public class DemoCondition extends BasePager {

    private String productId;

    private String batchCode;

    @Override
    public int getStart() {
        return start;
    }

    @Override
    public void setStart(int start) {
        this.start = start;
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    @Override
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(String batchCode) {
        this.batchCode = batchCode;
    }

    @Override
    public String toString() {
        return "DemoCondition{" + "productId=" + productId + ", batchCode='" + batchCode + '\'' + '}';
    }
}
