package com.ab.hicaresalesman.network.models.area;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Arjun Bhatt on 5/17/2021.
 */
public class ServiceActivityType implements Parcelable {
    @SerializedName("ServiceType")
    @Expose
    private String serviceType;
    @SerializedName("ServiceActivity")
    @Expose
    private List<ServiceActivity> serviceList;

    public ServiceActivityType() {
        this.serviceType = "NA";
    }


    protected ServiceActivityType(Parcel in) {
        serviceType = in.readString();
    }

    public static final Creator<ServiceActivityType> CREATOR = new Creator<ServiceActivityType>() {
        @Override
        public ServiceActivityType createFromParcel(Parcel in) {
            return new ServiceActivityType(in);
        }

        @Override
        public ServiceActivityType[] newArray(int size) {
            return new ServiceActivityType[size];
        }
    };

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public List<ServiceActivity> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<ServiceActivity> serviceList) {
        this.serviceList = serviceList;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(serviceType);
    }
}
