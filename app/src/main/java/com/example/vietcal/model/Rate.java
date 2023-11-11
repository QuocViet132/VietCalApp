package com.example.vietcal.model;

import com.google.gson.annotations.SerializedName;

public class Rate {
    @SerializedName("VND")
    private float vnd;
    @SerializedName("USD")
    private float usd;

    public float getVnd() {
        return vnd;
    }

    public void setVnd(float vnd) {
        this.vnd = vnd;
    }

    public float getUsd() {
        return usd;
    }

    public void setUsd(float usd) {
        this.usd = usd;
    }
}
