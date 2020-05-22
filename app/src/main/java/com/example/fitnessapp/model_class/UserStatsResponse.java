package com.example.fitnessapp.model_class;

public class UserStatsResponse {
    private boolean success;
    private int status;
    private Data data;

    public UserStatsResponse(boolean success, int status, Data data) {
        this.success = success;
        this.status = status;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public int getStatus() {
        return status;
    }

    public Data getData() {
        return data;
    }
}
