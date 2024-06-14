import React, { useState } from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Homepage from "./pages/Homepage";
import Jewelry from "./pages/Jewelry";
import JewelryItem from "./pages/JewelryItem";
import ScrollToTop from "./components/ScrollToTop";
import Category from "./pages/Category";
import Gemstone from "./pages/Gemstone";
import Diamond from "./pages/diamond.js";
import { CartProvider } from "./components/CartContext";
import LoginModal from "./components/LoginModal";
import UserProfile from "./pages/UserProfile";
import Staff from "./pages/Staff";
import DiamondItem from "./pages/DiamondItem.js";
import AdminPage from "./pages/AdminPage.jsx";
import SaleStaff from "./components/SaleStaff.jsx";

function App() {
  const [isLoginModalOpen, setIsLoginModalOpen] = useState(false);

  const toggleLoginModal = () => {
    setIsLoginModalOpen(!isLoginModalOpen);
  };
  return (
    <Router>
      <CartProvider>
        {" "}
        {/* Wrap Routes with CartProvider */}
        <div>
          <ScrollToTop />
          <Routes>
            <Route path="/" element={<Homepage />} />
            <Route path="/jewelry" element={<Jewelry />} />
            <Route path="/diamonds" element={<Diamond />} />
            <Route path="/diamonds/:diamondId" element={<DiamondItem />} />
            <Route
              path="/jewelry/categories/:categoryid"
              element={<Category />}
            />
            <Route
              path="/jewelry/gemstones/:gemstoneid"
              element={<Gemstone />}
            />
            <Route path="/jewelry/:jewelryId" element={<JewelryItem />} />
            <Route path="/profile" element={<UserProfile />} />{" "}
            {/* Add profile route */}
            <Route path="/staff" element={<Staff />} />
            <Route path="/salestaff" element={<SaleStaff />} />
            <Route path="/adminPage" element={<AdminPage />} />
          </Routes>
          <LoginModal isOpen={isLoginModalOpen} onClose={toggleLoginModal} />
        </div>
      </CartProvider>
    </Router>
  );
}

export default App;
