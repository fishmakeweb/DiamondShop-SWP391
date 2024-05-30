import React from "react";

function Cart({ isOpen, onClose }) {
  if (!isOpen) return null;

  return (
    <div className="fixed top-0 left-0 bg-gray-500 bg-opacity-75 flex justify-end h-full w-full">
      <div className="bg-white p-5 rounded-lg shadow-lg h-2/3 w-3/12 max-w-full max-h-full mt-10">
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
          <p className="text-xl text-center font-medium text-neutral-600">
            Your cart is currently empty.
          </p>
        </div>
      </div>
    </div>
  );
}

export default Cart;
