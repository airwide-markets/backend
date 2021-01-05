package za.co.airwide.marketplace.dto;

import java.io.Serializable;
import java.util.Objects;

public class MiddleLevelSubCategoryNameKeyDTO
        implements Serializable {

    private Integer categoryId;
    private Long higherLevelSubCategoryId;
    private String middleLevelSubCategoryName;
    private String requestType;
    private String listingType;

    public MiddleLevelSubCategoryNameKeyDTO() {
    }

    public MiddleLevelSubCategoryNameKeyDTO(Integer categoryId,
                                            Long higherLevelSubCategoryId,
                                            String middleLevelSubCategoryName,
                                            String requestType,
                                            String listingType) {
        this.categoryId = categoryId;
        this.higherLevelSubCategoryId = higherLevelSubCategoryId;
        this.middleLevelSubCategoryName = middleLevelSubCategoryName;
        this.requestType = requestType;
        this.listingType = listingType;
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

    public String getMiddleLevelSubCategoryName() {
        return middleLevelSubCategoryName;
    }

    public void setMiddleLevelSubCategoryName(String middleLevelSubCategoryName) {
        this.middleLevelSubCategoryName = middleLevelSubCategoryName;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getListingType() {
        return listingType;
    }

    public void setListingType(String listingType) {
        this.listingType = listingType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MiddleLevelSubCategoryNameKeyDTO that = (MiddleLevelSubCategoryNameKeyDTO) o;
        return Objects.equals(categoryId, that.categoryId) &&
                Objects.equals(higherLevelSubCategoryId, that.higherLevelSubCategoryId) &&
                Objects.equals(middleLevelSubCategoryName, that.middleLevelSubCategoryName) &&
                Objects.equals(requestType, that.requestType) &&
                Objects.equals(listingType, that.listingType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryId, higherLevelSubCategoryId, middleLevelSubCategoryName, requestType, listingType);
    }

    @Override
    public String toString() {
        return "MiddleLevelSubCategoryNameKeyDTO{" +
                "categoryId=" + categoryId +
                ", higherLevelSubCategoryId=" + higherLevelSubCategoryId +
                ", middleLevelSubCategoryName='" + middleLevelSubCategoryName + '\'' +
                ", requestType='" + requestType + '\'' +
                ", listingType='" + listingType + '\'' +
                '}';
    }
}
