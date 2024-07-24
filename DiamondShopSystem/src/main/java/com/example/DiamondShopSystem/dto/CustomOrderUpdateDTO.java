package com.example.DiamondShopSystem.dto;


import java.util.Date;

public class CustomOrderUpdateDTO {
    private int fullPaid;
    private Date finishDate;
    private String description;

    public CustomOrderUpdateDTO(int fullPaid) {
        this.fullPaid = fullPaid;
    }

    public CustomOrderUpdateDTO(int fullPaid, Date finishDate, String description) {
        this.fullPaid = fullPaid;
        this.finishDate = finishDate;
        this.description = description;
    }

    public int getFullPaid() {
        return fullPaid;
    }

    public void setFullPaid(int fullPaid) {
        this.fullPaid = fullPaid;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
