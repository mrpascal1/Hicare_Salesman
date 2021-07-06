package com.ab.hicaresalesman.network.models.area;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Arjun Bhatt on 5/6/2021.
 */
public class ServiceActivityRequest {
    @SerializedName("Activity_Id")
    @Expose
    private Integer activityId;
    @SerializedName("Area_Id")
    @Expose
    private Integer areaId;
    @SerializedName("Activity_Area_Mapping_Id")
    @Expose
    private Integer activityAreaMappingId;
    @SerializedName("Service_Id")
    @Expose
    private Integer serviceId;
    @SerializedName("Service_Type")
    @Expose
    private String serviceType;
    @SerializedName("Service_Activity_Id")
    @Expose
    private Integer serviceActivityId;
    @SerializedName("Service_Activity_Name")
    @Expose
    private String serviceActivityName;
    @SerializedName("Default_Selected")
    @Expose
    private Boolean defaultSelected;
    @SerializedName("Is_Selected")
    @Expose
    private Boolean isSelected;
    @SerializedName("CreatedBy")
    @Expose
    private Integer createdBy;

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Integer getActivityAreaMappingId() {
        return activityAreaMappingId;
    }

    public void setActivityAreaMappingId(Integer activityAreaMappingId) {
        this.activityAreaMappingId = activityAreaMappingId;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public Integer getServiceActivityId() {
        return serviceActivityId;
    }

    public void setServiceActivityId(Integer serviceActivityId) {
        this.serviceActivityId = serviceActivityId;
    }

    public String getServiceActivityName() {
        return serviceActivityName;
    }

    public void setServiceActivityName(String serviceActivityName) {
        this.serviceActivityName = serviceActivityName;
    }

    public Boolean getDefaultSelected() {
        return defaultSelected;
    }

    public void setDefaultSelected(Boolean defaultSelected) {
        this.defaultSelected = defaultSelected;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }
}
