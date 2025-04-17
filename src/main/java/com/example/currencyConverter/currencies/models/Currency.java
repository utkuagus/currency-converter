package com.example.currencyConverter.currencies.models;

public class Currency {

    private String code;
    private String name;
    private double usdRate;

    public Currency(String code, String name, double usdRate) {
        this.code = code;
        this.name = name;
        this.usdRate = usdRate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getUsdRate() {
        return usdRate;
    }

    public void setUsdRate(double usdRate) {
        this.usdRate = usdRate;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", usdRate=" + usdRate +
                '}';
    }

}