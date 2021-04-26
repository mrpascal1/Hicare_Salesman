package com.ab.hicaresalesman.network.models.pest_service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Arjun Bhatt on 4/26/2021.
 */
public class ServiceData {
    @SerializedName("Service_Id")
    @Expose
    private Integer serviceId;
    @SerializedName("Service_Type")
    @Expose
    private String serviceType;
    @SerializedName("Service_Code")
    @Expose
    private String serviceCode;
    @SerializedName("IsActive")
    @Expose
    private Boolean isActive;
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
    @SerializedName("Travel_Time")
    @Expose
    private Object travelTime;
    @SerializedName("Inspection_Time")
    @Expose
    private Object inspectionTime;
    @SerializedName("IsSelected")
    @Expose
    private Boolean isSelected;

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

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
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

    public Object getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(Object travelTime) {
        this.travelTime = travelTime;
    }

    public Object getInspectionTime() {
        return inspectionTime;
    }

    public void setInspectionTime(Object inspectionTime) {
        this.inspectionTime = inspectionTime;
    }

    public Boolean getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(Boolean isSelected) {
        this.isSelected = isSelected;
    }

}
