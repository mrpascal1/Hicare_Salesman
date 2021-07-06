package com.ab.hicaresalesman.network.models.question;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Arjun Bhatt on 4/27/2021.
 */
public class QuestionData {
    @SerializedName("Service_Type")
    @Expose
    private String serviceType;
    @SerializedName("Questions")
    @Expose
    private List<Questions> questions = null;

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public List<Questions> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Questions> questions) {
        this.questions = questions;
    }
}
