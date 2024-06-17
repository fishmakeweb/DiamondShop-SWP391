import React, { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";
import NewNavbar from "../components/NewNavbar";
import AuthService from "../components/AuthService";
import Footer from "../components/Footer";

function ConfirmOrder() {
  const location = useLocation();
  const { itemDetails, totalAmount, token } = location.state || { itemDetails: [], totalAmount: 0, token: null };
  const [profile, setProfile] = useState(null);

  useEffect(() => {
    if (token) {
      fetchProfile();
    }
  }, [token]);

  const fetchProfile = async () => {
    try {
      const data = await AuthService.getProfile(token);
      setProfile(data);
    } catch (error) {
      console.error('Failed to fetch profile:', error);
    }
  };

  if (!profile) {
    return <div>Loading...</div>;
  }

  const { fullName, email, address, username } = profile.customer;
  const { registeredDate } = profile.customer;

  return (
    <div className="flex flex-col bg-white">
            <NewNavbar />
      <div className="bg-gray-100 flex items-center justify-center min-h-screen font-nunito text-slate-600">
        <section className="max-w-[968px] w-full mx-4">
          <h1 className="mx-2 my-10 text-2xl font-semibold sm:text-3xl">
            Order Review
          </h1>
          <div className="w-full bg-white p-8 rounded-lg shadow-[0px_10px_15px_9px_#DDE4F1] mb-10">
            <h2 className="text-xl font-[800] mb-4">Items in your cart</h2>
            <div className="divide-y divide-gray-200 w-full">
              {itemDetails.map((item) => (
                <div key={item.orderDetailId} className="py-4 flex items-center">
                  <img
                    loading="lazy"
                    src={item.img}
                    alt={item.name}
                    className="w-24 h-24 rounded-md object-cover"
                  />
                  <div className="ml-4 flex-grow">
                    <p className="text-lg font-medium text-gray-900">{item.name}</p>
                    <p className="text-sm text-gray-500">Price: ${item.price.toFixed(2)}</p>
                    <p className="text-sm text-gray-500">Quantity: {item.quantity}</p>
                  </div>
                  <p className="text-lg font-medium text-gray-900">${(item.price * item.quantity).toFixed(2)}</p>
                </div>
              ))}
            </div>
          </div>
          <div className="w-full bg-white p-8 rounded-lg shadow-[0px_10px_15px_9px_#DDE4F1] mb-10">
            <h2 className="text-xl font-[800] mb-4">User Information</h2>
            <div className="flex flex-col sm:flex-row gap-2 justify-center">
              <div className="w-full">
                <dl className="text-black divide-y divide-gray-200">
                  <div className="flex flex-col py-3">
                    <dt className="mb-1 text-gray-500 md:text-lg">Full Name</dt>
                    <dd className="text-lg font-semibold">{fullName}</dd>
                  </div>
                  <div className="flex flex-col pt-3">
                    <dt className="mb-1 text-gray-500 md:text-lg">Email</dt>
                    <dd className="text-lg font-semibold">{email}</dd>
                  </div>
                </dl>
              </div>
              <div className="w-full">
                <dl className="text-black divide-y divide-gray-200">
                  <div className="flex flex-col pb-3">
                    <dt className="mb-1 text-gray-500 md:text-lg">Address</dt>
                    <dd className="text-lg font-semibold">{address}</dd>
                  </div>
                  <div className="flex flex-col pt-3">
                    <dt className="mb-1 text-gray-500 md:text-lg">Registered Date</dt>
                    <dd className="text-lg font-semibold">{registeredDate}</dd>
                  </div>
                </dl>
              </div>
            </div>
          </div>
          <div className="w-full bg-white p-8 rounded-lg shadow-[0px_10px_15px_9px_#DDE4F1] mb-10">
            <h2 className="text-xl font-[800] mb-4">Order Summary</h2>
            <div className="flex justify-between items-center">
              <p className="text-lg">Total</p>
              <p className="text-lg font-semibold">${totalAmount.toFixed(2)}</p>
            </div>
          </div>
          <button className="w-full bg-black mb-5 text-white py-4 rounded-lg text-lg font-semibold hover:bg-gray-900 transition duration-300 ease-in-out">
            Confirm Order
          </button>
        </section>
      </div>
      <Footer />
    </div>
  );
}

export default ConfirmOrder;
