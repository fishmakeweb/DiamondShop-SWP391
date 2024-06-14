import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import AuthService from "./AuthService";

function LoginModal({ isOpen, onClose, openSignUp }) {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();
    try {
      const response = await AuthService.login(username, password);
      console.log(response);
      if (AuthService.isAuthenticated()) {
        onClose();
        if (AuthService.isCustomer()) navigate("/");
        if (AuthService.isStaff()) navigate("/adminPage");
      } else {
        setUsername("");
        setPassword("");
        setError("Login failed. Please check your credentials.");
      }
    } catch (error) {
      console.error("Login failed:", error);
      setError("Login failed. Please check your credentials.");
    }
  };

  if (!isOpen) return null;

  return (
    <div className="fixed inset-0 bg-gray-500 bg-opacity-75 flex justify-center items-center">
      <div className="bg-white p-5 rounded-lg shadow-lg h-2/3 w-3/12 max-w-full max-h-full">
        <div className="flex justify-between items-center border-b border-stone-400 pb-4 mb-4">
          <div className="flex items-center gap-4">
            <img
              loading="lazy"
              src="https://cdn.builder.io/api/v1/image/assets/TEMP/0613006e3e56e9621b17c3cd1f1af563d72391d04585cc0a8f01c454d5b110fa?"
              alt="Logo"
              className="w-8 h-8"
            />
            <h2 className="text-2xl font-semibold">Log In</h2>
          </div>
          <img
            loading="lazy"
            src="https://cdn.builder.io/api/v1/image/assets/TEMP/bfc96218bf522623257aa77aa9fc8238e2e164baa393e0f71d35ec4a2f1ef1fb?"
            alt="Close"
            className="w-6 h-6 cursor-pointer"
            onClick={onClose}
          />
        </div>
        <form onSubmit={handleLogin} className="flex flex-col space-y-6">
          <input
            type="text"
            placeholder="Username"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            className="w-full px-4 py-3 border rounded focus:outline-none focus:ring-2 focus:ring-blue-500"
          />
          <input
            type="password"
            placeholder="Password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            className="w-full px-4 py-3 border rounded focus:outline-none focus:ring-2 focus:ring-blue-500"
          />
          {error && <div className="text-red-500 text-sm">{error}</div>}
          <div className="flex justify-end text-sm text-blue-500 cursor-pointer">
            Forgot Password?
          </div>
          <button
            type="submit"
            className="w-full py-3 text-white bg-black rounded hover:bg-opacity-90"
          >
            LOG IN
          </button>
          <div className="text-center">
            <span>Don't have an account? </span>
            <span className="text-blue-500 cursor-pointer" onClick={openSignUp}>
              Sign Up
            </span>
          </div>
        </form>
      </div>
    </div>
  );
}

export default LoginModal;
