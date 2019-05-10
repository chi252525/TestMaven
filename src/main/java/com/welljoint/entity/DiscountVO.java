package com.welljoint.entity;

public class DiscountVO implements java.io.Serializable {

 
    private static final long serialVersionUID = 155037908589628087L;
    private String disCode;
    private Double disRate;
    private String week;
    private String timeInterval;
    private String listSKU;
    private Boolean status;
    private String validity_period;

    public String getDisCode() {
        return disCode;
    }
    public void setDisCode(String disCode) {
        this.disCode = disCode;
    }
    public Double getDisRate() {
        return disRate;
    }
    public void setDisRate(Double disRate) {
        this.disRate = disRate;
    }
    public String getWeek() {
        return week;
    }
    public void setWeek(String week) {
        this.week = week;
    }
    public String getTimeInterval() {
        return timeInterval;
    }
    public void setTimeInterval(String timeInterval) {
        this.timeInterval = timeInterval;
    }
    public String getListSKU() {
        return listSKU;
    }
    public void setListSKU(String listSKU) {
        this.listSKU = listSKU;
    }
    public Boolean getStatus() {
        return status;
    }
    public void setStatus(Boolean status) {
        this.status = status;
    }
    public String getValidity_period() {
        return validity_period;
    }
    public void setValidity_period(String validity_period) {
        this.validity_period = validity_period;
    }

}
