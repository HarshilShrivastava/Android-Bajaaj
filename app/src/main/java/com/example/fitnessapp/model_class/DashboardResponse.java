package com.example.fitnessapp.model_class;

import com.google.gson.annotations.SerializedName;

public class DashboardResponse {
    private String success;
    private int status;
    @SerializedName( "data" )
    private Statistics statistics;

    public DashboardResponse(String success, int status, Statistics statistics) {
        this.success = success;
        this.status = status;
        this.statistics = statistics;
    }

    public String getSuccess() {
        return success;
    }

    public int getStatus() {
        return status;
    }

    public Statistics getStatistics() {
        return statistics;
    }
}
