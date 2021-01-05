package za.co.airwide.marketplace.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class OrderDTO
    implements Serializable {

    private String userToken;

    private Integer categoryId;
    private String categoryName;
    private Long higherLevelSubCategoryId;
    private Long middleLevelCategoryId;
    private Long lowLevelSubCategoryId;
    private Long orderId;
    private Long userId;
    private String description;
    private String narration;
    private Date orderDate;
    private Date availableDate;
    private String orderType;
    private Integer countryId;
    private String photoPath;

    private String notificationEmail;

    public OrderDTO() {
    }

    public OrderDTO( String userToken,
                     Integer categoryId,
                     String categoryName,
                     Long higherLevelSubCategoryId,
                     Long middleLevelCategoryId,
                     Long lowLevelSubCategoryId,
                     Long orderId,
                     Long userId,
                     String description,
                     String narration,
                     Date orderDate,
                     Date availableDate,
                     String orderType,
                     Integer countryId,
                     String photoPath) {

        this( userToken, categoryId, categoryName,
                higherLevelSubCategoryId,middleLevelCategoryId,lowLevelSubCategoryId,
                orderId, userId, description, narration, orderDate, availableDate,
                orderType, countryId, photoPath, null);
    }

    public OrderDTO( String userToken,
                     Integer categoryId,
                     String categoryName,
                     Long higherLevelSubCategoryId,
                     Long middleLevelCategoryId,
                     Long lowLevelSubCategoryId,
                     Long orderId,
                     Long userId,
                     String description,
                     String narration,
                     Date orderDate,
                     Date availableDate,
                     String orderType,
                     Integer countryId,
                     String photoPath,
                     String notificationEmail) {

        this.userToken = userToken;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.higherLevelSubCategoryId = higherLevelSubCategoryId;
        this.middleLevelCategoryId = middleLevelCategoryId;
        this.lowLevelSubCategoryId = lowLevelSubCategoryId;
        this.orderId = orderId;
        this.userId = userId;
        this.description = description;
        this.narration = narration;
        this.orderDate = orderDate;
        this.availableDate = availableDate;
        this.orderType = orderType;
        this.countryId = countryId;
        this.photoPath = photoPath;
        this.notificationEmail = notificationEmail;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
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

    public Long getMiddleLevelCategoryId() {
        return middleLevelCategoryId;
    }

    public void setMiddleLevelCategoryId(Long middleLevelCategoryId) {
        this.middleLevelCategoryId = middleLevelCategoryId;
    }

    public Long getLowLevelSubCategoryId() {
        return lowLevelSubCategoryId;
    }

    public void setLowLevelSubCategoryId(Long lowLevelSubCategoryId) {
        this.lowLevelSubCategoryId = lowLevelSubCategoryId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public String getNotificationEmail() {
        return notificationEmail;
    }

    public void setNotificationEmail(String notificationEmail) {
        this.notificationEmail = notificationEmail;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "userToken='" + userToken + '\'' +
                ", categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", higherLevelSubCategoryId=" + higherLevelSubCategoryId +
                ", middleLevelCategoryId=" + middleLevelCategoryId +
                ", lowLevelSubCategoryId=" + lowLevelSubCategoryId +
                ", orderId=" + orderId +
                ", userId=" + userId +
                ", description='" + description + '\'' +
                ", narration='" + narration + '\'' +
                ", orderDate=" + orderDate +
                ", availableDate=" + availableDate +
                ", orderType='" + orderType + '\'' +
                ", countryId=" + countryId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDTO orderDTO = (OrderDTO) o;
        return Objects.equals(userToken, orderDTO.userToken) &&
                Objects.equals(categoryId, orderDTO.categoryId) &&
                Objects.equals(categoryName, orderDTO.categoryName) &&
                Objects.equals(higherLevelSubCategoryId, orderDTO.higherLevelSubCategoryId) &&
                Objects.equals(middleLevelCategoryId, orderDTO.middleLevelCategoryId) &&
                Objects.equals(lowLevelSubCategoryId, orderDTO.lowLevelSubCategoryId) &&
                Objects.equals(orderId, orderDTO.orderId) &&
                Objects.equals(userId, orderDTO.userId) &&
                Objects.equals(description, orderDTO.description) &&
                Objects.equals(narration, orderDTO.narration) &&
                Objects.equals(orderDate, orderDTO.orderDate) &&
                Objects.equals(availableDate, orderDTO.availableDate) &&
                Objects.equals(orderType, orderDTO.orderType) &&
                Objects.equals(countryId, orderDTO.countryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userToken, categoryId, categoryName, higherLevelSubCategoryId, middleLevelCategoryId,
                lowLevelSubCategoryId, orderId, userId, description, narration, orderDate, availableDate,
                orderType, countryId);
    }
}
