package za.co.airwide.marketplace.dto;

import java.io.Serializable;

public class LowerLevelSubCategoryDTO
    implements Serializable {

    private Long id;
    private Integer categoryId;
    private Long higherLevelSubCategoryId;
    private Long middleLevelSubCategoryId;
    private String name;
    private String orderType;

    public LowerLevelSubCategoryDTO() {
    }

    public LowerLevelSubCategoryDTO(Long id,
                                    Integer categoryId,
                                    Long higherLevelSubCategoryId,
                                    Long middleLevelSubCategoryId,
                                    String name,
                                    String orderType) {
        this.id = id;
        this.categoryId = categoryId;
        this.higherLevelSubCategoryId = higherLevelSubCategoryId;
        this.middleLevelSubCategoryId = middleLevelSubCategoryId;
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

    public Long getMiddleLevelSubCategoryId() {
        return middleLevelSubCategoryId;
    }

    public void setMiddleLevelSubCategoryId(Long middleLevelSubCategoryId) {
        this.middleLevelSubCategoryId = middleLevelSubCategoryId;
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
                ", middleLevelSubCategoryId=" + middleLevelSubCategoryId +
                ", name='" + name + '\'' +
                ", orderType='" + orderType + '\'' +
                '}';
    }
}
