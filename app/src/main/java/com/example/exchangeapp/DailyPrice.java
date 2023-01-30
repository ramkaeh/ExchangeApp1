package com.example.exchangeapp;


import java.util.ArrayList;

public class DailyPrice {

    private String symbol;
    private String dailyDate;
    private double dailyOpen;
    private double dailyHigh;
    private double dailyLow;
    private double dailyClose;
    private boolean isExpanded;


    public DailyPrice(String symbol, String dailyDate, double dailyOpen, double dailyHigh, double dailyLow, double dailyClose) {
        this.symbol = symbol;
        this.dailyDate = dailyDate;
        this.dailyOpen = dailyOpen;
        this.dailyHigh = dailyHigh;
        this.dailyLow = dailyLow;
        this.dailyClose = dailyClose;
        isExpanded = false;


    }

    @Override
    public String toString() {
        return "DailyPrice{" +
                "dailyOpen=" + dailyOpen +
                '}';
    }


    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

    public String getDailyDate() {
        return dailyDate;
    }

    public void setDailyDate(String dailyDate) {
        this.dailyDate = dailyDate;
    }

    public double getDailyOpen() {
        return dailyOpen;
    }

    public void setDailyOpen(double dailyOpen) {
        this.dailyOpen = dailyOpen;
    }

    public double getDailyHigh() {
        return dailyHigh;
    }

    public void setDailyHigh(double dailyHigh) {
        this.dailyHigh = dailyHigh;
    }

    public double getDailyLow() {
        return dailyLow;
    }

    public void setDailyLow(double dailyLow) {
        this.dailyLow = dailyLow;
    }

    public double getDailyClose() {
        return dailyClose;
    }

    public void setDailyClose(double dailyClose) {
        this.dailyClose = dailyClose;
    }
}
