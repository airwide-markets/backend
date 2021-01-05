package za.co.airwide.marketplace.dao;

import java.io.Serializable;

public class User implements Serializable {

    private String success;
    private String message;
    private String token;
    private String name;
    private String surname;
    private String email;
    private String msisdn;
    private String address;
    private String town;
    private String country;
    private String profile;
    private String status;

    public User() {
    }

    public User(String name,
                String surname,
                String email,
                String msisdn,
                String address,
                String town,
                String country,
                String profile,
                String status) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.msisdn = msisdn;
        this.address = address;
        this.town = town;
        this.country = country;
        this.profile = profile;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", msisdn='" + msisdn + '\'' +
                ", address='" + address + '\'' +
                ", town='" + town + '\'' +
                ", country='" + country + '\'' +
                ", profile='" + profile + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
