package za.co.airwide.marketplace.dto;

import java.util.Objects;

public class HigherLevelSubCategoryKeyDTO {

    private Integer categoryId;
    private String higherLevelSubCategoryName;
    private String requestType;
    private String listingType;

    public HigherLevelSubCategoryKeyDTO(Integer categoryId,
                                        String higherLevelSubCategoryName,
                                        String requestType,
                                        String listingType) {
        this.categoryId = categoryId;
        this.higherLevelSubCategoryName = higherLevelSubCategoryName;
        this.requestType = requestType;
        this.listingType = listingType;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getHigherLevelSubCategoryName() {
        return higherLevelSubCategoryName;
    }

    public void setHigherLevelSubCategoryName(String higherLevelSubCategoryName) {
        this.higherLevelSubCategoryName = higherLevelSubCategoryName;
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
        HigherLevelSubCategoryKeyDTO that = (HigherLevelSubCategoryKeyDTO) o;
        return Objects.equals(categoryId, that.categoryId) &&
                Objects.equals(higherLevelSubCategoryName, that.higherLevelSubCategoryName) &&
                Objects.equals(requestType, that.requestType) &&
                Objects.equals(listingType, that.listingType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryId, higherLevelSubCategoryName, requestType, listingType);
    }

    @Override
    public String toString() {
        return "HigherLevelSubCategoryKeyDTO{" +
                "categoryId=" + categoryId +
                ", higherLevelSubCategoryName='" + higherLevelSubCategoryName + '\'' +
                ", requestType='" + requestType + '\'' +
                ", listingType='" + listingType + '\'' +
                '}';
    }
}
