import React, { createContext, useState, useEffect } from 'react';
import Cookies from 'js-cookie';
import AuthService from './AuthService';
import axios from 'axios';

const CartContext = createContext();

export const CartProvider = ({ children }) => {
  const [cart, setCart] = useState([]);

  useEffect(() => {
    if (AuthService.isAuthenticated()) {
      const savedCart = Cookies.get('cart');
      if (savedCart) {
        const parsedCart = JSON.parse(savedCart).map(item => ({ ...item, quantity: 1 }));
        setCart(parsedCart);
      }
    }
  }, []);

  const addToCart = async(item) => {
    const token = localStorage.getItem("token");
    




    setCart((prevCart) => {
      const itemIndex = prevCart.findIndex((cartItem) => cartItem.jewelryId === item.jewelryId);
      if (itemIndex !== -1) {
        const updatedCart = prevCart.map((cartItem, index) => {
          if (index === itemIndex) {
            return { ...cartItem, quantity: cartItem.quantity + 1 };
          }
          return cartItem;
        });
        Cookies.set('cart', JSON.stringify(updatedCart)); 
        return updatedCart;
      } else {
        const newCart = [...prevCart, { ...item, quantity: 1 }];
        Cookies.set('cart', JSON.stringify(newCart));
        return newCart;
      }
    });
  };

  const removeFromCart = (itemId) => {
    setCart((prevCart) => {
      const updatedCart = prevCart.filter((item) => item.jewelryId !== itemId);
      Cookies.set('cart', JSON.stringify(updatedCart)); 
      return updatedCart;
    });
  };

  return (
    <CartContext.Provider value={{ cart, addToCart, removeFromCart }}>
      {children}
    </CartContext.Provider>
  );
};

export default CartContext;
