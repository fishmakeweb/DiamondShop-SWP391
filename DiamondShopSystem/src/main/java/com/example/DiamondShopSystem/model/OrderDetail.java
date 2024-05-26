package com.example.DiamondShopSystem.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "OrderDetail")
@IdClass(OrderDetail.OrderDetailId.class)
public class OrderDetail {
    @Id
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Id
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    // Getters and Setters
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    // Composite key class
    public static class OrderDetailId implements Serializable {
        private Long product;
        private Long order;

        public OrderDetailId() {
        }
        public OrderDetailId(Long product, Long order) {
            this.product = product;
            this.order = order;
        }
        public Long getProduct() {
            return product;
        }
        public void setProduct(Long product) {
            this.product = product;
        }
        public Long getOrder() {
            return order;
        }
        public void setOrder(Long order) {
            this.order = order;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            OrderDetailId that = (OrderDetailId) o;
            return Objects.equals(product, that.product) && Objects.equals(order, that.order);
        }

        @Override
        public int hashCode() {
            return Objects.hash(product, order);
        }
    }
}
