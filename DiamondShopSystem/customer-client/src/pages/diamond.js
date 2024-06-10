
import React, { useState, useEffect } from "react";
import Navbar from "../components/Navbar.js";
import Footer from "../components/Footer.js";
import DiamondList from "../components/DiamondList";
import axios from '../axios.js';

function Diamond() {
  const [selectedShape, setSelectedShape] = useState(null);
  const [priceFrom, setPriceFrom] = useState(0);
  const [priceTo, setPriceTo] = useState(0);
  const [caratFrom, setCaratFrom] = useState(0);
  const [caratTo, setCaratTo] = useState(0);
  const [diamonds, setDiamonds] = useState([]);

  useEffect(() => {
    axios.get('/diamonds')
      .then(response => setDiamonds(response.data))
      .catch(error => console.error('Error fetching diamond data:', error));
  }, []);


  function handleFilter(){
    let byCaratFrom = [];
    if(caratFrom !== 0){
      byCaratFrom = diamonds.filter((item) => {
        return item.carat.carat >= caratFrom
      })
    } else {
      byCaratFrom = diamonds
    }

    let byCaratTo = [];
    if(caratTo !== 0){
      byCaratTo = byCaratFrom.filter((item) => {
        return item.carat.carat <= caratTo
      })
    } else {
      byCaratTo = byCaratFrom
    }


    let byPriceFrom = [];
    if(priceFrom !== 0){
      byPriceFrom = byCaratTo.filter((item) => {
        return item.price >= priceFrom
      })
    } else {
      byPriceFrom = byCaratTo
    }

    let byPriceTo = [];
    if(priceTo !== 0) {
      byPriceTo = byPriceFrom.filter((item) => {
        return item.price <= priceTo
      })
    } else {
      byPriceTo = byPriceFrom
    }

    console.log(byPriceTo)
    return setDiamonds(byPriceTo)
  }

  
    

  return (
    <div className="flex overflow-hidden flex-col items-center pb-20 bg-white z-0">
      <Navbar />
      <div className="self-stretch w-full bg-black min-h-[32px] max-md:max-w-full" />
      <div className="mt-36 text-4xl text-center text-black max-md:mt-10">
        Diamond
      </div>
      <div className="mt-8 text-xl text-center text-stone-500">
        Lorem ipsum dolor sit amet.
      </div>
      <div className="relative flex flex-col mt-32 w-full max-w-[1265px] max-md:mt-10 max-md:max-w-full">
        <div className="flex gap-5 w-full max-md:flex-wrap max-md:max-w-full">
          <div className="my-auto relative text-3xl text-black z-0">Shape</div>
          <div className="flex flex-auto relative gap-5 max-md:flex-wrap inset-0 z-0">
            {["S-1", "S-2", "S-3", "S-4", "S-5", "S-7", "S-8"].map((shape) => (
              <div
                key={shape}
                className={`flex justify-center items-center px-1.5 bg-white border border-solid backdrop-blur-[2px] border-stone-300 h-[72px] w-[72px] cursor-pointer ${
                  selectedShape === shape ? "border-black" : ""
                }`}
                // onClick={() => handleShapeClick(shape)}
              >
                <img
                  loading="lazy"
                  srcSet={`/img/Diamond/${shape}.png`}
                  className="aspect-square w-[60px]"
                  alt={shape}
                />
              </div>
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
              className="justify-center items-start bg-white border border-solid border-stone-300 max-md:pr-5 z-0"
              placeholder="From"
            />
            <input
              type="number"
              value={priceTo}
              onChange={(e) => setPriceTo(e.target.value)}
              className="justify-center items-start bg-white border border-solid backdrop-blur-[2px] border-stone-300 max-md:pr-5"
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
              className="justify-center items-start bg-white border border-solid backdrop-blur-[2px] border-stone-300 max-md:pr-5"
              placeholder="From"
            />
            <input
              type="number"
              value={caratTo}
              onChange={(e) => setCaratTo(e.target.value)}
              className="justify-center items-start bg-white border border-solid backdrop-blur-[2px] border-stone-300 max-md:pr-5"
              placeholder="To"
            />
          </div>
        </div>
        <button
          className="justify-center self-center px-7 py-4 mt-7 ml-14 text-2xl text-black bg-orange-200 rounded-xl max-md:pr-5"
          onClick={handleFilter}
        >
          {diamonds.length} Diamonds available
        </button>
        <DiamondList items={diamonds} />
      </div>
      <Footer />
    </div>
  );
}

export default Diamond;
