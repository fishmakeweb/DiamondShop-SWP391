import React, { useState, useEffect } from "react";
import Navbar from "../components/Navbar.js";
import Footer from "../components/Footer.js";
import DiamondList from "../components/DiamondList";
import axios from '../axios.js';
import '../style/Diamond.css'; 
import RangeSlider from "../components/RangeSlider.js";

function Diamond() {
  const colorGrades = ['D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M'];
  const clarityGrades = ['FL', 'IF', 'VVS1', 'VVS2', 'VS1', 'VS2', 'SI1', 'SI2', 'I1', 'I2'];
  const cutGrades = ['Fair', 'Good', 'Very Good', 'Excellent'];

  const [selectedShapes, setSelectedShapes] = useState([]);
  const [priceFrom, setPriceFrom] = useState("");
  const [priceTo, setPriceTo] = useState("");
  const [caratFrom, setCaratFrom] = useState("");
  const [caratTo, setCaratTo] = useState("");
  const [diamonds, setDiamonds] = useState([]);
  const [filteredDiamonds, setFilteredDiamonds] = useState([]);
  const [millimetreFrom, setMillimetreFrom] = useState("");
  const [millimetreTo, setMillimetreTo] = useState("");
  const [minColor, setMinColor] = useState(0); // Default to D
  const [maxColor, setMaxColor] = useState(9); // Default to M
  const [minClarity, setMinClarity] = useState(0); // Default to FL
  const [maxClarity, setMaxClarity] = useState(9); // Default to I2
  const [minCut, setMinCut] = useState(0); // Default to Fair
  const [maxCut, setMaxCut] = useState(3); // Default to Excellent

  useEffect(() => {
    axios.get('/diamonds')
      .then(response => {
        setDiamonds(response.data);
        setFilteredDiamonds(response.data);
      })
      .catch(error => console.error('Error fetching diamond data:', error));
  }, []);

  useEffect(() => {
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

    if (millimetreFrom !== "") {
      filtered = filtered.filter(diamond => diamond.measurement.length >= parseFloat(millimetreFrom));
    }

    if (millimetreTo !== "") {
      filtered = filtered.filter(diamond => diamond.measurement.length <= parseFloat(millimetreTo));
    }

    const selectedColorGrades = colorGrades.slice(minColor, maxColor + 1);
    const selectedClarityGrades = clarityGrades.slice(minClarity, maxClarity + 1);
    const selectedCutGrades = cutGrades.slice(minCut, maxCut + 1);

    filtered = filtered.filter(diamond => selectedColorGrades.includes(diamond.color.colorDescription));
    filtered = filtered.filter(diamond => selectedClarityGrades.includes(diamond.clarity.clarityDescription));
    filtered = filtered.filter(diamond => selectedCutGrades.includes(diamond.cut.cutDescription));

    setFilteredDiamonds(filtered);
  }, [
    selectedShapes, priceFrom, priceTo, caratFrom, caratTo, millimetreFrom, millimetreTo,
    minColor, maxColor, minClarity, maxClarity, minCut, maxCut, diamonds
  ]);

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
          <div className="flex gap-5 max-md:flex-wrap max-md:max-w-full">
            <div className="grow text-3xl text-black">
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
              className="number-input justify-center items-start bg-white border border-solid border-stone-300 max-md:pr-5"
              placeholder="To"
            />
          </div>
        </div>

       

        <div className="flex gap-5 justify-between mt-24 w-full text-base whitespace-nowrap text-stone-300 max-md:flex-wrap max-md:mt-10 max-md:max-w-full">
          <div className="flex gap-5 max-md:flex-wrap max-md:max-w-full w-full">
            <div className="w-full max-w-[550px]">
              <RangeSlider 
                label="Color"
                grades={colorGrades}
                minGrade={minColor}
                maxGrade={maxColor}
                setMinGrade={setMinColor}
                setMaxGrade={setMaxColor}
              />
            </div>
            <div className="w-full max-w-[550px] ml-60">
              <RangeSlider 
                label="Clarity"
                grades={clarityGrades}
                minGrade={minClarity}
                maxGrade={maxClarity}
                setMinGrade={setMinClarity}
                setMaxGrade={setMaxClarity}
              />
            </div>
          </div>
        </div>

        <div className="flex gap-5 justify-between mt-24 w-full text-base whitespace-nowrap text-stone-300 max-md:flex-wrap max-md:mt-10 max-md:max-w-full">
          <div className="flex gap-5 max-md:flex-wrap max-md:max-w-full w-full">
            <div className="w-full max-w-[550px]">
              <RangeSlider 
                label="Cut"
                grades={cutGrades}
                minGrade={minCut}
                maxGrade={maxCut}
                setMinGrade={setMinCut}
                setMaxGrade={setMaxCut}
              />
            </div>
          </div>
           <div className="flex gap-5 justify-end items-center w-full text-base whitespace-nowrap text-stone-300 max-md:flex-wrap max-md:mt-10 max-md:max-w-full">
          <div className="flex gap-5 max-md:flex-wrap max-md:max-w-full">
            <div className="grow text-3xl text-black">
              Millimeter
            </div>
            <input
              type="number"
              value={millimetreFrom}
              onChange={(e) => setMillimetreFrom(e.target.value)}
              className="number-input justify-center items-start bg-white border border-solid backdrop-blur-[2px] border-stone-300 max-md:pr-5"
              placeholder="From"
            />
            <input
              type="number"
              value={millimetreTo}
              onChange={(e) => setMillimetreTo(e.target.value)}
              className="number-input justify-center items-center bg-white border border-solid border-stone-300 max-md:pr-5"
              placeholder="To"
            />
          </div>
        </div>
        </div>
        

        <DiamondList items={filteredDiamonds} />
      </div>
      <Navbar />
      <Footer />
    </div>
  );
}

export default Diamond;
