import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import Navbar from "../components/Navbar";
import Footer from "../components/Footer";
import axios from "../axios.js";
import JewelryList from "../components/JewelryList";

function Category() {
  const { categoryid } = useParams();
  const [categoryName, setCategoryName] = useState("");
  const [jewelryItems, setJewelryItems] = useState([]);

  useEffect(() => {
    axios
      .get(`/categories/${categoryid}`)
      .then((response) => {
        setCategoryName(response.data.categoryName);
      })
      .catch((error) => console.error("Error fetching category data:", error));

    axios
      .get(`/jewelry/categories/${categoryid}`)
      .then((response) => {
        setJewelryItems(response.data);
      })
      .catch((error) => console.error("Error fetching jewelry items:", error));
  }, [categoryid]);

  return (
    <div className="flex flex-col bg-white">
      <div className="h-24 w-full"></div>
      <Navbar />
      <div className="self-center mt-16 text-4xl text-center text-black">
        {categoryName}
      </div>
      <JewelryList items={jewelryItems} />
      <Footer />
    </div>
  );
}

export default Category;
