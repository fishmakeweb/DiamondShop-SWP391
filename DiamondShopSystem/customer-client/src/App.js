import React, { useState } from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Homepage from './pages/Homepage';
import Jewelry from './pages/Jewelry';
import JewelryItem from './pages/JewelryItem';
import ScrollToTop from './components/ScrollToTop';
import Category from './pages/Category';
import Gemstone from './pages/Gemstone';
import CartContext from './components/CartContext';
import Diamond from './pages/diamond.js';
import DiamondDetails from './components/DiamondDetails';
import { CartProvider } from './components/CartContext';
import LoginModal from './components/LoginModal';
import UserProfile from './pages/UserProfile';
import Staff from './pages/Staff'


function App() {
  const [isLoginModalOpen, setIsLoginModalOpen] = useState(false);

  const toggleLoginModal = () => {
    setIsLoginModalOpen(!isLoginModalOpen);
  };
  return (
    <Router>
      <CartProvider> {/* Wrap Routes with CartProvider */}
        <div>
          <ScrollToTop />
          <Routes>
            <Route path="/" element={<Homepage />} />
            <Route path="/jewelry" element={<Jewelry />} />
            <Route path="/diamond" element={<Diamond />} />
            <Route path="/jewelry/categories/:categoryid" element={<Category />} />
            <Route path="/jewelry/gemstones/:gemstoneid" element={<Gemstone />} />
            <Route path="/jewelry/:jewelryId" element={<JewelryItem />} />
            <Route path="/profile" element={<UserProfile />} /> {/* Add profile route */}
            <Route path="/staff" element={<Staff />}/>
          </Routes>
          <LoginModal isOpen={isLoginModalOpen} onClose={toggleLoginModal} />
        </div>
      </CartProvider>
    </Router>
  );
}

export default App;
