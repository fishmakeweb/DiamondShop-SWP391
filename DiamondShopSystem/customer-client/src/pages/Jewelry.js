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
      .get("http://localhost:8080/api/jewelry")
      .then((response) => {
        setJewelryItems(response.data);
        setFilteredJewelry(response.data);
      })
      .catch((error) => console.error("Error fetching jewelry data:", error));
  }, []);

  useEffect(() => {
    let filtered = jewelryItems;

    if (priceRange) {
      const [from, to] = priceRange.split("-").map(Number);
      if (!isNaN(to)) {
        filtered = jewelryItems.filter(
          (jewelry) =>
            jewelry.price >= parseFloat(from) && jewelry.price <= parseFloat(to)
        );
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

      <JewelryList items={filteredJewelry} />
      <Footer />
    </div>
  );
}

export default Jewelry;
