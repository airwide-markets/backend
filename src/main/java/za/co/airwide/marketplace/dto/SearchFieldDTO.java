package za.co.airwide.marketplace.dto;

import java.io.Serializable;
import java.util.Objects;

public class SearchFieldDTO implements Serializable {

    private Long id;
    private Integer categoryId;
    private Long higherLevelSubCategoryId;
    private Long middleLevelSubCategoryId;
    private Long lowerLevelSubCategoryId;
    private String name;
    private String requestType;
    private String listingType;

    public SearchFieldDTO() {
    }

    public SearchFieldDTO(Long id,
                          Integer categoryId,
                          Long higherLevelSubCategoryId,
                          Long middleLevelSubCategoryId,
                          Long lowerLevelSubCategoryId,
                          String name,
                          String requestType,
                          String listingType) {
        this.id = id;
        this.categoryId = categoryId;
        this.higherLevelSubCategoryId = higherLevelSubCategoryId;
        this.middleLevelSubCategoryId = middleLevelSubCategoryId;
        this.lowerLevelSubCategoryId = lowerLevelSubCategoryId;
        this.name = name;
        this.requestType = requestType;
        this.listingType = listingType;
    }

    public SearchFieldDTO( SearchFieldKeyDTO key,
                           String name) {
        this(   null,
                key.getCategoryId(),
                key.getHigherLevelSubCategoryId(),
                key.getMiddleLevelSubCategoryId(),
                key.getLowerLevelSubCategoryId(),
                name,
                key.getRequestType(),
                key.getListingType());
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

    public Long getLowerLevelSubCategoryId() {
        return lowerLevelSubCategoryId;
    }

    public void setLowerLevelSubCategoryId(Long lowerLevelSubCategoryId) {
        this.lowerLevelSubCategoryId = lowerLevelSubCategoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        SearchFieldDTO that = (SearchFieldDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(categoryId, that.categoryId) &&
                Objects.equals(higherLevelSubCategoryId, that.higherLevelSubCategoryId) &&
                Objects.equals(middleLevelSubCategoryId, that.middleLevelSubCategoryId) &&
                Objects.equals(lowerLevelSubCategoryId, that.lowerLevelSubCategoryId) &&
                Objects.equals(name, that.name) &&
                Objects.equals(requestType, that.requestType) &&
                Objects.equals(listingType, that.listingType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, categoryId, higherLevelSubCategoryId, middleLevelSubCategoryId,
                lowerLevelSubCategoryId, name, requestType, listingType);
    }

    @Override
    public String toString() {
        return "SearchFieldDTO{" +
                "id=" + id +
                ", categoryId=" + categoryId +
                ", higherLevelSubCategoryId=" + higherLevelSubCategoryId +
                ", middleLevelSubCategoryId=" + middleLevelSubCategoryId +
                ", lowerLevelSubCategoryId=" + lowerLevelSubCategoryId +
                ", name='" + name + '\'' +
                ", requestType='" + requestType + '\'' +
                ", listingType='" + listingType + '\'' +
                '}';
    }

}
