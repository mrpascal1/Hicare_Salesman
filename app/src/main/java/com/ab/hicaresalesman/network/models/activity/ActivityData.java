package com.ab.hicaresalesman.network.models.activity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Arjun Bhatt on 4/23/2021.
 */
public class ActivityData {
    @SerializedName("Activity_Id")
    @Expose
    private Integer activityId;
    @SerializedName("Opportunity_Id")
    @Expose
    private String opportunityId;
    @SerializedName("Activity_Code")
    @Expose
    private String activityCode;
    @SerializedName("Activity_Name")
    @Expose
    private String activityName;
    @SerializedName("Is_Deleted")
    @Expose
    private Boolean isDeleted;
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
    @SerializedName("Industry_Id")
    @Expose
    private Integer industryId;
    @SerializedName("Industry_Name")
    @Expose
    private String industryName;
    @SerializedName("Cost_Generated")
    @Expose
    private Boolean Cost_Generated;

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getOpportunityId() {
        return opportunityId;
    }

    public void setOpportunityId(String opportunityId) {
        this.opportunityId = opportunityId;
    }

    public String getActivityCode() {
        return activityCode;
    }

    public void setActivityCode(String activityCode) {
        this.activityCode = activityCode;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
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

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Boolean getCost_Generated() {
        return Cost_Generated;
    }

    public void setCost_Generated(Boolean cost_Generated) {
        Cost_Generated = cost_Generated;
    }
}
