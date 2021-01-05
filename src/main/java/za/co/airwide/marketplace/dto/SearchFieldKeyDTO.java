package za.co.airwide.marketplace.dto;

import java.io.Serializable;
import java.util.Objects;

public class SearchFieldKeyDTO implements Serializable {

    private Integer categoryId;
    private Long higherLevelSubCategoryId;
    private Long middleLevelSubCategoryId;
    private Long lowerLevelSubCategoryId;
    private String requestType;
    private String listingType;

    public SearchFieldKeyDTO() {
    }

    public SearchFieldKeyDTO(Integer categoryId,
                             Long higherLevelSubCategoryId,
                             Long middleLevelSubCategoryId,
                             Long lowerLevelSubCategoryId,
                             String requestType,
                             String listingType) {

        this.categoryId = categoryId;
        this.higherLevelSubCategoryId
                = higherLevelSubCategoryId != 0 ? higherLevelSubCategoryId : null;
        this.middleLevelSubCategoryId
                = middleLevelSubCategoryId != 0 ? middleLevelSubCategoryId : null;
        this.lowerLevelSubCategoryId
                = lowerLevelSubCategoryId != 0 ? lowerLevelSubCategoryId : null;
        this.requestType = requestType;
        this.listingType = listingType;
    }

    public SearchFieldKeyDTO(Integer categoryId,
                             String requestType,
                             String listingType) {
        this.categoryId = categoryId;
        this.requestType = requestType;
        this.listingType = listingType;
    }

    public SearchFieldKeyDTO(Integer categoryId,
                             Long higherLevelSubCategoryId,
                             String requestType,
                             String listingType) {
        this.categoryId = categoryId;
        this.higherLevelSubCategoryId = higherLevelSubCategoryId;
        this.requestType = requestType;
        this.listingType = listingType;
    }

    public SearchFieldKeyDTO(Integer categoryId,
                             Long higherLevelSubCategoryId,
                             Long middleLevelSubCategoryId,
                             String requestType,
                             String listingType) {
        this.categoryId = categoryId;
        this.higherLevelSubCategoryId = higherLevelSubCategoryId;
        this.middleLevelSubCategoryId = middleLevelSubCategoryId;
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

    public Long getLowerLevelSubCategoryId() {
        return lowerLevelSubCategoryId;
    }

    public void setLowerLevelSubCategoryId(Long lowerLevelSubCategoryId) {
        this.lowerLevelSubCategoryId = lowerLevelSubCategoryId;
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
        SearchFieldKeyDTO that = (SearchFieldKeyDTO) o;
        return Objects.equals(categoryId, that.categoryId) &&
                Objects.equals(higherLevelSubCategoryId, that.higherLevelSubCategoryId) &&
                Objects.equals(middleLevelSubCategoryId, that.middleLevelSubCategoryId) &&
                Objects.equals(lowerLevelSubCategoryId, that.lowerLevelSubCategoryId) &&
                Objects.equals(requestType, that.requestType) &&
                Objects.equals(listingType, that.listingType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryId, higherLevelSubCategoryId, middleLevelSubCategoryId,
                lowerLevelSubCategoryId, requestType, listingType);
    }

    @Override
    public String toString() {
        return "SearchFieldKeyDTO{" +
                "categoryId=" + categoryId +
                ", higherLevelSubCategoryId=" + higherLevelSubCategoryId +
                ", middleLevelSubCategoryId=" + middleLevelSubCategoryId +
                ", lowerLevelSubCategoryId=" + lowerLevelSubCategoryId +
                ", requestType='" + requestType + '\'' +
                ", listingType='" + listingType + '\'' +
                '}';
    }
}
