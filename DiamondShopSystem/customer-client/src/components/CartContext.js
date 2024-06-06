import React, { createContext, useState, useEffect } from 'react';
import Cookies from 'js-cookie';

const CartContext = createContext();

export const CartProvider = ({ children }) => {
  const [cart, setCart] = useState([]);

  // Load cart data from cookies on component mount
  useEffect(() => {
    const savedCart = Cookies.get('cart');
    if (savedCart) {
      setCart(JSON.parse(savedCart));
    }
  }, []);

  const addToCart = (item) => {
    setCart((prevCart) => {
      const itemIndex = prevCart.findIndex((cartItem) => cartItem.jewelryId === item.jewelryId);
      if (itemIndex !== -1) {
        // Item already exists in cart
        return prevCart;
      } else {
        // Add new item to cart
        console.log(item.jewelryId);
        const newCart = [...prevCart, {...item}];
        Cookies.set('cart', JSON.stringify(newCart)); // Convert newCart to string before storing
        return newCart;
      }
    });
  };

  const removeFromCart = (itemId) => {
    setCart((prevCart) => {
      const updatedCart = prevCart.filter((item) => item.jewelryId !== itemId);
      Cookies.set('cart', JSON.stringify(updatedCart)); // Convert updatedCart to string before storing
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
