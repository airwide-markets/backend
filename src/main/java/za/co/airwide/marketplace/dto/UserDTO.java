package za.co.airwide.marketplace.dto;

import java.io.Serializable;

public class UserDTO
        implements Serializable {

    private Long id;
    private String mobileName;
    private String name;
    private String surname;
    private String notificationEmail;

    public UserDTO(Long id,
                   String mobileName,
                   String name,
                   String surname) {
        this(id, mobileName, name, surname, null);
    }

    public UserDTO(Long id,
                   String mobileName,
                   String name,
                   String surname,
                   String notificationEmail) {
        this.id = id;
        this.mobileName = mobileName;
        this.name = name;
        this.surname = surname;
        this.notificationEmail = notificationEmail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMobileName() {
        return mobileName;
    }

    public void setMobileName(String mobileName) {
        this.mobileName = mobileName;
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

    public String getNotificationEmail() {
        return notificationEmail;
    }

    public void setNotificationEmail(String notificationEmail) {
        this.notificationEmail = notificationEmail;
    }
}
