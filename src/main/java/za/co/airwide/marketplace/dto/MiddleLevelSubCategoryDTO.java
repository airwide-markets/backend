package za.co.airwide.marketplace.dto;

import java.io.Serializable;

public class MiddleLevelSubCategoryDTO
    implements Serializable {

    private Long id;
    private Integer categoryId;
    private Long higherLevelSubCategoryId;
    private String name;
    private String orderType;

    public MiddleLevelSubCategoryDTO() {
    }

    public MiddleLevelSubCategoryDTO(Long id,
                                     Integer categoryId,
                                     Long higherLevelSubCategoryId,
                                     String name,
                                     String orderType) {
        this.id = id;
        this.categoryId = categoryId;
        this.higherLevelSubCategoryId = higherLevelSubCategoryId;
        this.name = name;
        this.orderType = orderType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Long getHigherLevelSubCategoryId() {
        return higherLevelSubCategoryId;
    }

    public void setHigherLevelSubCategoryId(Long higherLevelSubCategoryId) {
        this.higherLevelSubCategoryId = higherLevelSubCategoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    @Override
    public String toString() {
        return "MiddleLevelSubCategoryDTO{" +
                "id=" + id +
                ", categoryId=" + categoryId +
                ", higherLevelSubCategoryId=" + higherLevelSubCategoryId +
                ", name='" + name + '\'' +
                ", orderType='" + orderType + '\'' +
                '}';
    }
}
