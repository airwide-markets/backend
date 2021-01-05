package za.co.airwide.marketplace.dto;

import java.util.Date;

public class JobListingDetailsDTO
    extends ListingDetailsDTO {

    private int category; // higher_level_subcategory
    private int field;    // medium_level_subcategory
    private int level;
    private int qualification;
    private String title; // name
    private Date deadline;
    private String details; // description
    private String city;
    private String country;

    public JobListingDetailsDTO() {
    }

    public JobListingDetailsDTO( long id,
                                 int parent,
                                 String type,
                                 int category,
                                 int field,
                                 int level,
                                 int qualification,
                                 String title,
                                 Date deadline,
                                 String details,
                                 String city,
                                 String country) {
        super(id, parent, type);
        this.category = category;
        this.field = field;
        this.level = level;
        this.qualification = qualification;
        this.title = title;
        this.deadline = deadline;
        this.details = details;
        this.city = city;
        this.country = country;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getField() {
        return field;
    }

    public void setField(int field) {
        this.field = field;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getQualification() {
        return qualification;
    }

    public void setQualification(int qualification) {
        this.qualification = qualification;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
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
}
