import React, { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import AuthService from "./AuthService";

function Cart({ isOpen, onClose }) {
  const [itemDetails, setItemDetails] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    if (isOpen) {
      fetchCarts();
    }
  }, [isOpen]);

  const updateQuantity = async (orderDetailId, newQuantity) => {
    const token = localStorage.getItem('token');
    try {
      await axios.post(`http://localhost:8080/api/order_details/updateQuantity`, null, {
        params: { orderDetailId, quantity: newQuantity },
        headers: { Authorization: `Bearer ${token}` }
      });
      fetchCarts();
    } catch (error) {
      console.error('Error updating quantity:', error);
    }
  };

  const incrementQuantity = (orderDetailId, currentQuantity) => {
    updateQuantity(orderDetailId, currentQuantity + 1);
  };

  const decrementQuantity = (orderDetailId, currentQuantity) => {
    updateQuantity(orderDetailId, currentQuantity - 1);
  };

  const removeFromCart = (orderDetailId) => {
    updateQuantity(orderDetailId, 0);
  };

  const fetchCarts = async () => {
    try {
      const token = localStorage.getItem('token');
      const data = await AuthService.getCart(token);
      setItemDetails(data.map(item => ({
        ...item.product.jewelry,
        orderDetailId: item.id,
        quantity: item.quantity,
      })));
    } catch (error) {
      console.error("Error fetching users data:", error);
    }
  };

  const getTotalAmount = () => {
    return itemDetails.reduce((acc, item) => acc + (item.price * item.quantity), 0);
  };

  if (!isOpen) return null;

  const handleCheckout = () => {
    const token = localStorage.getItem('token');
    navigate('/confirmOrder', { state: { itemDetails, totalAmount: getTotalAmount(), token } });
  };

  return (
    <div className="fixed top-0 left-0 bg-black bg-opacity-80 flex justify-end h-full w-full">
      <div className="bg-white p-5 rounded-lg shadow-lg h-full w-1/3 max-w-full overflow-auto mt-0 flex flex-col justify-between">
        <div>
          <div className="flex justify-between items-center border-b border-stone-400 pb-4 mb-4">
            <div className="flex items-center gap-4">
              <img
                loading="lazy"
                src="https://cdn.builder.io/api/v1/image/assets/TEMP/150fb56034a3aa099ee6f17f6e64c0bb5ece850ceb913d835e458f686fd2f259?"
                alt="Cart Logo"
                className="w-8 h-8"
              />
              <h2 className="text-2xl font-semibold">In My Bag</h2>
            </div>
            <img
              loading="lazy"
              src="https://cdn.builder.io/api/v1/image/assets/TEMP/bfc96218bf522623257aa77aa9fc8238e2e164baa393e0f71d35ec4a2f1ef1fb?"
              alt="Close"
              className="w-6 h-6 cursor-pointer"
              onClick={onClose}
            />
          </div>
          <div className="flex flex-col justify-center items-center h-full">
            {itemDetails.length === 0 ? (
              <p className="text-xl text-center font-medium text-neutral-600">
                Your cart is currently empty.
              </p>
            ) : (
              <ul className="divide-y divide-gray-200 w-full">
                {itemDetails.map((item) => (
                  <li key={item.orderDetailId} className="py-4 flex items-center">
                    <img
                      loading="lazy"
                      src={item.img}
                      alt={item.name}
                      className="w-24 h-24 rounded-md object-cover"
                    />
                    <div className="ml-4 flex-grow">
                      <p className="text-lg font-medium text-gray-900">{item.name}</p>
                      <p className="text-sm text-gray-500">Price: ${item.price.toFixed(2)}</p>
                      <div className="flex items-center">
                        <button className="text-sm text-gray-600 px-3 py-1 bg-gray-200 rounded-md"
                          onClick={() => decrementQuantity(item.orderDetailId, item.quantity)}>-</button>
                        <p className="text-sm text-gray-500 mx-2">{item.quantity}</p>
                        <button className="text-sm text-gray-600 px-3 py-1 bg-gray-200 rounded-md"
                          onClick={() => incrementQuantity(item.orderDetailId, item.quantity)}>+</button>
                      </div>
                    </div>
                    <button className="text-sm text-red-600 ml-3" onClick={() => removeFromCart(item.orderDetailId)}>
                      Remove
                    </button>
                  </li>
                ))}
              </ul>
            )}
          </div>
        </div>
        {itemDetails.length > 0 && (
          <div className="mt-4">
            <p className="text-lg font-semibold text-center">
              Total Amount: ${getTotalAmount().toFixed(2)}
            </p>
            <div className="flex justify-center mt-4">
              <button
                className="bg-black text-white text-xl px-28 py-6 rounded-md hover:bg-[#B6A69D]"
                onClick={handleCheckout}
              >
                Check Out
              </button>
            </div>
          </div>
        )}
      </div>
    </div>
  );
}

export default Cart;
