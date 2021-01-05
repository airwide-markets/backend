package za.co.airwide.marketplace.model;

import java.io.Serializable;
import java.util.Objects;

public class UserDTO implements Serializable {
    private String userId;
    private String userPhone;
    private String userEmail;
    private String userFirstName;
    private String userSurname;
    private String userRole;
    private String userPhotoUrl;

    public UserDTO() {
    }

    public UserDTO(String userId,
                   String userPhone,
                   String userEmail,
                   String userFirstName,
                   String userSurname,
                   String userRole,
                   String userPhotoUrl) {
        this.userId = userId;
        this.userPhone = userPhone;
        this.userEmail = userEmail;
        this.userFirstName = userFirstName;
        this.userSurname = userSurname;
        this.userRole = userRole;
        this.userPhotoUrl = userPhotoUrl;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUserPhotoUrl() {
        return userPhotoUrl;
    }

    public void setUserPhotoUrl(String userPhotoUrl) {
        this.userPhotoUrl = userPhotoUrl;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userId='" + userId + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userFirstName='" + userFirstName + '\'' +
                ", userSurname='" + userSurname + '\'' +
                ", userRole='" + userRole + '\'' +
                ", userPhotoUrl='" + userPhotoUrl + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(userId, userDTO.userId) &&
                Objects.equals(userPhone, userDTO.userPhone) &&
                Objects.equals(userEmail, userDTO.userEmail) &&
                Objects.equals(userFirstName, userDTO.userFirstName) &&
                Objects.equals(userSurname, userDTO.userSurname) &&
                Objects.equals(userRole, userDTO.userRole) &&
                Objects.equals(userPhotoUrl, userDTO.userPhotoUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userPhone, userEmail, userFirstName, userSurname, userRole, userPhotoUrl);
    }
}
