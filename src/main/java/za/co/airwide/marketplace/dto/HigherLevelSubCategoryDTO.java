package za.co.airwide.marketplace.dto;

import java.io.Serializable;
import java.util.Objects;

public class HigherLevelSubCategoryDTO
    implements Serializable {

    private Long id;
    private Integer categoryId;
    private String name;
    private String requestType;
    private String listingType;

    public HigherLevelSubCategoryDTO() {
    }

    public HigherLevelSubCategoryDTO(Long id,
                                     Integer categoryId,
                                     String name,
                                     String requestType,
                                     String listingType) {
        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
        this.requestType = requestType;
        this.listingType = listingType;
    }

    public HigherLevelSubCategoryDTO(Integer categoryId,
                                     String name,
                                     String requestType,
                                     String listingType) {
        this(null, categoryId, name, requestType, listingType);
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
        HigherLevelSubCategoryDTO that = (HigherLevelSubCategoryDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(categoryId, that.categoryId) &&
                Objects.equals(name, that.name) &&
                Objects.equals(requestType, that.requestType) &&
                Objects.equals(listingType, that.listingType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, categoryId, name, requestType, listingType);
    }

    @Override
    public String toString() {
        return "HigherLevelSubCategoryDTO{" +
                "id=" + id +
                ", categoryId=" + categoryId +
                ", name='" + name + '\'' +
                ", requestType='" + requestType + '\'' +
                ", listingType='" + listingType + '\'' +
                '}';
    }
}
