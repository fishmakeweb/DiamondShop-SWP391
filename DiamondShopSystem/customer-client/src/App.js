import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Homepage from './pages/Homepage';
import Jewelry from './pages/Jewelry';
import JewelryItem from './pages/JewelryItem';
import ScrollToTop from './components/ScrollToTop';
import Category from './pages/Category';
import Gemstone from './pages/Gemstone';

function App() {
  return (
    <Router>
      <div>
        <ScrollToTop />
        <Routes>
          <Route path="/" element={<Homepage />} />
          <Route path="/jewelry" element={<Jewelry />} />
          <Route path="/jewelry/categories/:categoryid" element={<Category />} />
          <Route path="/jewelry/gemstones/:gemstoneid" element={<Gemstone />} />
          <Route path="/jewelry/:jewelryId" element={<JewelryItem />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;