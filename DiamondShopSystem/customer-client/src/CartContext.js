import React, { createContext, useState, useContext, useEffect } from 'react';
// import AuthService from './AuthService';  // Ensure AuthService is correctly imported

const CartContext = createContext();

export const useCart = () => useContext(CartContext);

export const CartProvider = ({ children }) => {
    const [cart, setCart] = useState([]);
    const [isLoading, setIsLoading] = useState(false);

    const fetchCart = async () => {
        console.log("Cart is fetching!!!");

    };


    useEffect(() => {
        fetchCart();
    }, []);

    const value = {
        cart,
        // addToCart,
        isLoading,
        fetchCart  // Exposing fetchCart if needed elsewhere
    };

    return (
        <CartContext.Provider value={value}>
            {children}
        </CartContext.Provider>
    );
};
