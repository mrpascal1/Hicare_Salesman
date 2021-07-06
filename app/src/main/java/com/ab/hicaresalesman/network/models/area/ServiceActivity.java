package com.ab.hicaresalesman.network.models.area;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Arjun Bhatt on 5/5/2021.
 */
public class ServiceActivity {
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

    public ServiceActivity() {
        this.activityId = 0;
        this.areaId = 0;
        this.activityAreaMappingId = 0;
        this.serviceId = 0;
        this.serviceType = "NA";
        this.serviceActivityId = 0;
        this.serviceActivityName = "NA";
        this.defaultSelected = false;
        this.isSelected = false;
        this.createdBy = 0;
    }

    protected ServiceActivity(Parcel in) {
        if (in.readByte() == 0) {
            activityId = null;
        } else {
            activityId = in.readInt();
        }
        if (in.readByte() == 0) {
            areaId = null;
        } else {
            areaId = in.readInt();
        }
        if (in.readByte() == 0) {
            activityAreaMappingId = null;
        } else {
            activityAreaMappingId = in.readInt();
        }
        if (in.readByte() == 0) {
            serviceId = null;
        } else {
            serviceId = in.readInt();
        }
        serviceType = in.readString();
        if (in.readByte() == 0) {
            serviceActivityId = null;
        } else {
            serviceActivityId = in.readInt();
        }
        serviceActivityName = in.readString();
        byte tmpDefaultSelected = in.readByte();
        defaultSelected = tmpDefaultSelected == 0 ? null : tmpDefaultSelected == 1;
        byte tmpIsSelected = in.readByte();
        isSelected = tmpIsSelected == 0 ? null : tmpIsSelected == 1;
        if (in.readByte() == 0) {
            createdBy = null;
        } else {
            createdBy = in.readInt();
        }
    }



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

    public Boolean getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(Boolean isSelected) {
        this.isSelected = isSelected;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

}


