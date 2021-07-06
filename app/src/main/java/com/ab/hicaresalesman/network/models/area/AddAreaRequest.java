package com.ab.hicaresalesman.network.models.area;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Arjun Bhatt on 5/5/2021.
 */
public class AddAreaRequest {
    @SerializedName("Activity_Id")
    @Expose
    private Integer activityId;
    @SerializedName("Subarea_Id")
    @Expose
    private Integer subareaId;
    @SerializedName("Subarea_Name")
    @Expose
    private String subareaName;
    @SerializedName("Template_Name")
    @Expose
    private String templateName;
    @SerializedName("Template_Type")
    @Expose
    private String templateType;
    @SerializedName("Tower_No")
    @Expose
    private Integer towerNo;

    @SerializedName("Tower_Name")
    @Expose
    private String towerName;
    @SerializedName("Unit")
    @Expose
    private String unit;
    @SerializedName("Floor_No")
    @Expose
    private String floorNo;
    @SerializedName("Area_Sq_Ft")
    @Expose
    private String areaSqFt;
    @SerializedName("Additional_Sq_Ft")
    @Expose
    private String additionalSqFt;
    @SerializedName("Total_Area")
    @Expose
    private String totalArea;
    @SerializedName("CreatedBy")
    @Expose
    private Integer createdBy;
    @SerializedName("ServiceActivity")
    @Expose
    private List<ServiceActivityType> serviceActivity = null;

    private boolean isEdited;

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getSubareaId() {
        return subareaId;
    }

    public void setSubareaId(Integer subareaId) {
        this.subareaId = subareaId;
    }

    public String getSubareaName() {
        return subareaName;
    }

    public void setSubareaName(String subareaName) {
        this.subareaName = subareaName;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTemplateType() {
        return templateType;
    }

    public void setTemplateType(String templateType) {
        this.templateType = templateType;
    }

    public Integer getTowerNo() {
        return towerNo;
    }

    public void setTowerNo(Integer towerNo) {
        this.towerNo = towerNo;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getFloorNo() {
        return floorNo;
    }

    public void setFloorNo(String floorNo) {
        this.floorNo = floorNo;
    }

    public String getAreaSqFt() {
        return areaSqFt;
    }

    public void setAreaSqFt(String areaSqFt) {
        this.areaSqFt = areaSqFt;
    }

    public String getAdditionalSqFt() {
        return additionalSqFt;
    }

    public void setAdditionalSqFt(String additionalSqFt) {
        this.additionalSqFt = additionalSqFt;
    }

    public String getTotalArea() {
        return totalArea;
    }

    public void setTotalArea(String totalArea) {
        this.totalArea = totalArea;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public List<ServiceActivityType> getServiceActivity() {
        return serviceActivity;
    }

    public void setServiceActivity(List<ServiceActivityType> serviceActivity) {
        this.serviceActivity = serviceActivity;
    }

    public boolean isEdited() {
        return isEdited;
    }

    public void setEdited(boolean edited) {
        isEdited = edited;
    }

    public String getTowerName() {
        return towerName;
    }

    public void setTowerName(String towerName) {
        this.towerName = towerName;
    }
}
