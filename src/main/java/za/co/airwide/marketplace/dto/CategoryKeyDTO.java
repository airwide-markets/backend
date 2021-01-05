package za.co.airwide.marketplace.dto;

import java.util.Objects;

public class CategoryKeyDTO {

    private final String name;
    private final String requestType;
    private final String listingType;

    public CategoryKeyDTO(String name,
                          String requestType,
                          String listingType) {
        this.name = name;
        this.requestType = requestType;
        this.listingType = listingType;
    }

    public String getName() {
        return name;
    }

    public String getRequestType() {
        return requestType;
    }

    public String getListingType() {
        return listingType;
    }

    @Override
    public String toString() {
        return "CategoryKeyDTO{" +
                "name='" + name + '\'' +
                ", requestType='" + requestType + '\'' +
                ", listingType='" + listingType + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryKeyDTO that = (CategoryKeyDTO) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(requestType, that.requestType) &&
                Objects.equals(listingType, that.listingType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, requestType, listingType);
    }
}
