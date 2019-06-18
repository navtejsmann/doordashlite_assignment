package com.navtej.doordashlite.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RestaurantDetail {
    private static  final int MAX_DESCRIPTION_ALLOWED = 100;

    @SerializedName("id")
    @Expose
    private String mId;
    @SerializedName("name")
    @Expose
    private String mName;
    @SerializedName("description")
    @Expose
    private String mDescription;
    @SerializedName("cover_img_url")
    @Expose
    public String cover_img_url;
    @SerializedName("status")
    @Expose
    private String mStatus;
    @SerializedName("header_img_url")
    @Expose
    public String mHeaderImgURL;
    @SerializedName("delivery_fee")
    @Expose
    private String mDeliveryFee;


    public void setName(String name) {
        this.mName = name;
    }

    public void setCoverURL(String cover) {
        this.cover_img_url = cover;
    }

    public void setId(String id) {
        this.mId = id;
    }

    public void setDescription(String description) {
        this.mDescription = description;
    }

    public void setStatus(String status) {
        this.mStatus = status;
    }

    public void setDeliveryFee(String deliveryFee) {
        this.mDeliveryFee = deliveryFee;
    }

    public void setHeaderImgURL(String headerImgURL) {
        this.mHeaderImgURL = headerImgURL;
    }

    public String getHeaderImgURL() {
        return mHeaderImgURL;
    }


    public String getId(){
        return mId;
    }

    public String getCoverURL() {
        return cover_img_url;
    }

    public String getName() {
        return mName;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getShortDescription() {
        if(mDescription!= null && mDescription.length() > MAX_DESCRIPTION_ALLOWED){
            StringBuffer sb = new StringBuffer();
            sb.append(mDescription.substring(0, MAX_DESCRIPTION_ALLOWED)).append("...");
            return sb.toString();
        }else{
            return mDescription;
        }
    }


    public String getStatus() {
        return mStatus;
    }

    @Override
    public String toString(){
        StringBuffer sb =  new StringBuffer();
        sb.append(mId).append(" ").append(mName).append(" ").append(mDeliveryFee).append(" ").append(mStatus).append(" ")
                .append(mDeliveryFee).append(" ").append(cover_img_url);
        return sb.toString();
    }
}
