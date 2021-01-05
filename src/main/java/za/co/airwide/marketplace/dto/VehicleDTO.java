package za.co.airwide.marketplace.dto;

import java.math.BigDecimal;
import java.util.Date;

public class VehicleDTO {

    private Long id;
    private String type; // 'Request','Listing'
    private Long listingId;
    private String name;
    private Long higherLevelSubCategoryId;
    private Long middleLevelSubCategory;
    private String make;
    private String thumbnail;
    private String description;
    private String transmission;
    private String fuel; // 'Petrol','Diesiel','Electric','Other'
    private Long city;
    private Long country;
    private BigDecimal price;
    private Date dateCreated;

    public VehicleDTO(Long id,
                      String type,
                      Long listingId,
                      String name,
                      Long higherLevelSubCategoryId,
                      Long middleLevelSubCategory,
                      String make,
                      String thumbnail,
                      String description,
                      String transmission,
                      String fuel,
                      Long city,
                      Long country,
                      BigDecimal price,
                      Date dateCreated) {

        this.id = id;
        this.type = type;
        this.listingId = listingId;
        this.name = name;
        this.higherLevelSubCategoryId = higherLevelSubCategoryId;
        this.middleLevelSubCategory = middleLevelSubCategory;
        this.make = make;
        this.thumbnail = thumbnail;
        this.description = description;
        this.transmission = transmission;
        this.fuel = fuel;
        this.city = city;
        this.country = country;
        this.price = price;
        this.dateCreated = dateCreated;
    }

    public VehicleDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getListingId() {
        return listingId;
    }

    public void setListingId(Long listingId) {
        this.listingId = listingId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getHigherLevelSubCategoryId() {
        return higherLevelSubCategoryId;
    }

    public void setHigherLevelSubCategoryId(Long higherLevelSubCategoryId) {
        this.higherLevelSubCategoryId = higherLevelSubCategoryId;
    }

    public Long getMiddleLevelSubCategory() {
        return middleLevelSubCategory;
    }

    public void setMiddleLevelSubCategory(Long middleLevelSubCategory) {
        this.middleLevelSubCategory = middleLevelSubCategory;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public Long getCity() {
        return city;
    }

    public void setCity(Long city) {
        this.city = city;
    }

    public Long getCountry() {
        return country;
    }

    public void setCountry(Long country) {
        this.country = country;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
