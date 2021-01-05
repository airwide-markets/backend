package za.co.airwide.marketplace.dto;

import java.math.BigDecimal;
import java.util.Date;

public class VehicleListingDetailsDTO
    extends ListingDetailsDTO {

    private long mode; // higher_level_sub_category
    private long vehicleType; // middle_level_sub_category
    private long subType; // lower_level_subcategory
    private BrandDTO brand;
    private String thumbnail;
    private BigDecimal price;
    private String description;
    private String transmission;
    private String fuel;
    private String city;
    private String country;
    private Date dateCreated;

    public VehicleListingDetailsDTO() {
    }

    public VehicleListingDetailsDTO(long id,
                                    int parent,
                                    String type,
                                    long mode,
                                    long vehicleType,
                                    long subType,
                                    BrandDTO brand,
                                    String thumbnail,
                                    BigDecimal price,
                                    String description,
                                    String transmission,
                                    String fuel,
                                    String city,
                                    String country,
                                    Date dateCreated) {

        super(id, parent, type);
        this.mode = mode;
        this.vehicleType = vehicleType;
        this.subType = subType;
        this.brand = brand;
        this.thumbnail = thumbnail;
        this.price = price;
        this.description = description;
        this.transmission = transmission;
        this.fuel = fuel;
        this.city = city;
        this.country = country;
        this.dateCreated = dateCreated;
    }

    public long getMode() {
        return mode;
    }

    public void setMode(long mode) {
        this.mode = mode;
    }

    public long getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(long vehicleType) {
        this.vehicleType = vehicleType;
    }

    public long getSubType() {
        return subType;
    }

    public void setSubType(long subType) {
        this.subType = subType;
    }

    public BrandDTO getBrand() {
        return brand;
    }

    public void setBrand(BrandDTO brand) {
        this.brand = brand;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
