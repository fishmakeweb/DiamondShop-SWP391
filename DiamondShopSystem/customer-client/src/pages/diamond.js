import React, { useState, useEffect } from "react";
import Navbar from "../components/Navbar.js";
import Footer from "../components/Footer.js";
import DiamondList from "../components/DiamondList";
import axios from '../axios.js';
import '../style/Diamond.css'; // Ensure the CSS file is imported

function Diamond() {
  const [selectedShapes, setSelectedShapes] = useState([]);
  const [priceFrom, setPriceFrom] = useState("");
  const [priceTo, setPriceTo] = useState("");
  const [caratFrom, setCaratFrom] = useState("");
  const [caratTo, setCaratTo] = useState("");
  const [diamonds, setDiamonds] = useState([]);
  const [filteredDiamonds, setFilteredDiamonds] = useState([]);

  useEffect(() => {
    axios.get('/diamonds')
      .then(response => {
        setDiamonds(response.data);
        setFilteredDiamonds(response.data);
      })
      .catch(error => console.error('Error fetching diamond data:', error));
  }, []);

  function handleFilter() {
    let filtered = diamonds;

    if (selectedShapes.length > 0) {
      filtered = filtered.filter(diamond => selectedShapes.includes(diamond.shape.shapeDescription));
    }

    if (caratFrom !== "") {
      filtered = filtered.filter(diamond => diamond.carat.carat >= parseFloat(caratFrom));
    }

    if (caratTo !== "") {
      filtered = filtered.filter(diamond => diamond.carat.carat <= parseFloat(caratTo));
    }

    if (priceFrom !== "") {
      filtered = filtered.filter(diamond => diamond.price >= parseFloat(priceFrom));
    }

    if (priceTo !== "") {
      filtered = filtered.filter(diamond => diamond.price <= parseFloat(priceTo));
    }

    setFilteredDiamonds(filtered);
  }

  function handleShapeClick(shape) {
    setSelectedShapes(prevSelectedShapes => {
      if (prevSelectedShapes.includes(shape)) {
        return prevSelectedShapes.filter(s => s !== shape);
      } else {
        return [...prevSelectedShapes, shape];
      }
    });
  }

  return (
    <div className="flex overflow-hidden flex-col items-center pb-20 bg-white z-0">
      <div className="self-stretch w-full bg-black min-h-[32px] max-md:max-w-full" />
        <div className="mt-36 text-4xl text-center text-black max-md:mt-10">
          Diamond
        </div>
        <div className="mt-8 text-xl text-center text-stone-500">
          Lorem ipsum dolor sit amet.
        </div>
        
      <div className=" flex flex-col mt-32 w-full max-w-[1265px] max-md:mt-10 max-md:max-w-full">
        <div className="flex gap-5 w-full max-md:flex-wrap max-md:max-w-full">
          <div className="my-auto text-3xl text-black z-0">Shape</div>
          <div className="flex flex-auto gap-5 max-md:flex-wrap inset-0 z-0">
            {["Round", "Princess", "Cushion", "Oval", "Pear", "Emerald", "Heart", "Marquise"].map((shape) => (
              <button
                key={shape}
                className={`flex justify-center items-center px-1.5 bg-white border border-solid backdrop-blur-[2px] border-stone-300 h-[72px] w-[72px] cursor-pointer ${
                  selectedShapes.includes(shape) ? "border-4 border-orange-500" : ""
                }`}
                onClick={() => handleShapeClick(shape)}
              >
                <img
                  loading="lazy"
                  src={`/img/Diamond/${shape}.png`}
                  className="aspect-square w-[60px]"
                  alt={shape}
                />
              </button>
            ))}
          </div>
        </div>
        <div className="flex gap-5 justify-between mt-24 w-full text-base whitespace-nowrap text-stone-300 max-md:flex-wrap max-md:mt-10 max-md:max-w-full">
          <div className="flex gap-5 max-md:flex-wrap max-md:max-w-full">
            <div className="grow text-3xl text-black">Price</div>
            <input
              type="number"
              value={priceFrom}
              onChange={(e) => setPriceFrom(e.target.value)}
              className="number-input justify-center items-start bg-white border border-solid border-stone-300 max-md:pr-5 z-0"
              placeholder="From"
            />
            <input
              type="number"
              value={priceTo}
              onChange={(e) => setPriceTo(e.target.value)}
              className="number-input justify-center items-start bg-white border border-solid backdrop-blur-[2px] border-stone-300 max-md:pr-5"
              placeholder="To"
            />
          </div>
          <div className="flex gap-5 justify-between max-md:flex-wrap max-md:max-w-full">
            <div className="self-start mt-3 text-3xl text-center text-black">
              Carat
            </div>
            <input
              type="number"
              value={caratFrom}
              onChange={(e) => setCaratFrom(e.target.value)}
              className="number-input justify-center items-start bg-white border border-solid backdrop-blur-[2px] border-stone-300 max-md:pr-5"
              placeholder="From"
            />
            <input
              type="number"
              value={caratTo}
              onChange={(e) => setCaratTo(e.target.value)}
              className="number-input justify-center items-start bg-white border border-solid backdrop-blur-[2px] border-stone-300 max-md:pr-5"
              placeholder="To"
            />
          </div>
        </div>
        <button
          className="justify-center self-center px-7 py-4 mt-7 ml-14 text-2xl text-black bg-orange-200 rounded-xl max-md:pr-5"
          onClick={handleFilter}
        >
          Search
        </button>
        <DiamondList items={filteredDiamonds} />
      </div>
      <Navbar />
      <Footer />
    </div>
  );
}

export default Diamond;
