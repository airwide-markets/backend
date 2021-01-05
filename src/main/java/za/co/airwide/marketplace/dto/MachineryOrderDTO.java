//package za.co.airwide.marketplace.dto;
//
//import java.util.Date;
//
//public class MachineryOrderDTO
//        extends OrderDTO {
//
//    private Long orderDetailId;
//    private String description;
//    private String narration;
//
//    public MachineryOrderDTO() {
//    }
//
//    public MachineryOrderDTO(String userToken,
//                             Integer categoryId,
//                             String categoryName,
//                             Long higherLevelSubCategoryId,
//                             Long middleLevelCategoryId,
//                             Long lowLevelSubCategoryId,
//                             Long orderId,
//                             Long userId,
//                             Date orderDate,
//                             String orderType,
//                             Long orderDetailId,
//                             String description,
//                             String narration) {
//
//        super( userToken, categoryId, categoryName,
//               higherLevelSubCategoryId, middleLevelCategoryId, lowLevelSubCategoryId,
//               orderId, userId, orderDate, orderType);
//
//        this.orderDetailId = orderDetailId;
//        this.description = description;
//        this.narration = narration;
//    }
//
//    public MachineryOrderDTO(String userToken,
//                             Integer categoryId,
//                             Long higherLevelSubCategoryId,
//                             Long middleLevelCategoryId,
//                             Long lowLevelSubCategoryId,
//                             Long orderId,
//                             Long userId,
//                             Date orderDate,
//                             String orderType,
//                             Long orderDetailId,
//                             String description,
//                             String narration) {
//        this( userToken, categoryId, null, higherLevelSubCategoryId, middleLevelCategoryId,
//                lowLevelSubCategoryId,orderId, userId, orderDate, orderType, orderDetailId,
//                description, narration );
//    }
//
//    public Long getOrderDetailId() {
//        return orderDetailId;
//    }
//
//    public void setOrderDetailId(Long orderDetailId) {
//        this.orderDetailId = orderDetailId;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public String getNarration() {
//        return narration;
//    }
//
//    public void setNarration(String narration) {
//        this.narration = narration;
//    }
//
//    @Override
//    public String toString() {
//        return "ServiceOrderDTO{" +
//                "orderDetailId=" + orderDetailId +
//                ", description='" + description + '\'' +
//                ", narration='" + narration + '\'' +
//                '}';
//    }
//}
