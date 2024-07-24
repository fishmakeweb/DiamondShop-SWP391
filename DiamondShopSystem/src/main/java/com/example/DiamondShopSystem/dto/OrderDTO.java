package com.example.DiamondShopSystem.dto;

import com.example.DiamondShopSystem.model.Order;
import com.example.DiamondShopSystem.model.OrderDetail;

import java.util.List;

public class OrderDTO {
    private List<OrderDetail> listOrderDetail;
    private int totalPrice;

    public OrderDTO(List<OrderDetail> listOrderDetail, int totalPrice) {
        this.listOrderDetail = listOrderDetail;
        this.totalPrice = totalPrice;
    }

    public List<OrderDetail> getListOrderDetail() {
        return listOrderDetail;
    }

    public void setListOrderDetail(List<OrderDetail> listOrderDetail) {
        this.listOrderDetail = listOrderDetail;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
