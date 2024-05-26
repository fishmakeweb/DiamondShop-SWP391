package com.example.DiamondShopSystem.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Cart_item")
@IdClass(CartItem.CartItemId.class)
public class CartItem {
    @Id
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @Id
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public static class CartItemId implements Serializable {
        private Long cart;
        private Long product;

        // Default constructor
        public CartItemId() {
        }

        // Constructor
        public CartItemId(Long cart, Long product) {
            this.cart = cart;
            this.product = product;
        }

        // Getters and Setters
        public Long getCart() {
            return cart;
        }

        public void setCart(Long cart) {
            this.cart = cart;
        }

        public Long getProduct() {
            return product;
        }

        public void setProduct(Long product) {
            this.product = product;
        }

        // hashCode and equals implementations
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CartItemId that = (CartItemId) o;
            return Objects.equals(cart, that.cart) &&
                    Objects.equals(product, that.product);
        }

        @Override
        public int hashCode() {
            return Objects.hash(cart, product);
        }
    }
}
