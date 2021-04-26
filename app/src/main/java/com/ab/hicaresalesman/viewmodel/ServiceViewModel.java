package com.ab.hicaresalesman.viewmodel;

import android.os.Parcel;
import android.os.Parcelable;

import com.ab.hicaresalesman.network.models.pest_service.ServiceData;

/**
 * Created by Arjun Bhatt on 4/26/2021.
 */
public class ServiceViewModel implements Parcelable {
    private String serviceName;
    private boolean isSelected;
    private String serviceCode;

    public ServiceViewModel() {
        this.serviceName = "NA";
        this.isSelected = false;
        this.serviceCode = "NA";
    }

    protected ServiceViewModel(Parcel in) {
        serviceName = in.readString();
        isSelected = in.readByte() != 0;
        serviceCode = in.readString();
    }

    public static final Creator<ServiceViewModel> CREATOR = new Creator<ServiceViewModel>() {
        @Override
        public ServiceViewModel createFromParcel(Parcel in) {
            return new ServiceViewModel(in);
        }

        @Override
        public ServiceViewModel[] newArray(int size) {
            return new ServiceViewModel[size];
        }
    };

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(serviceName);
        parcel.writeByte((byte) (isSelected ? 1 : 0));
        parcel.writeString(serviceCode);
    }

    public void clone(ServiceData data){
        this.serviceName = data.getServiceType();
        this.isSelected = data.getIsSelected();
        this.serviceCode = data.getServiceCode();
    }
}
