package com.ab.hicaresalesman.network.models.cost_service_list;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Arjun Bhatt on 6/16/2021.
 */
public class CostData {
    @SerializedName("Service_Name")
    @Expose
    private String serviceName;
    @SerializedName("Total_Service")
    @Expose
    private Integer totalService;
    @SerializedName("Cost")
    @Expose
    private Double cost;

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

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }
}
