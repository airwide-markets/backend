package za.co.airwide.marketplace.dto;

import java.io.Serializable;
import java.util.Objects;

public class OrderKeyDTO
        implements Serializable {

    private Integer categoryId;
    private Long higherLevelSubCategoryId;
    private Long middleLevelSubCategoryId;
    private Long lowerLevelSubCategoryId;
    private Long orderId;

    public OrderKeyDTO() {
    }

    public OrderKeyDTO(Integer categoryId,
                       Long higherLevelSubCategoryId,
                       Long middleLevelSubCategoryId,
                       Long lowerLevelSubCategoryId,
                       Long orderId) {

        this.categoryId = categoryId;
        this.higherLevelSubCategoryId = higherLevelSubCategoryId;
        this.middleLevelSubCategoryId = middleLevelSubCategoryId;
        this.lowerLevelSubCategoryId = lowerLevelSubCategoryId;
        this.orderId = orderId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Long getHigherLevelSubCategoryId() {
        return higherLevelSubCategoryId;
    }

    public void setHigherLevelSubCategoryId(Long higherLevelSubCategoryId) {
        this.higherLevelSubCategoryId = higherLevelSubCategoryId;
    }

    public Long getMiddleLevelSubCategoryId() {
        return middleLevelSubCategoryId;
    }

    public void setMiddleLevelSubCategoryId(Long middleLevelSubCategoryId) {
        this.middleLevelSubCategoryId = middleLevelSubCategoryId;
    }

    public Long getLowerLevelSubCategoryId() {
        return lowerLevelSubCategoryId;
    }

    public void setLowerLevelSubCategoryId(Long lowerLevelSubCategoryId) {
        this.lowerLevelSubCategoryId = lowerLevelSubCategoryId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "OrderKeyDTO{" +
                "categoryId=" + categoryId +
                ", higherLevelSubCategoryId=" + higherLevelSubCategoryId +
                ", middleLevelSubCategoryId=" + middleLevelSubCategoryId +
                ", lowerLevelSubCategoryId=" + lowerLevelSubCategoryId +
                ", orderId=" + orderId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderKeyDTO that = (OrderKeyDTO) o;
        return Objects.equals(categoryId, that.categoryId) &&
                Objects.equals(higherLevelSubCategoryId, that.higherLevelSubCategoryId) &&
                Objects.equals(middleLevelSubCategoryId, that.middleLevelSubCategoryId) &&
                Objects.equals(lowerLevelSubCategoryId, that.lowerLevelSubCategoryId) &&
                Objects.equals(orderId, that.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryId, higherLevelSubCategoryId, middleLevelSubCategoryId, lowerLevelSubCategoryId, orderId);
    }
}
