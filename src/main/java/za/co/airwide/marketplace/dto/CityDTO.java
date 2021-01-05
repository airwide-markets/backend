package za.co.airwide.marketplace.dto;

import java.io.Serializable;

public class CityDTO
    implements Serializable {

    private int stateId;
    private int cityId;
    private String cityName;
    private int status;

    public CityDTO() {
    }

    public CityDTO(int stateId,
                   int cityId,
                   String cityName,
                   int status) {
        this.stateId = stateId;
        this.cityId = cityId;
        this.cityName = cityName;
        this.status = status;
    }

    public int getStateId() {
        return stateId;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}