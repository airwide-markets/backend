package za.co.airwide.marketplace.dto;

import java.sql.Timestamp;

public class RequestDTO {

    private String userToken;
    private Integer category;
    private String name;
    private Integer type;
    private Timestamp added;
    private Integer post;
    private AccommodationDTO details;

    public RequestDTO() {
    }

    public RequestDTO(String userToken,
                      Integer category,
                      String name,
                      Integer type,
                      Timestamp added,
                      Integer post,
                      AccommodationDTO details) {
        this.userToken = userToken;
        this.category = category;
        this.name = name;
        this.type = type;
        this.added = added;
        this.post = post;
        this.details = details;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Timestamp getAdded() {
        return added;
    }

    public void setAdded(Timestamp added) {
        this.added = added;
    }

    public Integer getPost() {
        return post;
    }

    public void setPost(Integer post) {
        this.post = post;
    }

    public AccommodationDTO getDetails() {
        return details;
    }

    public void setDetails(AccommodationDTO details) {
        this.details = details;
    }
}
