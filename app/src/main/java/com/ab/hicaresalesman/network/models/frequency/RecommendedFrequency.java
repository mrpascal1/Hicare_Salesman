package com.ab.hicaresalesman.network.models.frequency;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Arjun Bhatt on 5/7/2021.
 */
public class RecommendedFrequency {
    @SerializedName("Frequency_Id")
    @Expose
    private Integer frequencyId;
    @SerializedName("Frequency_Name")
    @Expose
    private String frequencyName;
    @SerializedName("IsActive")
    @Expose
    private Boolean isActive;
    @SerializedName("IsSelected")
    @Expose
    private Boolean IsSelected;
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
    private String str;
    private int spnPosition;
    private String recommendedName;

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

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public int getSpnPosition() {
        return spnPosition;
    }

    public void setSpnPosition(int spnPosition) {
        this.spnPosition = spnPosition;
    }

    public Boolean getSelected() {
        return IsSelected;
    }

    public void setSelected(Boolean selected) {
        IsSelected = selected;
    }

    public String getRecommendedName() {
        return recommendedName;
    }

    public void setRecommendedName(String recommendedName) {
        this.recommendedName = recommendedName;
    }

    @Override
    public String toString() {
        return frequencyName;
    }
}
