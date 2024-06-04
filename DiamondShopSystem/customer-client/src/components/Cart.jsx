import React, { useContext } from "react";
import CartContext from "./CartContext";

function Cart({ isOpen, onClose }) {
  const { cart, removeFromCart } = useContext(CartContext);

  if (!isOpen) return null;

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
            {cart.length === 0 ? (
              <p className="text-xl text-center font-medium text-neutral-600">
                Your cart is currently empty.
              </p>
            ) : (
              <ul className="divide-y divide-gray-200 w-full">
                {cart.map((item) => (
                  <li key={item.jewelryId} className="py-4 flex items-center">
                    <img
                    loading="lazy"
                      src={`/img/jewelry/${item.img}`}
                      alt={item.name}
                      className="w-24 h-24 rounded-md object-cover"
                    />
                    <div className="ml-4 flex-grow">
                      <p className="text-lg font-medium text-gray-900">
                        {item.name}
                      </p>
                      <p className="text-sm text-gray-500">
                        Quantity: {item.quantity}
                      </p>
                      <p className="text-sm text-gray-500">
                        Price: ${item.price.toFixed(2)}
                      </p>
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
        {cart.length > 0 && (
          <div className="flex justify-center pb-4">
            <button
              className="mt-5 bg-black text-white text-xl px-24 py-6 rounded-md hover:bg-[#B6A69D]"
              onClick={() => {/* Handle checkout action here */}}
            >
              Check Out
            </button>
          </div>
        )}
      </div>
    </div>
  );
}

export default Cart;
