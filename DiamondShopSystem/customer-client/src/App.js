import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Homepage from './pages/Homepage';
import Jewelry from './pages/Jewelry';

function App() {
  return (
    <Router>
      <div>
        <Routes>
          <Route path="/" element={<Homepage />} />
          <Route path="/jewelry" element={<Jewelry />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;