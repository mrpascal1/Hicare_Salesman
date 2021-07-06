package com.ab.hicaresalesman.network.models.pest_service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Arjun Bhatt on 5/1/2021.
 */
public class AddServiceRequest {
    @SerializedName("ActivityId")
    @Expose
    private Integer activityId;
    @SerializedName("ServiceId")
    @Expose
    private Integer serviceId;
    @SerializedName("ServiceName")
    @Expose
    private String serviceName;
    @SerializedName("ServiceCode")
    @Expose
    private String serviceCode;
    @SerializedName("CreatedBy")
    @Expose
    private Integer createdBy;
    private boolean isSelected;

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

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public boolean getIsSelected() {
        return isSelected;
    }
    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }
}
