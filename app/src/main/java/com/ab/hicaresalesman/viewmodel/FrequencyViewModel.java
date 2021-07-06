package com.ab.hicaresalesman.viewmodel;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.ab.hicaresalesman.network.models.frequency.FrequencyData;
import com.ab.hicaresalesman.network.models.frequency.RecommendedFrequency;
import com.ab.hicaresalesman.network.models.pest_service.ServiceData;

import java.util.List;

/**
 * Created by Arjun Bhatt on 5/7/2021.
 */
public class FrequencyViewModel implements Parcelable {
    private int serviceId;
    private String serviceType;
    private String serviceCode;
    private int netScore;
    private int total;
    private double avgNetScore;
    private int categoryId;
    private String categoryName;
    private int recommendedFrequencyId;
    private String recommendedFrequency;
    private int agreedFrequencyId;
    private String agreedFrequency;
    private List<RecommendedFrequency> frequencyList;
    private int optionPos;


    public FrequencyViewModel() {
        this.serviceId = 0;
        this.serviceType = "NA";
        this.serviceCode = "NA";
        this.netScore = 0;
        this.total = 0;
        this.avgNetScore = 0;
        this.categoryId = 0;
        this.categoryName = "NA";
        this.recommendedFrequencyId = 0;
        this.recommendedFrequency = "NA";
        this.agreedFrequencyId = 0;
        this.agreedFrequency = "NA";
        this.optionPos = 0;

    }


    protected FrequencyViewModel(Parcel in) {
        serviceId = in.readInt();
        serviceType = in.readString();
        serviceCode = in.readString();
        netScore = in.readInt();
        total = in.readInt();
        avgNetScore = in.readDouble();
        categoryId = in.readInt();
        categoryName = in.readString();
        recommendedFrequencyId = in.readInt();
        recommendedFrequency = in.readString();
        agreedFrequencyId = in.readInt();
        agreedFrequency = in.readString();
        optionPos = in.readInt();
    }

    public static final Creator<FrequencyViewModel> CREATOR = new Creator<FrequencyViewModel>() {
        @Override
        public FrequencyViewModel createFromParcel(Parcel in) {
            return new FrequencyViewModel(in);
        }

        @Override
        public FrequencyViewModel[] newArray(int size) {
            return new FrequencyViewModel[size];
        }
    };

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
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

    public int getNetScore() {
        return netScore;
    }

    public void setNetScore(int netScore) {
        this.netScore = netScore;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public double getAvgNetScore() {
        return avgNetScore;
    }

    public void setAvgNetScore(double avgNetScore) {
        this.avgNetScore = avgNetScore;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getRecommendedFrequencyId() {
        return recommendedFrequencyId;
    }

    public void setRecommendedFrequencyId(int recommendedFrequencyId) {
        this.recommendedFrequencyId = recommendedFrequencyId;
    }

    public String getRecommendedFrequency() {
        return recommendedFrequency;
    }

    public void setRecommendedFrequency(String recommendedFrequency) {
        this.recommendedFrequency = recommendedFrequency;
    }

    public int getAgreedFrequencyId() {
        return agreedFrequencyId;
    }

    public void setAgreedFrequencyId(int agreedFrequencyId) {
        this.agreedFrequencyId = agreedFrequencyId;
    }

    public String getAgreedFrequency() {
        return agreedFrequency;
    }

    public void setAgreedFrequency(String agreedFrequency) {
        this.agreedFrequency = agreedFrequency;
    }

    public List<RecommendedFrequency> getFrequencyList() {
        return frequencyList;
    }

    public void setFrequencyList(List<RecommendedFrequency> frequencyList) {
        this.frequencyList = frequencyList;
    }


    public int getOptionPos() {
        if (optionPos == 0) {
            for (int i = 0; i < frequencyList.size(); i++) {
                if (frequencyList.get(i).getSelected()) {
                    Log.i("recommended", recommendedFrequency);
                    Log.i("recommended1", String.valueOf(frequencyList.get(i).getFrequencyName()));
//                    if (frequencyList.get(i).getFrequencyName() != null && agreedFrequency!=null && frequencyList.get(i).getFrequencyName().equals(recommendedFrequency)) {
//                        Log.i("option", String.valueOf(frequencyList.get(i).getSelected()));
                        return i + 1;
//                    }
                }
            }
        }
        Log.d("option1", frequencyList.get(optionPos).getFrequencyName());
        return optionPos;
    }

    public void setOptionPos(int optionPos) {
        this.optionPos = optionPos;
    }

    public void clone(FrequencyData data) {
        this.serviceId = data.getServiceId();
        this.serviceType = data.getServiceType();
        this.serviceCode = data.getServiceCode();
        this.netScore = data.getTotalNetScore();
        this.total = data.getTotalResponse();
        this.avgNetScore = data.getAvgNetScore();
        this.categoryId = data.getCategoryId();
        this.categoryName = data.getCategoryName();
        this.recommendedFrequencyId = data.getRecommendedFrequencyId();
        this.recommendedFrequency = data.getRecommendedFrequency();
        this.agreedFrequencyId = data.getAgreedFrequencyId();
        this.agreedFrequency = data.getAgreedFrequency();
        this.frequencyList = data.getRecommendedFrequencyList();
        this.optionPos = data.getOptionPos();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(serviceId);
        parcel.writeString(serviceType);
        parcel.writeString(serviceCode);
        parcel.writeInt(netScore);
        parcel.writeInt(total);
        parcel.writeDouble(avgNetScore);
        parcel.writeInt(categoryId);
        parcel.writeString(categoryName);
        parcel.writeInt(recommendedFrequencyId);
        parcel.writeString(recommendedFrequency);
        parcel.writeInt(agreedFrequencyId);
        parcel.writeString(agreedFrequency);
        parcel.writeInt(optionPos);
    }
}