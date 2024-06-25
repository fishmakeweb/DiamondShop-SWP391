
import React, { useState, useEffect, useCallback } from "react";
import axios from "../axios";
import { useNavigate } from "react-router-dom";
import AuthService from "./AuthService";
import { debounce } from "lodash";

function Cart({ isOpen, onClose }) {
  const [itemDetails, setItemDetails] = useState([]);
  const [errorMessages, setErrorMessages] = useState({});
  const [orderId, setOrderId] = useState();
  const navigate = useNavigate();

  useEffect(() => {
    if (isOpen) {
      fetchCarts();
    }
  }, [isOpen]);

  const updateQuantityInState = (orderDetailId, newQuantity) => {
    setItemDetails((currentItems) =>
      currentItems.map((item) => {
        if (item.orderDetailId === orderDetailId) {
          return { ...item, quantity: newQuantity };
        }
        return item;
      }).filter(item => item.quantity > 0) // Continue filtering out zero quantities
    );
  };

  const updateQuantity = async (orderDetailId, newQuantity) => {
    const token = localStorage.getItem("token");
    try {
      await axios.post(`/order_details/updateQuantity`, null, {
        params: { orderDetailId, quantity: newQuantity },
        headers: { Authorization: `Bearer ${token}` },
      });
    } catch (error) {
      console.error("Error updating quantity:", error);
    }
  };

  const debouncedUpdateTotal = useCallback(debounce((orderDetailId, newQuantity) => {
    updateQuantity(orderDetailId, newQuantity);
  }, 800), []); // Debounce updates to prevent excessive API calls

  const handleQuantityChange = (orderDetailId, newQuantity) => {
    const numericQuantity = parseInt(newQuantity, 10);
    if (!isNaN(numericQuantity)) {
      if (numericQuantity > 1000) {
        // Set error message for this item
        setErrorMessages(prev => ({ ...prev, [orderDetailId]: "Quantity cannot exceed 1000" }));
      } else {
        // Clear any existing error message and update quantity
        setErrorMessages(prev => ({ ...prev, [orderDetailId]: "" }));
        updateQuantityInState(orderDetailId, numericQuantity);
        debouncedUpdateTotal(orderDetailId, numericQuantity);
      }
    }
  };

  const incrementQuantity = (orderDetailId, currentQuantity) => {
    handleQuantityChange(orderDetailId, currentQuantity + 1);
  };

  const decrementQuantity = (orderDetailId, currentQuantity) => {
    handleQuantityChange(orderDetailId, Math.max(currentQuantity - 1, 0)); // Prevent negative quantities
  };

  const removeFromCart = (orderDetailId) => {
    handleQuantityChange(orderDetailId, 0);
  };

  const fetchCarts = async () => {
    try {
      const token = localStorage.getItem("token");
      const data = await AuthService.getCart(token);
      if (data.listOrderDetail.length !== 0) setOrderId(data.listOrderDetail[0].orderId);
      setItemDetails(
        data.listOrderDetail.map((item) => ({
          ...item.product.jewelry,
          orderDetailId: item.id,
          quantity: item.quantity,
        })).filter(item => item.quantity > 0)
      );
    } catch (error) {
      console.error("Error fetching users data:", error);
    }
  };

  const getTotalAmount = () => {
    return itemDetails.reduce(
      (acc, item) => acc + item.price * item.quantity,
      0
    );
  };

  if (!isOpen) return null;

  const handleCheckout = () => {
    const token = localStorage.getItem("token");
    navigate("/confirmOrder", {
      state: {
        orderId,
        itemDetails,
        totalAmount: getTotalAmount(),
        token,
      },
    });
  };

  return (
    <div className="fixed z-50 top-0 left-0 bg-black bg-opacity-80 flex items-end justify-end h-full w-full">
      <div className="bg-white p-5 rounded-lg shadow-lg h-full w-full max-w-md md:max-w-lg lg:max-w-xl overflow-auto mt-0 flex flex-col justify-between">
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
                        <input
                          type="number"
                          max="1000"
                          className="w-12 text-center border border-gray-300 rounded-md mx-2"
                          value={item.quantity}
                          onChange={(e) => handleQuantityChange(item.orderDetailId, e.target.value)}
                        />
                        <button className="text-sm text-gray-600 px-3 py-1 bg-gray-200 rounded-md"
                          onClick={() => incrementQuantity(item.orderDetailId, item.quantity)}>+</button>
                      </div>
                      {errorMessages[item.orderDetailId] && (
                        <p className="text-red-500 text-sm mt-2">{errorMessages[item.orderDetailId]}</p>
                      )}
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
// import React, { useEffect } from "react";
// import { useNavigate } from "react-router-dom";
// import { useCart } from "./CartContext"; // Import useCart

// function Cart({ isOpen, onClose }) {
//   const navigate = useNavigate();
//   const { cart, isLoading, removeFromCart, incrementQuantity, decrementQuantity } = useCart();

//   const handleCheckout = () => {
//     const token = localStorage.getItem("token");
//     navigate("/confirmOrder", {
//       state: {
//         cart,
//         token,
//       },
//     });
//   };

//   if (!isOpen || isLoading) return null;

//   return (
//     <div className="fixed z-50 top-0 left-0 bg-black bg-opacity-80 flex items-end justify-end h-full w-full">
//       <div className="bg-white p-5 rounded-lg shadow-lg h-full w-full max-w-md md:max-w-lg lg:max-w-xl overflow-auto mt-0 flex flex-col justify-between">
//         <div>
//           <div className="flex justify-between items-center border-b border-stone-400 pb-4 mb-4">
//             <h2 className="text-2xl font-semibold">In My Bag</h2>
//             <button onClick={onClose} className="text-red-500">Close</button>
//           </div>
//           <div className="flex flex-col justify-center items-center h-full">
//             {cart.length === 0 ? (
//               <p className="text-xl text-center font-medium text-neutral-600">
//                 Your cart is currently empty.
//               </p>
//             ) : (
//               <ul className="divide-y divide-gray-200 w-full">
//                 {cart.map((item) => (
//                   <li key={item.orderDetailId} className="py-4 flex items-center justify-between">
//                     <div>
//                       <h3>{item.productName}</h3>
//                       <p>Price: ${item.price}</p>
//                     </div>
//                     <div>
//                       <button onClick={() => decrementQuantity(item.orderDetailId)}>-</button>
//                       <span>{item.quantity}</span>
//                       <button onClick={() => incrementQuantity(item.orderDetailId)}>+</button>
//                     </div>
//                     <button onClick={() => removeFromCart(item.orderDetailId)}>
//                       Remove
//                     </button>
//                   </li>
//                 ))}
//               </ul>
//             )}
//           </div>
//           <div className="mt-4">
//             <p className="text-lg font-semibold text-center">
//               Total: ${cart.reduce((acc, item) => acc + item.quantity * item.price, 0)}
//             </p>
//             <button onClick={handleCheckout} className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">
//               Check Out
//             </button>
//           </div>
//         </div>
//       </div>
//     </div>
//   );
// }

// export default Cart;
