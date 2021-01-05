package za.co.airwide.marketplace.dto;

import java.math.BigDecimal;
import java.util.Date;

public final class AccommodationListingDetailsDTO
        extends ListingDetailsDTO {

    private Integer property; // category id: 2
    private String thumbnail; // category id: 2
    private Integer bedrooms; // category id: 2
    private BigDecimal price; // category id: 2
    private String town;      // category id: 2
    private String country;   // category id: 2
    private Date dateVacant;  // category id: 2

    public AccommodationListingDetailsDTO() {
    }

    public AccommodationListingDetailsDTO (
            long id,
            int parent,
            String type,
            Integer property,
            String thumbnail,
            Integer bedrooms,
            BigDecimal price,
            String town,
            String country,
            Date dateVacant) {

        super(id, parent, type);
        this.property = property;
        this.thumbnail = thumbnail;
        this.bedrooms = bedrooms;
        this.price = price;
        this.town = town;
        this.country = country;
        this.dateVacant = dateVacant;
    }

    public Integer getProperty() {
        return property;
    }

    public void setProperty(Integer property) {
        this.property = property;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Integer getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(Integer bedrooms) {
        this.bedrooms = bedrooms;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getDateVacant() {
        return dateVacant;
    }

    public void setDateVacant(Date dateVacant) {
        this.dateVacant = dateVacant;
    }
}
