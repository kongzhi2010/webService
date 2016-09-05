package husd.web.model;

/**
 * Created by hushengdong on 16/6/3.
 */
public class DemoCondition {

    private int start = 0;

    private int pageSize = 20;

    private String productId;

    private String batchCode;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getPageSize() {
        return pageSize;
    }

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
        return "DemoCondition{" +
                "productId=" + productId +
                ", batchCode='" + batchCode + '\'' +
                '}';
    }
}
