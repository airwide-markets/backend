package za.co.airwide.marketplace.dto;

import java.io.Serializable;

public class ActivateAccountRequestDTO implements Serializable {

    private String msisdn;
    private String code;

    public ActivateAccountRequestDTO() {
    }

    public ActivateAccountRequestDTO(String msisdn, String code) {
        this.msisdn = msisdn;
        this.code = code;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "ActivateAccountRequestDTO{" +
                "msisdn='" + msisdn + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
