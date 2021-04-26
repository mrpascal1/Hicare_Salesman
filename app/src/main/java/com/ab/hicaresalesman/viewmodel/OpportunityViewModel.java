package com.ab.hicaresalesman.viewmodel;

import android.os.Parcel;
import android.os.Parcelable;

import com.ab.hicaresalesman.network.models.opportunity.OpportunityData;
import com.ab.hicaresalesman.network.models.opportunity.SubType;

/**
 * Created by Arjun Bhatt on 4/22/2021.
 */
public class OpportunityViewModel implements Parcelable {
    private String id;
    private String name;
    private String opportunityNo;
    private String stageName;
    private Double opportunityAmount;
    private String comment;
    private String customerType;
    private String customerSubType;
    private String opportunityType;
    private String probablity;
    private String leadSource;
    private String subSource;
    private Integer industryId;
    private SubType subType_R;

    public OpportunityViewModel() {
        this.id = "NA";
        this.name = "NA";
        this.opportunityNo = "NA";
        this.stageName = "NA";
        this.opportunityAmount = 0.0;
        this.comment = "NA";
        this.customerType = "NA";
        this.customerSubType = "NA";
        this.opportunityType = "NA";
        this.probablity = "NA";
        this.leadSource = "NA";
        this.subSource = "NA";
        this.industryId = 0;
    }

    protected OpportunityViewModel(Parcel in) {
        id = in.readString();
        name = in.readString();
        opportunityNo = in.readString();
        stageName = in.readString();
        if (in.readByte() == 0) {
            opportunityAmount = null;
        } else {
            opportunityAmount = in.readDouble();
        }
        comment = in.readString();
        customerType = in.readString();
        customerSubType = in.readString();
        opportunityType = in.readString();
        probablity = in.readString();
        leadSource = in.readString();
        subSource = in.readString();
        if (in.readByte() == 0) {
            industryId = null;
        } else {
            industryId = in.readInt();
        }
    }

    public static final Creator<OpportunityViewModel> CREATOR = new Creator<OpportunityViewModel>() {
        @Override
        public OpportunityViewModel createFromParcel(Parcel in) {
            return new OpportunityViewModel(in);
        }

        @Override
        public OpportunityViewModel[] newArray(int size) {
            return new OpportunityViewModel[size];
        }
    };

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

    public String getOpportunityNo() {
        return opportunityNo;
    }

    public void setOpportunityNo(String opportunityNo) {
        this.opportunityNo = opportunityNo;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public Double getOpportunityAmount() {
        return opportunityAmount;
    }

    public void setOpportunityAmount(Double opportunityAmount) {
        this.opportunityAmount = opportunityAmount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getCustomerSubType() {
        return customerSubType;
    }

    public void setCustomerSubType(String customerSubType) {
        this.customerSubType = customerSubType;
    }

    public String getOpportunityType() {
        return opportunityType;
    }

    public void setOpportunityType(String opportunityType) {
        this.opportunityType = opportunityType;
    }

    public String getProbablity() {
        return probablity;
    }

    public void setProbablity(String probablity) {
        this.probablity = probablity;
    }

    public String getLeadSource() {
        return leadSource;
    }

    public void setLeadSource(String leadSource) {
        this.leadSource = leadSource;
    }

    public String getSubSource() {
        return subSource;
    }

    public void setSubSource(String subSource) {
        this.subSource = subSource;
    }

    public Integer getIndustryId() {
        return industryId;
    }

    public void setIndustryId(Integer industryId) {
        this.industryId = industryId;
    }

    public SubType getSubType_R() {
        return subType_R;
    }

    public void setSubType_R(SubType subType_R) {
        this.subType_R = subType_R;
    }

    public void clone(OpportunityData opportunityData) {
        this.id = opportunityData.getId();
        this.name = opportunityData.getName();
        this.opportunityNo = opportunityData.getOpportunityNumberC();
        this.stageName = opportunityData.getStageName();
        this.opportunityAmount = opportunityData.getOpportunityAmountB2CC();
        this.comment = opportunityData.getCommentsC();
        this.customerType = opportunityData.getCustomerTypeC();
        this.customerSubType = opportunityData.getSubTypeC();
        this.opportunityType = opportunityData.getOpportunityTypeC();
        this.probablity = opportunityData.getProbability();
        this.leadSource = opportunityData.getLeadSource();
        this.subSource = opportunityData.getSubSourceC();
        this.industryId = opportunityData.getIndustryId();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeString(opportunityNo);
        parcel.writeString(stageName);
        if (opportunityAmount == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(opportunityAmount);
        }
        parcel.writeString(comment);
        parcel.writeString(customerType);
        parcel.writeString(customerSubType);
        parcel.writeString(opportunityType);
        parcel.writeString(probablity);
        parcel.writeString(leadSource);
        parcel.writeString(subSource);
        if (industryId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(industryId);
        }
    }
}
