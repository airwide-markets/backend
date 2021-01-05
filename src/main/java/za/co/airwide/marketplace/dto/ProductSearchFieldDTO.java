package za.co.airwide.marketplace.dto;

import java.io.Serializable;

public class ProductSearchFieldDTO implements Serializable {

    private int categoryId;
    private int productClassId;
    private Long id;
    private String name;

    public ProductSearchFieldDTO() {
    }

    public ProductSearchFieldDTO(int categoryId,
                                 int productClassId,
                                 Long id,
                                 String name) {
        this.categoryId = categoryId;
        this.productClassId = productClassId;
        this.id = id;
        this.name = name;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
