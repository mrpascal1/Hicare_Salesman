package com.ab.hicaresalesman.network.models.area;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Arjun Bhatt on 4/28/2021.
 */
public class TowerData {
    @SerializedName("Tower")
    @Expose
    private Integer tower;
    @SerializedName("Tower_Name")
    @Expose
    private String towerName;
    @SerializedName("Data")
    @Expose
    private List<AreaData> data = null;
    private boolean isCloned;

    public Integer getTower() {
        return tower;
    }

    public void setTower(Integer tower) {
        this.tower = tower;
    }

    public List<AreaData> getData() {
        return data;
    }

    public void setData(List<AreaData> data) {
        this.data = data;
    }

    public boolean isCloned() {
        return isCloned;
    }

    public void setCloned(boolean cloned) {
        isCloned = cloned;
    }

    public String getTowerName() {
        return towerName;
    }

    public void setTowerName(String towerName) {
        this.towerName = towerName;
    }
}
