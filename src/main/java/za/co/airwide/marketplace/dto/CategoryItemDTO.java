package za.co.airwide.marketplace.dto;

import java.io.Serializable;

public class CategoryItemDTO implements Serializable {
    private String categoryName;
    private Long itemId;
    private String itemDescription;

    public CategoryItemDTO() {
    }

    public CategoryItemDTO(String categoryName,
                           Long itemId,
                           String itemDescription) {

        this.categoryName = categoryName;
        this.itemId = itemId;
        this.itemDescription = itemDescription;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    @Override
    public String toString() {
        return "CategoryItemDTO{" +
                "categoryName='" + categoryName + '\'' +
                ", itemId=" + itemId +
                ", itemDescription='" + itemDescription + '\'' +
                '}';
    }
}
