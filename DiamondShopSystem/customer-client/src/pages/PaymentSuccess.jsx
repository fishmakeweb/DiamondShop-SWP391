import React, { useEffect } from "react";
import "../style/PaymentSuccess.css";
import { Link, useParams } from "react-router-dom";
import axios from "../axios";

function PaymentSuccess() {
  const {orderId} = useParams();
  useEffect(() => {
    axios.post(`/confirmOrder?orderId=${orderId}`);
  }, [orderId]);

  return (
    <div className="flex flex-col items-center justify-center min-h-screen bg-gray-100">
      <div className="bg-white shadow-lg rounded-lg p-6 max-w-sm w-full text-center">
        <div className="flex justify-center mb-4">
          <div className="success-checkmark">
            <div className="check-icon">
              <span className="icon-line line-tip"></span>
              <span className="icon-line line-long"></span>
              <div className="icon-circle"></div>
              <div className="icon-fix"></div>
            </div>
          </div>
        </div>
        <div className="text-2xl font-bold text-green-600 mb-4">Payment Success!</div>
        <div className="text-xl text-black mb-8">Thank you for your purchase!</div>
        <div className="items-center">
          <Link to="/" className="bg-black text-white py-4 px-6 rounded hover:bg-gray-900 focus:outline-none">
            Go Back to Store
          </Link>
        </div>
      </div>
    </div>
  );
}

export default PaymentSuccess;
