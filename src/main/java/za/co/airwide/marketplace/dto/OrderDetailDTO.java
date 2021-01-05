package za.co.airwide.marketplace.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class OrderDetailDTO
    implements Serializable {

    private String userToken;
    private Long userId;
    private Integer categoryId;
    private String categoryName;
    private Long higherLevelSubCategoryId;
    private String higherLevelSubCategoryName;
    private Long middleLevelCategoryId;
    private String middleLevelCategoryName;
    private Long lowLevelSubCategoryId;
    private String lowLevelSubCategoryName;
    private Long orderId;
    private String orderType;
    private String description;
    private String narration;
    private Date orderDate;
    private Date availableDate;
    private Integer countryId;
    private String countryName;
    private String photoPath;
    private String orderKey;

    private List<FileDTO> files;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO( Long userId,
                          Integer categoryId,
                          String categoryName,
                          Long higherLevelSubCategoryId,
                          String higherLevelSubCategoryName,
                          Long middleLevelCategoryId,
                          String middleLevelCategoryName,
                          Long lowLevelSubCategoryId,
                          String lowLevelSubCategoryName,
                          Long orderId,
                          String orderType,
                          String description,
                          String narration,
                          Date orderDate,
                          Date availableDate,
                          Integer countryId,
                          String countryName,
                          String photoPath,
                          String orderKey,
                          List<FileDTO> files) {

        this.userId = userId;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.higherLevelSubCategoryId = higherLevelSubCategoryId;
        this.higherLevelSubCategoryName = higherLevelSubCategoryName;
        this.middleLevelCategoryId = middleLevelCategoryId;
        this.middleLevelCategoryName = middleLevelCategoryName;
        this.lowLevelSubCategoryId = lowLevelSubCategoryId;
        this.lowLevelSubCategoryName = lowLevelSubCategoryName;
        this.orderId = orderId;
        this.orderType = orderType;
        this.description = description;
        this.narration = narration;
        this.orderDate = orderDate;
        this.availableDate = availableDate;
        this.countryId = countryId;
        this.countryName = countryName;
        this.photoPath = photoPath;
        this.orderKey = orderKey;
        this.files = files;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getHigherLevelSubCategoryId() {
        return higherLevelSubCategoryId;
    }

    public void setHigherLevelSubCategoryId(Long higherLevelSubCategoryId) {
        this.higherLevelSubCategoryId = higherLevelSubCategoryId;
    }

    public String getHigherLevelSubCategoryName() {
        return higherLevelSubCategoryName;
    }

    public void setHigherLevelSubCategoryName(String higherLevelSubCategoryName) {
        this.higherLevelSubCategoryName = higherLevelSubCategoryName;
    }

    public Long getMiddleLevelCategoryId() {
        return middleLevelCategoryId;
    }

    public void setMiddleLevelCategoryId(Long middleLevelCategoryId) {
        this.middleLevelCategoryId = middleLevelCategoryId;
    }

    public String getMiddleLevelCategoryName() {
        return middleLevelCategoryName;
    }

    public void setMiddleLevelCategoryName(String middleLevelCategoryName) {
        this.middleLevelCategoryName = middleLevelCategoryName;
    }

    public Long getLowLevelSubCategoryId() {
        return lowLevelSubCategoryId;
    }

    public void setLowLevelSubCategoryId(Long lowLevelSubCategoryId) {
        this.lowLevelSubCategoryId = lowLevelSubCategoryId;
    }

    public String getLowLevelSubCategoryName() {
        return lowLevelSubCategoryName;
    }

    public void setLowLevelSubCategoryName(String lowLevelSubCategoryName) {
        this.lowLevelSubCategoryName = lowLevelSubCategoryName;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getAvailableDate() {
        return availableDate;
    }

    public void setAvailableDate(Date availableDate) {
        this.availableDate = availableDate;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getOrderKey() {
        return orderKey;
    }

    public void setOrderKey(String orderKey) {
        this.orderKey = orderKey;
    }

    public List<FileDTO> getFiles() {
        return files;
    }

    public void setFiles(List<FileDTO> files) {
        this.files = files;
    }

    @Override
    public String toString() {
        return "OrderDetailDTO{" +
                "userToken='" + userToken + '\'' +
                ", userId=" + userId +
                ", categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", higherLevelSubCategoryId=" + higherLevelSubCategoryId +
                ", higherLevelSubCategoryName='" + higherLevelSubCategoryName + '\'' +
                ", middleLevelCategoryId=" + middleLevelCategoryId +
                ", middleLevelCategoryName='" + middleLevelCategoryName + '\'' +
                ", lowLevelSubCategoryId=" + lowLevelSubCategoryId +
                ", lowLevelSubCategoryName='" + lowLevelSubCategoryName + '\'' +
                ", orderId=" + orderId +
                ", orderType='" + orderType + '\'' +
                ", description='" + description + '\'' +
                ", narration='" + narration + '\'' +
                ", orderDate=" + orderDate +
                ", availableDate=" + availableDate +
                ", countryId=" + countryId +
                ", countryName='" + countryName + '\'' +
                '}';
    }
}
