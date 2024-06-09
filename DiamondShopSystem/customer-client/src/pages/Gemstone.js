import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import Navbar from "../components/Navbar";
import Footer from "../components/Footer";
import axios from "../axios.js";

import JewelryList from "../components/JewelryList";

function Gemstone() {
  const { gemstoneid } = useParams();
  const [gemstoneName, setGemstoneName] = useState("");
  const [jewelryItems, setJewelryItems] = useState([]);

  useEffect(() => {
    axios
      .get(`/gemstones/${gemstoneid}`)
      .then((response) => {
        setGemstoneName(response.data.gemstoneName);
      })
      .catch((error) => console.error("Error fetching category data:", error));

    axios
      .get(`/jewelry/gemstones/${gemstoneid}`)
      .then((response) => {
        setJewelryItems(response.data);
      })
      .catch((error) => console.error("Error fetching jewelry items:", error));
  }, [gemstoneid]);

  // Handlers for fetched data

  return (
    <div className="flex flex-col bg-white">
      <div className="h-24 w-full"></div>
      <Navbar />
      <div className="self-center mt-16 text-4xl text-center text-black">
        {gemstoneName}
      </div>
      <JewelryList items={jewelryItems} />
      <Footer />
    </div>
  );
}

export default Gemstone;
