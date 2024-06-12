import React, { useEffect, useState } from "react";
import Navbar from "../components/Navbar";
import Footer from "../components/Footer";
import JewelryList from "../components/JewelryList";
import axios from "../axios.js";

function Jewelry() {
  const [jewelryItems, setJewelryItems] = useState([]);

  useEffect(() => {
    axios
      .get("http://139.162.39.187:8080/api/jewelry")
      .then((response) => {
        setJewelryItems(response.data);
      })
      .catch((error) => console.error("Error fetching jewelry data:", error));
  }, []);

  return (
    <div className="flex flex-col bg-white">
      <div className="h-24 w-full"></div>
      <Navbar />

      <div className="self-center mt-16 text-4xl text-center text-black">
        Jewelry
      </div>

      <div className="flex justify-center items-center px-16 py-5 mt-5 text-lg text-black border-b border-solid border-neutral-300">
        <div className="flex gap-5 justify-between w-full max-w-4xl">
          <div className="text-center">Price</div>
        </div>
      </div>
      <JewelryList items={jewelryItems} />
      <Footer />
    </div>
  );
}

export default Jewelry;
