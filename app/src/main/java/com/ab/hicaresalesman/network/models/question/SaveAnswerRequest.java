package com.ab.hicaresalesman.network.models.question;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Arjun Bhatt on 5/3/2021.
 */
public class SaveAnswerRequest {
    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("Activity_Id")
    @Expose
    private Integer activityId;
    @SerializedName("Question_Id")
    @Expose
    private Integer questionId;
    @SerializedName("Service_Name")
    @Expose
    private String serviceName;
    @SerializedName("Answer_Text")
    @Expose
    private String answerText;
    @SerializedName("Risk_Level")
    @Expose
    private String riskLevel;
    @SerializedName("Net_Score")
    @Expose
    private Integer netScore;
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
    @SerializedName("Picture_Url")
    @Expose
    private String Picture_Url;

    private boolean isImageRequired;

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

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public Integer getNetScore() {
        return netScore;
    }

    public void setNetScore(Integer netScore) {
        this.netScore = netScore;
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

    public String getPicture_Url() {
        return Picture_Url;
    }

    public void setPicture_Url(String picture_Url) {
        Picture_Url = picture_Url;
    }

    public boolean isImageRequired() {
        return isImageRequired;
    }

    public void setImageRequired(boolean imageRequired) {
        isImageRequired = imageRequired;
    }
}
