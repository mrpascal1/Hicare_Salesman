package com.ab.hicaresalesman.network.models.opportunity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Arjun Bhatt on 4/22/2021.
 */
public class OpportunityData {
    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Opportunity_Number__c")
    @Expose
    private String opportunityNumberC;
    @SerializedName("StageName")
    @Expose
    private String stageName;
    @SerializedName("Opportunity_Amount_B2C__c")
    @Expose
    private Double opportunityAmountB2CC;
    @SerializedName("ML_Date__c")
    @Expose
    private Object mLDateC;
    @SerializedName("Comments__c")
    @Expose
    private String commentsC;
    @SerializedName("Customer_Type__c")
    @Expose
    private String customerTypeC;
    @SerializedName("Sub_Type__c")
    @Expose
    private String subTypeC;
    @SerializedName("Opportunity_Type__c")
    @Expose
    private String opportunityTypeC;
    @SerializedName("Probability")
    @Expose
    private String probability;
    @SerializedName("LeadSource")
    @Expose
    private String leadSource;
    @SerializedName("Sub_Source__c")
    @Expose
    private String subSourceC;
    @SerializedName("IndustryId")
    @Expose
    private Integer industryId;
    @SerializedName("Sub_Type_Lookup__r")
    @Expose
    private SubType subTypeLookupR;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOpportunityNumberC() {
        return opportunityNumberC;
    }

    public void setOpportunityNumberC(String opportunityNumberC) {
        this.opportunityNumberC = opportunityNumberC;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public Double getOpportunityAmountB2CC() {
        return opportunityAmountB2CC;
    }

    public void setOpportunityAmountB2CC(Double opportunityAmountB2CC) {
        this.opportunityAmountB2CC = opportunityAmountB2CC;
    }

    public Object getMLDateC() {
        return mLDateC;
    }

    public void setMLDateC(Object mLDateC) {
        this.mLDateC = mLDateC;
    }

    public String getCommentsC() {
        return commentsC;
    }

    public void setCommentsC(String commentsC) {
        this.commentsC = commentsC;
    }

    public String getCustomerTypeC() {
        return customerTypeC;
    }

    public void setCustomerTypeC(String customerTypeC) {
        this.customerTypeC = customerTypeC;
    }

    public String getSubTypeC() {
        return subTypeC;
    }

    public void setSubTypeC(String subTypeC) {
        this.subTypeC = subTypeC;
    }

    public String getOpportunityTypeC() {
        return opportunityTypeC;
    }

    public void setOpportunityTypeC(String opportunityTypeC) {
        this.opportunityTypeC = opportunityTypeC;
    }

    public String getProbability() {
        return probability;
    }

    public void setProbability(String probability) {
        this.probability = probability;
    }

    public String getLeadSource() {
        return leadSource;
    }

    public void setLeadSource(String leadSource) {
        this.leadSource = leadSource;
    }

    public String getSubSourceC() {
        return subSourceC;
    }

    public void setSubSourceC(String subSourceC) {
        this.subSourceC = subSourceC;
    }

    public Integer getIndustryId() {
        return industryId;
    }

    public void setIndustryId(Integer industryId) {
        this.industryId = industryId;
    }

    public SubType getSubTypeLookupR() {
        return subTypeLookupR;
    }

    public void setSubTypeLookupR(SubType subTypeLookupR) {
        this.subTypeLookupR = subTypeLookupR;
    }
}
