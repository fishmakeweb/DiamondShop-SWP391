import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Homepage from "./pages/Homepage";
import Jewelry from "./pages/Jewelry";
import JewelryItem from "./pages/JewelryItem";
import ScrollToTop from "./components/ScrollToTop";
import UserProfile from "./pages/UserProfile";
import Staff from "./pages/Staff";
import AdminPage from "./pages/AdminPage.jsx";
import SaleStaff from "./components/SaleStaff.jsx";
import AddJewelry from "./components/AddJewelry.jsx";
import AddDiamond from "./components/AddDiamond.jsx";
import LoginPage from "./pages/LoginPage.jsx";
import SignUp from "./pages/Signup.jsx";
import ViewDiamond from "./components/ViewDiamond.jsx";
import ConfirmOrder from "./pages/OrderConfirm.jsx";
import ChangePassword from "./pages/ChangePassword.jsx";
import EmailComponent from "./pages/EmailComponent.jsx";
import ViewJewelry from "./components/ViewJewelry.jsx";
import NewDiamond from "./components/NewDiamond.jsx";
import NewRelease from "./components/NewRelease.jsx";
import { CartProvider } from "./CartContext.js";
import PaymentSuccess from "./pages/PaymentSuccess.jsx";


function App() {
  return (
    <Router>
        {/* */}
        {/* Wrap Routes with CartProvider */}
        
          <CartProvider>
          <ScrollToTop />
          <Routes>
            <Route path="/" element={<Homepage />} />
            <Route path="/jewelry/page/:page_number" element={<Jewelry />} />
            <Route path="/jewelry/:productId" element={<JewelryItem />} />
            <Route path="/profile" element={<UserProfile />} />{" "}
            <Route path="/staff" element={<Staff />} />
            <Route path="/salestaff" element={<SaleStaff />} />
            <Route path="/adminPage" element={<AdminPage />} />
            <Route path="/addjewelry" element={<AddJewelry />} />
            <Route path="/adddiamond" element={<AddDiamond />} />
            <Route path="/login" element={<LoginPage />} />
            <Route path="/login/signup" element={<SignUp />} />
            <Route path="/viewalljewelry" element={<ViewJewelry />} />
            <Route path="/viewalldiamond" element={<ViewDiamond />} />
            <Route path="/newdiamond" element={<NewDiamond />} />
            <Route path="/confirmOrder" element={<ConfirmOrder />} />
            <Route path="/changePassword" element={<ChangePassword />} />
            <Route path="/emailComponent" element={<EmailComponent />} />
            <Route path="/newrelease/page/:page_number" element={<NewRelease />} />
            <Route path="/success/:orderId" element={<PaymentSuccess/>} />
          </Routes>
          </CartProvider>
    </Router>
  );
}

export default App;
