package za.co.airwide.marketplace.dto;

import java.io.Serializable;

public class StateDTO
    implements Serializable {

    private int countryId;
    private int stateId;
    private String stateName;
    private int status;

    public StateDTO() {
    }

    public StateDTO(int countryId,
                    int stateId,
                    String stateName,
                    int status) {

        this.countryId = countryId;
        this.stateId = stateId;
        this.stateName = stateName;
        this.status = status;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public int getStateId() {
        return stateId;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
