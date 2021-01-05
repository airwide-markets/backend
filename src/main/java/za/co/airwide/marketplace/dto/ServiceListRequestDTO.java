package za.co.airwide.marketplace.dto;

import java.io.Serializable;

public class ServiceListRequestDTO implements Serializable {

    private int categoryId;
    private int productClassId;

    public ServiceListRequestDTO() {
    }

    public ServiceListRequestDTO(int categoryId, int productClassId) {
        this.categoryId = categoryId;
        this.productClassId = productClassId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getProductClassId() {
        return productClassId;
    }

    public void setProductClassId(int productClassId) {
        this.productClassId = productClassId;
    }
}
