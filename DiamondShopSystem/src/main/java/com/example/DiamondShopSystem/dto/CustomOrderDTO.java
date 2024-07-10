package com.example.DiamondShopSystem.dto;

import java.util.Date;

public class CustomOrderDTO {
    private Long customOrderId;
    private String username;
    private Date startDate;
    private Date finishDate;
    private String orderStatus;
    private double prepaid;
    private double fullpaid;

    // Getters and Setters


    public CustomOrderDTO(Long customOrderId, String username, Date startDate, Date finishDate, String orderStatus, double prepaid, double fullpaid) {
        this.customOrderId = customOrderId;
        this.username = username;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.orderStatus = orderStatus;
        this.prepaid = prepaid;
        this.fullpaid = fullpaid;
    }

    public Long getCustomOrderId() {
        return customOrderId;
    }

    public void setCustomOrderId(Long customOrderId) {
        this.customOrderId = customOrderId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public double getPrepaid() {
        return prepaid;
    }

    public void setPrepaid(double prepaid) {
        this.prepaid = prepaid;
    }

    public double getFullpaid() {
        return fullpaid;
    }

    public void setFullpaid(double fullpaid) {
        this.fullpaid = fullpaid;
    }
}
