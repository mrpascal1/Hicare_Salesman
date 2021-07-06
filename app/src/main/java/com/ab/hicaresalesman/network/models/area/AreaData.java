package com.ab.hicaresalesman.network.models.area;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Arjun Bhatt on 4/28/2021.
 */
public class AreaData {
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
    @SerializedName("Allow_Multiple")
    @Expose
    private Boolean allowMultiple;
    @SerializedName("Industry_Id")
    @Expose
    private Integer industryId;
    @SerializedName("Industry_Name")
    @Expose
    private String industryName;
    @SerializedName("Area_Mapping_Id")
    @Expose
    private Integer areaMappingId;
    @SerializedName("Tower_No")
    @Expose
    private Integer towerNo;
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
    @SerializedName("ServiceActivity")
    @Expose
    private List<ServiceActivityType> serviceActivity = null;
    @SerializedName("Floor_List")
    @Expose
    private List<String> floorList = null;
    @SerializedName("UniqueValue")
    @Expose
    private int UniqueValue;
    private int floorPos;
    private boolean isCloned;




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

    public Boolean getAllowMultiple() {
        return allowMultiple;
    }

    public void setAllowMultiple(Boolean allowMultiple) {
        this.allowMultiple = allowMultiple;
    }

    public Integer getIndustryId() {
        return industryId;
    }

    public void setIndustryId(Integer industryId) {
        this.industryId = industryId;
    }

    public String getIndustryName() {
        return industryName;
    }

    public void setIndustryName(String industryName) {
        this.industryName = industryName;
    }

    public Integer getAreaMappingId() {
        return areaMappingId;
    }

    public void setAreaMappingId(Integer areaMappingId) {
        this.areaMappingId = areaMappingId;
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

    public List<ServiceActivityType> getServiceActivity() {
        return serviceActivity;
    }

    public void setServiceActivity(List<ServiceActivityType> serviceActivity) {
        this.serviceActivity = serviceActivity;
    }

    public int getFloorPos() {
        return floorPos;
    }

    public void setFloorPos(int floorPos) {
        this.floorPos = floorPos;
    }

    public boolean isCloned() {
        return isCloned;
    }

    public void setCloned(boolean cloned) {
        isCloned = cloned;
    }


    public List<String> getFloorList() {
        return floorList;
    }

    public void setFloorList(List<String> floorList) {
        this.floorList = floorList;
    }

    public String getFloorNo() {
        return floorNo;
    }

    public void setFloorNo(String floorNo) {
        this.floorNo = floorNo;
    }

    public int getUniqueValue() {
        return UniqueValue;
    }

    public void setUniqueValue(int uniqueValue) {
        UniqueValue = uniqueValue;
    }
}
