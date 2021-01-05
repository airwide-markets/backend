package za.co.airwide.marketplace.dto;

import java.io.Serializable;

public class CountryDTO implements Serializable {
    private int countryId;
    private String countryName;
    private int status;

    public CountryDTO() {
    }

    public CountryDTO(int countryId,
                      String countryName,
                      int status) {
        this.countryId = countryId;
        this.countryName = countryName;
        this.status = status;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
