import React, { useEffect, useState } from "react";
import Navbar from "../components/Navbar";
import Footer from "../components/Footer";
import JewelryList from "../components/JewelryList";
import axios from "../axios.js";

function Jewelry() {
  const [jewelryItems, setJewelryItems] = useState([]);
  const [priceRange, setPriceRange] = useState("");
  const [filteredJewelry, setFilteredJewelry] = useState([]);
  const [categories, setCategories] = useState("");


  useEffect(() => {
    axios
      .get("/jewelry")
      .then((response) => {
        setJewelryItems(response.data);
        setFilteredJewelry(response.data);
      })
      .catch((error) => console.error("Error fetching jewelry data:", error));
  }, []);

  useEffect(() => {
    let filtered = jewelryItems;

    if (priceRange) {
      const [from, to] = priceRange.split('-').map(Number);
      if (!isNaN(to)) {
        filtered = jewelryItems.filter(jewelry => jewelry.price >= parseFloat(from) && jewelry.price <= parseFloat(to));
      }
    }
    setFilteredJewelry(filtered);
  }, [priceRange, jewelryItems]);

  return (
    <div className="flex flex-col bg-white">
      <div className="h-24 w-full"></div>
      <Navbar />

      <div className="self-center mt-16 text-4xl text-center text-black">
        Jewelry
      </div>

      <div className="flex justify-center items-center px-16 py-5 mt-5 text-lg text-black border-b border-solid border-neutral-300">
        <div className="flex gap-5 justify-between w-full max-w-4xl">
          <div className="flex justify-center mt-8">
            <div className="flex items-center gap-4">
              <div className="text-lg text-black">Price:</div>
              <select 
                value={priceRange} 
                onChange={(e) => setPriceRange(e.target.value)}
                className="number-input bg-white border border-solid border-stone-300 px-2 py-1"
              >
                <option value="">Select Price</option> 
                <option value="1000-3000">$1000 - $3000</option> 
                <option value="3000-5000">$3000 - $5000</option> 
                <option value="5000-9999999">Higher $5000</option> 
              </select>
            </div>
            <div className="text-lg text-black">Type:</div>
            <select
              value={categories}
              onChange={(e) => setCategories(e.target.value)}
              className="category-input bg-white border border-solid border-stone-300 px-2 py-1"
            >
              {/* <option value="">Select Categories</option>
              {jewelryItems.category.categoryName.map(category => (
                <option key={category} value={category}>{category}</option>
              ))} */}
            </select>
          </div>
        </div>
      </div>

      <JewelryList items={filteredJewelry} />
      <Footer />
    </div>
  );
}

export default Jewelry;
