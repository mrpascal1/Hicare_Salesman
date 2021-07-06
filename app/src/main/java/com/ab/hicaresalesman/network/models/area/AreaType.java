package com.ab.hicaresalesman.network.models.area;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Arjun Bhatt on 4/28/2021.
 */
public class AreaType {
    @SerializedName("Template_Type")
    @Expose
    private String templateType;
    @SerializedName("Allow_Multiple")
    @Expose
    private Boolean allowMultiple;
    @SerializedName("Data")
    @Expose
    private List<TowerData> data = null;

    public String getTemplateType() {
        return templateType;
    }

    public void setTemplateType(String templateType) {
        this.templateType = templateType;
    }

    public Boolean getAllowMultiple() {
        return allowMultiple;
    }

    public void setAllowMultiple(Boolean allowMultiple) {
        this.allowMultiple = allowMultiple;
    }

    public List<TowerData> getData() {
        return data;
    }

    public void setData(List<TowerData> data) {
        this.data = data;
    }
}
