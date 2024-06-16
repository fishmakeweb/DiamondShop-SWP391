import React, { useContext, useState, useEffect } from "react";
import axios from "axios";
import CartContext from "./CartContext";

function Cart({ isOpen, onClose }) {
  const { cart, removeFromCart, getTotalAmount, incrementQuantity, decrementQuantity } = useContext(CartContext);
  const [itemDetails, setItemDetails] = useState([]);

  useEffect(() => {
    if (isOpen) {
      fetchCarts().then(cartItems => {
        fetchItemDetails(cartItems);
      });
    }
  }, [isOpen]);

  const fetchCarts = async () => {
    const user = JSON.parse(localStorage.getItem("user"));
    const userId = user.userId;

    try {
      const response = await axios.get(`http://localhost:8080/api/orders/getCart/${userId}`);
      return response.data.map(item => ({
        productId: item.product.productId,
        orderId: item.order.orderId,
        quantity: item.quantity,
        unitPrice: item.unitPrice,
      }));
    } catch (error) {
      console.error("Error fetching users data:", error);
      return [];
    }
  };

  const fetchItemDetails = async (cartItems) => {
    try {
      const detailsPromises = cartItems.map(async (item) => {
        const response = await axios.get(`http://localhost:8080/api/jewelry/${item.productId}`);
        return {
          jewelryId: response.data.jewelryId,
          name: response.data.name,
          img: response.data.img,
          price: item.unitPrice,   
          quantity: item.quantity
        };
      });

      const details = await Promise.all(detailsPromises);
      setItemDetails(details);
    } catch (error) {
      console.error("Error fetching product details:", error);
    }
  };

  if (!isOpen) return null;

  const handleCheckout = () => {
    console.log("Implementing checkout logic...");
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
                  <li key={item.jewelryId} className="py-4 flex items-center">
                    <img
                      loading="lazy"
                      src={`${item.img}`}
                      alt={item.name}
                      className="w-24 h-24 rounded-md object-cover"
                    />
                    <div className="ml-4 flex-grow">
                      <p className="text-lg font-medium text-gray-900">
                        {item.name}
                      </p>
                      <p className="text-sm text-gray-500">
                        Price: ${item.price}
                      </p>
                      <div className="flex items-center">
                        <button className="text-sm text-gray-600 px-3 py-1 bg-gray-200 rounded-md" onClick={() => decrementQuantity(item.jewelryId)}>-</button>
                        <p className="text-sm text-gray-500 mx-2">{item.quantity}</p>
                        <button className="text-sm text-gray-600 px-3 py-1 bg-gray-200 rounded-md" onClick={() => incrementQuantity(item.jewelryId)}>+</button>
                      </div>
                    </div>
                    <button
                      className="text-sm text-red-600 ml-3"
                      onClick={() => removeFromCart(item.jewelryId)}
                    >
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
