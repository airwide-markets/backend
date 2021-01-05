package za.co.airwide.marketplace.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AccommodationDTO implements Serializable {

    /*
    Unrecognized field "state" (class za.co.airwide.marketplace.dto.AccommodationDTO), n
    ot marked as ignorable
     (11 known properties: , "price", "parent", "name", "thumbnail", "town", "property", "country", "bedrooms", "type", "id", "dateVacant"])
 at [Source: org.glassfish.jersey.message.internal.ReaderInterceptorExecutor$UnCloseableInputStream@21786ee6; line: 1, column: 221]
  (through reference chain: za.co.airwide.marketplace.dto.RequestDTO["details"]->za.co.airwide.marketplace.dto.AccommodationDTO["state"])

     */
    private long id;
    private int parent;
    private String type;
    private String name;
    private Integer property;
    private String thumbnail;
    private Integer bedrooms;
    private BigDecimal price;
    private int town;
    private int country;
    private int state;
    private Date dateVacant;

    public AccommodationDTO() {
    }

    public AccommodationDTO(long id,
                            int parent,
                            String type,
                            String name,
                            Integer property,
                            String thumbnail,
                            Integer bedrooms,
                            BigDecimal price,
                            int town,
                            int country,
                            int state,
                            Date dateVacant) {

        this.id = id;
        this.parent = parent;
        this.type = type;
        this.name = name;
        this.property = property;
        this.thumbnail = thumbnail;
        this.bedrooms = bedrooms;
        this.price = price;
        this.town = town;
        this.country = country;
        this.state = state;
        this.dateVacant = dateVacant;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getTown() {
        return town;
    }

    public void setTown(int town) {
        this.town = town;
    }

    public int getCountry() {
        return country;
    }

    public void setCountry(int country) {
        this.country = country;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Date getDateVacant() {
        return dateVacant;
    }

    public void setDateVacant(Date dateVacant) {
        this.dateVacant = dateVacant;
    }
}
