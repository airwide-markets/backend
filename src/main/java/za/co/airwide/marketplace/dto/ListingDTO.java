package za.co.airwide.marketplace.dto;

import java.math.BigDecimal;
import java.util.Date;

public class ListingDTO {
        /*
        'listingType' => 'request',
        'id' => $rs['id'],
        'user' => $rs['user'],
        'category' => $this->Categories($rs['category']),
        'name' => $rs['name'],
        'type' => $this->request_Type($rs['type']),
        'details' => $this->requestDetails($rs['category'],$rs['id']),
        'date_created' => $rs['added'],
        'post' => $rs['post']
        */
    // services
    // id, type, category_id AS parent_id, P.name, S.narrative

    /*
        ACCOMMODATION

        'id' => $rs['id'],
        'parent' => $rs['listing_id'],
        'type' => $rs['type'],
        'property' => $rs['higher_level_sub_category'],
        'thumbnail' => $rs['thumbnail'],
        'bedrooms' => $rs['bedrooms'],
        'price' => $rs['price'],
        'town' => $rs['town'],
        'country' => $rs['country'],
        'date_vacant' => $rs['date_vacant']
     */
    private String listingType;
    private long id;
    private long user;
    private CategoryDTO category;
    private String name;
    private RequestTypeDTO type;
    private ListingDetailsDTO details;
    private Date dateCreated;
    private int post;

    /*
    private String narrative;
    private String thumbnail;
    private Integer bedrooms;
    private BigDecimal price;
    private String town;
    private String country;
    private Date dateVacant;
    */

    public ListingDTO() {
    }

    public ListingDTO(String listingType,
                      long id,
                      long user,
                      CategoryDTO category,
                      String name,
                      RequestTypeDTO type,
                      ListingDetailsDTO details,
                      Date dateCreated,
                      int post) {
        this.listingType = listingType;
        this.id = id;
        this.user = user;
        this.category = category;
        this.name = name;
        this.type = type;
        this.details = details;
        this.dateCreated = dateCreated;
        this.post = post;
    }

    public String getListingType() {
        return listingType;
    }

    public void setListingType(String listingType) {
        this.listingType = listingType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUser() {
        return user;
    }

    public void setUser(long user) {
        this.user = user;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RequestTypeDTO getType() {
        return type;
    }

    public void setType(RequestTypeDTO type) {
        this.type = type;
    }

    public ListingDetailsDTO getDetails() {
        return details;
    }

    public void setDetails(ListingDetailsDTO details) {
        this.details = details;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public int getPost() {
        return post;
    }

    public void setPost(int post) {
        this.post = post;
    }
}
