import React, { useState } from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Homepage from "./pages/Homepage";
import Jewelry from "./pages/Jewelry";
import JewelryItem from "./pages/JewelryItem";
import ScrollToTop from "./components/ScrollToTop";
import Diamond from "./pages/diamond.js";
import LoginModal from "./components/LoginModal";
import UserProfile from "./pages/UserProfile";
import Staff from "./pages/Staff";
import AdminPage from "./pages/AdminPage.jsx";
import SaleStaff from "./components/SaleStaff.jsx";
import AddJewelry from "./components/AddJewelry.jsx";
import AddDiamond from "./components/AddDiamond.jsx";
import LoginPage from "./pages/LoginPage.jsx";
import SignUp from "./pages/Signup.jsx";
import ViewAllJewelry from "./components/ViewAllJewelry.jsx";
function App() {
  const [isLoginModalOpen, setIsLoginModalOpen] = useState(false);

  const toggleLoginModal = () => {
    setIsLoginModalOpen(!isLoginModalOpen);
  };
  return (
    <Router>
        {" "}
        {/* Wrap Routes with CartProvider */}
        <div>
          <ScrollToTop />
          <Routes>
            <Route path="/" element={<Homepage />} />
            <Route path="/jewelry/page/:productId" element={<Jewelry />} />
            <Route path="/diamonds" element={<Diamond />} />
            <Route path="/jewelry/:productId" element={<JewelryItem />} />
            <Route path="/profile" element={<UserProfile />} />{" "}
            <Route path="/staff" element={<Staff />} />
            <Route path="/salestaff" element={<SaleStaff />} />
            <Route path="/adminPage" element={<AdminPage />} />
            <Route path="/addjewelry" element={<AddJewelry />} />
            <Route path="/adddiamond" element={<AddDiamond />} />
            <Route path="/login" element={<LoginPage />} />
            <Route path="/signup" element={<SignUp />} />
            <Route path="/viewalljewelry" element={<ViewAllJewelry />} />
          </Routes>
          <LoginModal isOpen={isLoginModalOpen} onClose={toggleLoginModal} />
        </div>
    </Router>
  );
}

export default App;
