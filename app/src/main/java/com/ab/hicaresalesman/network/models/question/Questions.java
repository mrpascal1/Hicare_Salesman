package com.ab.hicaresalesman.network.models.question;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Arjun Bhatt on 4/27/2021.
 */
public class Questions {
    @SerializedName("Question_Id")
    @Expose
    private Integer questionId;
    @SerializedName("Service_Id")
    @Expose
    private Integer serviceId;
    @SerializedName("Industry_Id")
    @Expose
    private Integer industryId;
    @SerializedName("Service_Code")
    @Expose
    private String serviceCode;
    @SerializedName("Service_Type")
    @Expose
    private String serviceType;
    @SerializedName("Industry_Name")
    @Expose
    private String industryName;
    @SerializedName("Question_Title")
    @Expose
    private String questionTitle;
    @SerializedName("Question_Desc")
    @Expose
    private String questionDesc;
    @SerializedName("Question_Approach")
    @Expose
    private String questionApproach;
    @SerializedName("Image_Required")
    @Expose
    private Boolean imageRequired;
    @SerializedName("IsActive")
    @Expose
    private Boolean isActive;
    @SerializedName("OptionList")
    @Expose
    private List<OptionData> optionList = null;
    @SerializedName("SelectedAnswer")
    @Expose
    private Object selectedAnswer;
    @SerializedName("SelectedAnswerId")
    @Expose
    private Integer selectedAnswerId;
    @SerializedName("Picture_Url")
    @Expose
    private String PictureUrl;
    private int parentPos;

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public Integer getIndustryId() {
        return industryId;
    }

    public void setIndustryId(Integer industryId) {
        this.industryId = industryId;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getIndustryName() {
        return industryName;
    }

    public void setIndustryName(String industryName) {
        this.industryName = industryName;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public String getQuestionDesc() {
        return questionDesc;
    }

    public void setQuestionDesc(String questionDesc) {
        this.questionDesc = questionDesc;
    }

    public String getQuestionApproach() {
        return questionApproach;
    }

    public void setQuestionApproach(String questionApproach) {
        this.questionApproach = questionApproach;
    }

    public Boolean getImageRequired() {
        return imageRequired;
    }

    public void setImageRequired(Boolean imageRequired) {
        this.imageRequired = imageRequired;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public List<OptionData> getOptionList() {
        return optionList;
    }


    public void setOptionList(List<OptionData> optionList) {
        this.optionList = optionList;
    }

    public Object getSelectedAnswer() {
        return selectedAnswer;
    }

    public void setSelectedAnswer(Object selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }

    public Integer getSelectedAnswerId() {
        return selectedAnswerId;
    }

    public void setSelectedAnswerId(Integer selectedAnswerId) {
        this.selectedAnswerId = selectedAnswerId;
    }

    public String getPictureUrl() {
        return PictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        PictureUrl = pictureUrl;
    }

    public int getParentPos() {

        if (parentPos == 0) {
            for (int i = 0; i < optionList.size(); i++) {
                if (optionList.get(i).getIsSelected()) {
                    if (optionList.get(i).getOptionTitle() != null && optionList.get(i).getOptionTitle().equals(selectedAnswer)) {
                        return i + 1;
                    }
                }
            }
        }

        return parentPos;
    }

    public void setParentPos(int parentPos) {
        this.parentPos = parentPos;
    }
}
