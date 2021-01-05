package za.co.airwide.marketplace.dto;

import java.io.Serializable;

public class BrandDTO
        implements Serializable {

    private long id;
    private int category;
    private long higher;
    private long medium;
    private long lower;
    private String name;
    private String icon;

    public BrandDTO() {
    }

    public BrandDTO(long id,
                    int category,
                    long higher,
                    long medium,
                    long lower,
                    String name,
                    String icon) {

        this.id = id;
        this.category = category;
        this.higher = higher;
        this.medium = medium;
        this.lower = lower;
        this.name = name;
        this.icon = icon;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public long getHigher() {
        return higher;
    }

    public void setHigher(long higher) {
        this.higher = higher;
    }

    public long getMedium() {
        return medium;
    }

    public void setMedium(long medium) {
        this.medium = medium;
    }

    public long getLower() {
        return lower;
    }

    public void setLower(long lower) {
        this.lower = lower;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
