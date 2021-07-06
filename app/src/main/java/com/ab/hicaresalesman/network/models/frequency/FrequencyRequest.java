package com.ab.hicaresalesman.network.models.frequency;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Arjun Bhatt on 5/10/2021.
 */
public class FrequencyRequest {
    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("Activity_Id")
    @Expose
    private Integer activityId;
    @SerializedName("Service_Id")
    @Expose
    private Integer serviceId;
    @SerializedName("Frequency_Id")
    @Expose
    private Integer frequencyId;
    @SerializedName("Frequency_Name")
    @Expose
    private String frequencyName;
    @SerializedName("Created_On")
    @Expose
    private String createdOn;
    @SerializedName("Created_By_Id_User")
    @Expose
    private Integer createdByIdUser;
    @SerializedName("Modified_On")
    @Expose
    private String modifiedOn;
    @SerializedName("Modified_By_Id_User")
    @Expose
    private Integer modifiedByIdUser;
    @SerializedName("CategoryId")
    @Expose
    private Integer CategoryId;
    @SerializedName("CategoryName")
    @Expose
    private String CategoryName;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public Integer getFrequencyId() {
        return frequencyId;
    }

    public void setFrequencyId(Integer frequencyId) {
        this.frequencyId = frequencyId;
    }

    public String getFrequencyName() {
        return frequencyName;
    }

    public void setFrequencyName(String frequencyName) {
        this.frequencyName = frequencyName;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public Integer getCreatedByIdUser() {
        return createdByIdUser;
    }

    public void setCreatedByIdUser(Integer createdByIdUser) {
        this.createdByIdUser = createdByIdUser;
    }

    public String getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(String modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public Integer getModifiedByIdUser() {
        return modifiedByIdUser;
    }

    public void setModifiedByIdUser(Integer modifiedByIdUser) {
        this.modifiedByIdUser = modifiedByIdUser;
    }

    public Integer getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(Integer categoryId) {
        CategoryId = categoryId;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }
}
