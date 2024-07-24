package com.example.DiamondShopSystem.model;

import jakarta.persistence.*;

import java.util.Date;


@Entity
@Table(name = "CustomOrder")
public class CustomOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customOrderId;

    @Column(nullable = false)
    private String username;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn(name = "custom_jewelry_id", nullable = false)
    private CustomJewelry customJewelry;

    private int prepaid;
    private int fullpaid;
    private String description;
    private Date startDate;
    private Date finishDate;

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

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public CustomJewelry getCustomJewelry() {
        return customJewelry;
    }

    public void setCustomJewelry(CustomJewelry customJewelry) {
        this.customJewelry = customJewelry;
    }

    public int getPrepaid() {
        return prepaid;
    }

    public void setPrepaid(int prepaid) {
        this.prepaid = prepaid;
    }

    public int getFullpaid() {
        return fullpaid;
    }

    public void setFullpaid(int fullpaid) {
        this.fullpaid = fullpaid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}
