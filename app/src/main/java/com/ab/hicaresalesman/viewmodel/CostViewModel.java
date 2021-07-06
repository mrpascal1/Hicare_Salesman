package com.ab.hicaresalesman.viewmodel;

import android.os.Parcel;
import android.os.Parcelable;

import com.ab.hicaresalesman.network.models.cost_service_list.CostData;

/**
 * Created by Arjun Bhatt on 6/17/2021.
 */
public class CostViewModel implements Parcelable {
    private String serviceName;
    private Integer totalService;
    private Double serviceCost;

    public CostViewModel() {
        this.serviceName = "";
        this.totalService = 0;
        this.serviceCost = 0.0;
    }

    protected CostViewModel(Parcel in) {
        serviceName = in.readString();
        if (in.readByte() == 0) {
            totalService = null;
        } else {
            totalService = in.readInt();
        }
        if (in.readByte() == 0) {
            serviceCost = null;
        } else {
            serviceCost = in.readDouble();
        }
    }

    public static final Creator<CostViewModel> CREATOR = new Creator<CostViewModel>() {
        @Override
        public CostViewModel createFromParcel(Parcel in) {
            return new CostViewModel(in);
        }

        @Override
        public CostViewModel[] newArray(int size) {
            return new CostViewModel[size];
        }
    };

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Integer getTotalService() {
        return totalService;
    }

    public void setTotalService(Integer totalService) {
        this.totalService = totalService;
    }

    public Double getServiceCost() {
        return serviceCost;
    }

    public void setServiceCost(Double serviceCost) {
        this.serviceCost = serviceCost;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(serviceName);
        if (totalService == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(totalService);
        }
        if (serviceCost == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(serviceCost);
        }
    }

    public void clone(CostData data){
        this.setServiceName(data.getServiceName());
        this.setServiceCost(data.getCost());
        this.setTotalService(data.getTotalService());
    }

}
