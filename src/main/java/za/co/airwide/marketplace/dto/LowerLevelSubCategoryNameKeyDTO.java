package za.co.airwide.marketplace.dto;

import java.io.Serializable;
import java.util.Objects;

public class LowerLevelSubCategoryNameKeyDTO
        implements Serializable {

    private Integer categoryId;
    private Long higherLevelSubCategoryId;
    private Long middleLevelSubCategoryId;
    private String lowerLevelSubCategoryName;
    private String requestType;
    private String listingType;

    public LowerLevelSubCategoryNameKeyDTO() {
    }

    public LowerLevelSubCategoryNameKeyDTO(Integer categoryId,
                                           Long higherLevelSubCategoryId,
                                           Long middleLevelSubCategoryId,
                                           String lowerLevelSubCategoryName,
                                           String requestType,
                                           String listingType) {
        this.categoryId = categoryId;
        this.higherLevelSubCategoryId = higherLevelSubCategoryId;
        this.middleLevelSubCategoryId = middleLevelSubCategoryId;
        this.lowerLevelSubCategoryName = lowerLevelSubCategoryName;
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

    public Long getMiddleLevelSubCategoryId() {
        return middleLevelSubCategoryId;
    }

    public void setMiddleLevelSubCategoryId(Long middleLevelSubCategoryId) {
        this.middleLevelSubCategoryId = middleLevelSubCategoryId;
    }

    public String getLowerLevelSubCategoryName() {
        return lowerLevelSubCategoryName;
    }

    public void setLowerLevelSubCategoryName(String lowerLevelSubCategoryName) {
        this.lowerLevelSubCategoryName = lowerLevelSubCategoryName;
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
        LowerLevelSubCategoryNameKeyDTO that = (LowerLevelSubCategoryNameKeyDTO) o;
        return Objects.equals(categoryId, that.categoryId) &&
                Objects.equals(higherLevelSubCategoryId, that.higherLevelSubCategoryId) &&
                Objects.equals(middleLevelSubCategoryId, that.middleLevelSubCategoryId) &&
                Objects.equals(lowerLevelSubCategoryName, that.lowerLevelSubCategoryName) &&
                Objects.equals(requestType, that.requestType) &&
                Objects.equals(listingType, that.listingType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryId, higherLevelSubCategoryId, middleLevelSubCategoryId, lowerLevelSubCategoryName, requestType, listingType);
    }

    @Override
    public String toString() {
        return "LowerLevelSubCategoryNameKeyDTO{" +
                "categoryId=" + categoryId +
                ", higherLevelSubCategoryId=" + higherLevelSubCategoryId +
                ", middleLevelSubCategoryId=" + middleLevelSubCategoryId +
                ", lowerLevelSubCategoryName='" + lowerLevelSubCategoryName + '\'' +
                ", requestType='" + requestType + '\'' +
                ", listingType='" + listingType + '\'' +
                '}';
    }
}
