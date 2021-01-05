package za.co.airwide.marketplace.dto;

public class RegistrationRequestDTO {

    private String msisdn;
    private String dialCode;
    private String localMobileNumber;
    private String name;
    private String lastName;
    private String password;
    private String town;
    private String country;
    private String countryData;

    public RegistrationRequestDTO() {
    }

    public RegistrationRequestDTO(String msisdn,
                                  String dialCode,
                                  String localMobileNumber,
                                  String name,
                                  String lastName,
                                  String password,
                                  String town,
                                  String country,
                                  String countryData) {
        this.msisdn = msisdn;
        this.dialCode = dialCode;
        this.localMobileNumber = localMobileNumber;
        this.name = name;
        this.lastName = lastName;
        this.password = password;
        this.town = town;
        this.country = country;
        this.countryData = countryData;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getDialCode() {
        return dialCode;
    }

    public void setDialCode(String dialCode) {
        this.dialCode = dialCode;
    }

    public String getLocalMobileNumber() {
        return localMobileNumber;
    }

    public void setLocalMobileNumber(String localMobileNumber) {
        this.localMobileNumber = localMobileNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getCountryData() {
        return countryData;
    }

    public void setCountryData(String countryData) {
        this.countryData = countryData;
    }
}
