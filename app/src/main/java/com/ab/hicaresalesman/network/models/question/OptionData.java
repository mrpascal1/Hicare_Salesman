package com.ab.hicaresalesman.network.models.question;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Arjun Bhatt on 4/27/2021.
 */
public class OptionData {
    @SerializedName("Option_Id")
    @Expose
    private Integer optionId;
    @SerializedName("Question_Id")
    @Expose
    private Integer questionId;
    @SerializedName("Category_Id")
    @Expose
    private Integer categoryId;
    @SerializedName("Option_Title")
    @Expose
    private String optionTitle;
    @SerializedName("IsActive")
    @Expose
    private Boolean isActive;
    @SerializedName("Category_Name")
    @Expose
    private String categoryName;
    @SerializedName("Net_Score")
    @Expose
    private Integer netScore;
    @SerializedName("IsSelected")
    @Expose
    private Boolean isSelected;
    private String str;
    private int spnPosition;

    public Integer getOptionId() {
        return optionId;
    }

    public void setOptionId(Integer optionId) {
        this.optionId = optionId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getOptionTitle() {
        return optionTitle;
    }

    public void setOptionTitle(String optionTitle) {
        this.optionTitle = optionTitle;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getNetScore() {
        return netScore;
    }

    public void setNetScore(Integer netScore) {
        this.netScore = netScore;
    }

    public Boolean getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(Boolean isSelected) {
        this.isSelected = isSelected;
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

    @Override
    public String toString() {
        return optionTitle;
    }
}
