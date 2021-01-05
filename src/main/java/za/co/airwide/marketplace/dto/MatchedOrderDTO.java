package za.co.airwide.marketplace.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class MatchedOrderDTO
        implements Serializable {
    /*
               "    SELECT a.category_id, c.category_name,                                                          " +
               "           a.higher_level_sub_category_id, h.name as higher_level_sub_category_name,                " +
               "           a.middle_level_sub_category_id, m.name as middle_level_sub_category_name,                " +
               "           a.lower_level_sub_category_id,  l.name as lower_level_sub_category_name,  a.order_type,  " +
               "           a.order_id as my_order_id, b.order_id as partner_order_id,                               " +
               "           a.description as my_description, b.description as partner_description,                   " +
               "           a.narration as my_narration, b.narration as partner_narration,                           " +
               "           a.order_date as my_order_date, b.order_date as partner_order_date,                       " +
               "           a.available_date as my_available_date, b.available_date as partner_available_date,       " +
               "           p1.photo_path as my_photo_path, p2.photo_path as partner_photo_path,                     " +
               "           a.country_id, c.country_name,                                                            " +
               "           a.order_id as my_order_id, b.order_id as partner_order_id,                               " +
               "           o.order_detail_id as partner_detail_order_id,                                            " +
               "           u.id as partner_id, u.name as partner_name, u.surname, u.profile_image,                  " +
               "           u.msisdn, b.order_type as partner_order_type, o.description as partner_description,      " +
               "           o.narration as partner_narration
     */
    private Integer categoryId;
    private String categoryName;
    private Long higherLevelSubCategoryId;
    private String higherLevelSubCategoryName;
    private Long middleLevelSubCategoryId;
    private String middleLevelSubCategoryName;
    private Long lowerLevelSubCategoryId;
    private String lowerLevelSubCategoryName;
    private String orderType;
    private Long orderId;
    private Long partnerOrderId;
    private String myDescription;
    private String partnerDescription;
    private String myNarration;
    private String partnerNarration;
    private Date myOrderDate;
    private Date partnerOrderDate;
    private Date myAvailableDate;
    private Date partnerAvailableDate;
    private String myPhotoPath;
    private String partnerPhotoPath;
    private Integer countryId;
    private String countryName;
    private Long partnerId;
    private String partnerName;
    private String partnerSurname;
    private String partnerProfileImage;
    private String partnerMobileNumber;
    private List<FileDTO> files;

    public MatchedOrderDTO() {
    }

    public MatchedOrderDTO(Integer categoryId,
                           String categoryName,
                           Long higherLevelSubCategoryId,
                           String higherLevelSubCategoryName,
                           Long middleLevelSubCategoryId,
                           String middleLevelSubCategoryName,
                           Long lowerLevelSubCategoryId,
                           String lowerLevelSubCategoryName,
                           String orderType,
                           Long orderId,
                           Long partnerOrderId,
                           String myDescription,
                           String partnerDescription,
                           String myNarration,
                           String partnerNarration,
                           Date myOrderDate,
                           Date partnerOrderDate,
                           Date myAvailableDate,
                           Date partnerAvailableDate,
                           String myPhotoPath,
                           String partnerPhotoPath,
                           Integer countryId,
                           String countryName,
                           Long partnerId,
                           String partnerName,
                           String partnerSurname,
                           String partnerProfileImage,
                           String partnerMobileNumber,
                           List<FileDTO> files ) {

        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.higherLevelSubCategoryId = higherLevelSubCategoryId;
        this.higherLevelSubCategoryName = higherLevelSubCategoryName;
        this.middleLevelSubCategoryId = middleLevelSubCategoryId;
        this.middleLevelSubCategoryName = middleLevelSubCategoryName;
        this.lowerLevelSubCategoryId = lowerLevelSubCategoryId;
        this.lowerLevelSubCategoryName = lowerLevelSubCategoryName;
        this.orderType = orderType;
        this.orderId = orderId;
        this.partnerOrderId = partnerOrderId;
        this.myDescription = myDescription;
        this.partnerDescription = partnerDescription;
        this.myNarration = myNarration;
        this.partnerNarration = partnerNarration;
        this.myOrderDate = myOrderDate;
        this.partnerOrderDate = partnerOrderDate;
        this.myAvailableDate = myAvailableDate;
        this.partnerAvailableDate = partnerAvailableDate;
        this.myPhotoPath = myPhotoPath;
        this.partnerPhotoPath = partnerPhotoPath;
        this.countryId = countryId;
        this.countryName = countryName;
        this.partnerId = partnerId;
        this.partnerName = partnerName;
        this.partnerSurname = partnerSurname;
        this.partnerProfileImage = partnerProfileImage;
        this.partnerMobileNumber = partnerMobileNumber;
        this.files = files;
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

    public Long getMiddleLevelSubCategoryId() {
        return middleLevelSubCategoryId;
    }

    public void setMiddleLevelSubCategoryId(Long middleLevelSubCategoryId) {
        this.middleLevelSubCategoryId = middleLevelSubCategoryId;
    }

    public String getMiddleLevelSubCategoryName() {
        return middleLevelSubCategoryName;
    }

    public void setMiddleLevelSubCategoryName(String middleLevelSubCategoryName) {
        this.middleLevelSubCategoryName = middleLevelSubCategoryName;
    }

    public Long getLowerLevelSubCategoryId() {
        return lowerLevelSubCategoryId;
    }

    public void setLowerLevelSubCategoryId(Long lowerLevelSubCategoryId) {
        this.lowerLevelSubCategoryId = lowerLevelSubCategoryId;
    }

    public String getLowerLevelSubCategoryName() {
        return lowerLevelSubCategoryName;
    }

    public void setLowerLevelSubCategoryName(String lowerLevelSubCategoryName) {
        this.lowerLevelSubCategoryName = lowerLevelSubCategoryName;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getPartnerOrderId() {
        return partnerOrderId;
    }

    public void setPartnerOrderId(Long partnerOrderId) {
        this.partnerOrderId = partnerOrderId;
    }

    public String getMyDescription() {
        return myDescription;
    }

    public void setMyDescription(String myDescription) {
        this.myDescription = myDescription;
    }

    public String getPartnerDescription() {
        return partnerDescription;
    }

    public void setPartnerDescription(String partnerDescription) {
        this.partnerDescription = partnerDescription;
    }

    public String getMyNarration() {
        return myNarration;
    }

    public void setMyNarration(String myNarration) {
        this.myNarration = myNarration;
    }

    public String getPartnerNarration() {
        return partnerNarration;
    }

    public void setPartnerNarration(String partnerNarration) {
        this.partnerNarration = partnerNarration;
    }

    public Date getMyOrderDate() {
        return myOrderDate;
    }

    public void setMyOrderDate(Date myOrderDate) {
        this.myOrderDate = myOrderDate;
    }

    public Date getPartnerOrderDate() {
        return partnerOrderDate;
    }

    public void setPartnerOrderDate(Date partnerOrderDate) {
        this.partnerOrderDate = partnerOrderDate;
    }

    public Date getMyAvailableDate() {
        return myAvailableDate;
    }

    public void setMyAvailableDate(Date myAvailableDate) {
        this.myAvailableDate = myAvailableDate;
    }

    public Date getPartnerAvailableDate() {
        return partnerAvailableDate;
    }

    public void setPartnerAvailableDate(Date partnerAvailableDate) {
        this.partnerAvailableDate = partnerAvailableDate;
    }

    public String getMyPhotoPath() {
        return myPhotoPath;
    }

    public void setMyPhotoPath(String myPhotoPath) {
        this.myPhotoPath = myPhotoPath;
    }

    public String getPartnerPhotoPath() {
        return partnerPhotoPath;
    }

    public void setPartnerPhotoPath(String partnerPhotoPath) {
        this.partnerPhotoPath = partnerPhotoPath;
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

    public Long getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(Long partnerId) {
        this.partnerId = partnerId;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public String getPartnerSurname() {
        return partnerSurname;
    }

    public void setPartnerSurname(String partnerSurname) {
        this.partnerSurname = partnerSurname;
    }

    public String getPartnerProfileImage() {
        return partnerProfileImage;
    }

    public void setPartnerProfileImage(String partnerProfileImage) {
        this.partnerProfileImage = partnerProfileImage;
    }

    public String getPartnerMobileNumber() {
        return partnerMobileNumber;
    }

    public void setPartnerMobileNumber(String partnerMobileNumber) {
        this.partnerMobileNumber = partnerMobileNumber;
    }

    public List<FileDTO> getFiles() {
        return files;
    }

    public void setFiles(List<FileDTO> files) {
        this.files = files;
    }

    @Override
    public String toString() {
        return "MatchedOrderDTO{" +
                "categoryId=" + categoryId +
                ", categoryName=" + categoryName +
                ", higherLevelSubCategoryId=" + higherLevelSubCategoryId +
                ", higherLevelSubCategoryName='" + higherLevelSubCategoryName + '\'' +
                ", middleLevelSubCategoryId=" + middleLevelSubCategoryId +
                ", middleLevelSubCategoryName='" + middleLevelSubCategoryName + '\'' +
                ", lowerLevelSubCategoryId=" + lowerLevelSubCategoryId +
                ", lowerLevelSubCategoryName='" + lowerLevelSubCategoryName + '\'' +
                ", orderType='" + orderType + '\'' +
                ", orderId=" + orderId +
                ", partnerOrderId=" + partnerOrderId +
                ", myDescription='" + myDescription + '\'' +
                ", partnerDescription='" + partnerDescription + '\'' +
                ", myNarration='" + myNarration + '\'' +
                ", partnerNarration='" + partnerNarration + '\'' +
                ", myOrderDate=" + myOrderDate +
                ", partnerOrderDate=" + partnerOrderDate +
                ", myAvailableDate=" + myAvailableDate +
                ", partnerAvailableDate=" + partnerAvailableDate +
                ", myPhotoPath='" + myPhotoPath + '\'' +
                ", partnerPhotoPath='" + partnerPhotoPath + '\'' +
                ", countryId=" + countryId +
                ", countryName='" + countryName + '\'' +
                ", partnerId=" + partnerId +
                ", partnerName='" + partnerName + '\'' +
                ", partnerSurname='" + partnerSurname + '\'' +
                ", partnerProfileImage='" + partnerProfileImage + '\'' +
                ", partnerMobileNumber='" + partnerMobileNumber + '\'' +
                '}';
    }
}
