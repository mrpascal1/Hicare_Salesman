package com.ab.hicaresalesman.network.models.frequency;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Arjun Bhatt on 5/7/2021.
 */
public class FrequencyData {
    @SerializedName("ServiceId")
    @Expose
    private Integer serviceId;
    @SerializedName("ServiceType")
    @Expose
    private String serviceType;
    @SerializedName("ServiceCode")
    @Expose
    private String serviceCode;
    @SerializedName("Total_NetScore")
    @Expose
    private Integer totalNetScore;
    @SerializedName("TotalResponse")
    @Expose
    private Integer totalResponse;
    @SerializedName("Avg_NetScore")
    @Expose
    private Double avgNetScore;
    @SerializedName("CategoryId")
    @Expose
    private Integer categoryId;
    @SerializedName("CategoryName")
    @Expose
    private String categoryName;
    @SerializedName("RecommendedFrequencyId")
    @Expose
    private Integer recommendedFrequencyId;
    @SerializedName("RecommendedFrequency")
    @Expose
    private String recommendedFrequency;
    @SerializedName("AgreedFrequency")
    @Expose
    private String agreedFrequency;
    @SerializedName("AgreedFrequencyId")
    @Expose
    private Integer agreedFrequencyId;
    @SerializedName("RecommendedFrequencyList")
    @Expose
    private List<RecommendedFrequency> recommendedFrequencyList = null;

    private int optionPos;

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

    public Integer getTotalNetScore() {
        return totalNetScore;
    }

    public void setTotalNetScore(Integer totalNetScore) {
        this.totalNetScore = totalNetScore;
    }

    public Integer getTotalResponse() {
        return totalResponse;
    }

    public void setTotalResponse(Integer totalResponse) {
        this.totalResponse = totalResponse;
    }

    public Double getAvgNetScore() {
        return avgNetScore;
    }

    public void setAvgNetScore(Double avgNetScore) {
        this.avgNetScore = avgNetScore;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getRecommendedFrequencyId() {
        return recommendedFrequencyId;
    }

    public void setRecommendedFrequencyId(Integer recommendedFrequencyId) {
        this.recommendedFrequencyId = recommendedFrequencyId;
    }

    public String getRecommendedFrequency() {
        return recommendedFrequency;
    }

    public void setRecommendedFrequency(String recommendedFrequency) {
        this.recommendedFrequency = recommendedFrequency;
    }

    public String getAgreedFrequency() {
        return agreedFrequency;
    }

    public void setAgreedFrequency(String agreedFrequency) {
        this.agreedFrequency = agreedFrequency;
    }

    public Integer getAgreedFrequencyId() {
        return agreedFrequencyId;
    }

    public void setAgreedFrequencyId(Integer agreedFrequencyId) {
        this.agreedFrequencyId = agreedFrequencyId;
    }

    public List<RecommendedFrequency> getRecommendedFrequencyList() {
        return recommendedFrequencyList;
    }

    public void setRecommendedFrequencyList(List<RecommendedFrequency> recommendedFrequencyList) {
        this.recommendedFrequencyList = recommendedFrequencyList;
    }

    public int getOptionPos() {
        return optionPos;
    }

    public void setOptionPos(int optionPos) {
        this.optionPos = optionPos;
    }
}
