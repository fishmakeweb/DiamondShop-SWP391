import React, { useState, useEffect } from "react";
import "./Slider.css";

const Slider = () => {
  const [currentSlide, setCurrentSlide] = useState(0);

  useEffect(() => {
    const loopSlider = setInterval(() => {
      setCurrentSlide((prevSlide) => (prevSlide === 0 ? 1 : 0));
    }, 6000); // Change interval for slides

    return () => clearInterval(loopSlider);
  }, []);

  return (
    <>
      <div className="sliderAx">
        <div
          className={`container ${
            currentSlide === 0 ? "slide-in" : "slide-out"
          }`}
        >
          <div
            className="bg-cover bg-center h-full w-full"
            style={{
              backgroundImage:
                "url(https://jewelry.ge/assets/%E1%83%A5%E1%83%90%E1%83%95%E1%83%94%E1%83%A0%E1%83%94%E1%83%91%E1%83%98/%E1%83%96%E1%83%9D%E1%83%9B%E1%83%90%E1%83%A8%E1%83%94%E1%83%AA%E1%83%95%E1%83%9A%E1%83%98%E1%83%9A%E1%83%98/1_1_1.jpg)",
              backgroundSize: "cover",
              backgroundPosition: "center",
              width: "100vw" /* Full viewport width */,
              height: "100vh" /* Full viewport height */,
            }}
          >
            <div className="content md:w-1/2">
              <p className="text-3xl font-bold">Summer has arrived.</p>
              <p className="text-2xl mb-10 leading-none">
                Shop for our new releases starting today.
              </p>
              <a
                href="#"
                className="border border-collapse border-white py-4 px-8 text-white font-bold uppercase text-xs rounded hover:bg-gray-200 hover:text-gray-800"
              >
                SHOP NOW
              </a>
            </div>
          </div>
        </div>
        <div
          className={`container ${
            currentSlide === 1 ? "slide-in" : "slide-out"
          }`}
        >
          <div
            className="bg-cover bg-center h-full w-full"
            style={{
              backgroundImage:
                "url(https://i.pinimg.com/originals/88/d7/17/88d7179b77df5a3d9ca76c8d079adba0.jpg)",
              backgroundSize: "cover",
              backgroundPosition: "center",
              width: "100vw" /* Full viewport width */,
              height: "100vh" /* Full viewport height */,
            }}
          >
            <div className="content md:w-1/2">
              <p className="text-3xl font-bold">Summer has arrived.</p>
              <p className="text-2xl mb-10 leading-none">
                Shop for our new releases starting today.
              </p>
              <a
                href="#"
                className="border border-collapse border-white py-4 px-8 text-white font-bold uppercase text-xs rounded hover:bg-gray-200 hover:text-gray-800"
              >
                SHOP NOW
              </a>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default Slider;
