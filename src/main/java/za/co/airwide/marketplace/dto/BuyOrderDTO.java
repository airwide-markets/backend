package za.co.airwide.marketplace.dto;

import java.io.Serializable;

public class BuyOrderDTO implements Serializable {

    private long userId;
    private int categoryId;
    private int productClassId;
    private long productId;
    private String name;

    public BuyOrderDTO() {
    }

    public BuyOrderDTO(long userId,
                       int categoryId,
                       int productClassId,
                       long productId,
                       String name
                    ) {

        this.userId = userId;
        this.categoryId = categoryId;
        this.productClassId = productClassId;
        this.productId = productId;
        this.name = name;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
