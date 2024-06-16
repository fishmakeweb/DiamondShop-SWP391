import React, { useEffect } from "react";
import $ from "jquery";

const Slider = () => {
  useEffect(() => {
    let cont = 0;
    let xx;

    function loopSlider() {
      xx = setInterval(function () {
        switch (cont) {
          case 0:
            $("#slider-1").fadeOut(400);
            $("#slider-2").delay(400).fadeIn(400);
            $("#sButton1").removeClass("bg-purple-800");
            $("#sButton2").addClass("bg-purple-800");
            cont = 1;
            break;
          case 1:
            $("#slider-2").fadeOut(400);
            $("#slider-1").delay(400).fadeIn(400);
            $("#sButton2").removeClass("bg-purple-800");
            $("#sButton1").addClass("bg-purple-800");
            cont = 0;
            break;
          default:
            break;
        }
      }, 8000);
    }

    function reinitLoop(time) {
      clearInterval(xx);
      setTimeout(loopSlider, time);
    }

    window.sliderButton1 = function () {
      $("#slider-2").fadeOut(400);
      $("#slider-1").delay(400).fadeIn(400);
      $("#sButton2").removeClass("bg-purple-800");
      $("#sButton1").addClass("bg-purple-800");
      reinitLoop(4000);
      cont = 0;
    };

    window.sliderButton2 = function () {
      $("#slider-1").fadeOut(400);
      $("#slider-2").delay(400).fadeIn(400);
      $("#sButton1").removeClass("bg-purple-800");
      $("#sButton2").addClass("bg-purple-800");
      reinitLoop(4000);
      cont = 1;
    };

    $(document).ready(function () {
      $("#slider-2").hide();
      $("#sButton1").addClass("bg-purple-800");
      loopSlider();
    });

    return () => {
      clearInterval(xx);
    };
  }, []);

  return (
    <>
      <div className="sliderAx h-screen">
        <div id="slider-1" className="container mx-auto">
          <div
            className="bg-cover bg-center h-auto text-white py-64 px-10 object-fill"
            style={{
              backgroundImage:
                "url(https://jewelry.ge/assets/%E1%83%A5%E1%83%90%E1%83%95%E1%83%94%E1%83%A0%E1%83%94%E1%83%91%E1%83%98/%E1%83%96%E1%83%9D%E1%83%9B%E1%83%90%E1%83%A8%E1%83%94%E1%83%AA%E1%83%95%E1%83%9A%E1%83%98%E1%83%9A%E1%83%98/1_1_1.jpg)",
            }}
          >
            <div className="md:w-1/2">
              {/* <p className="font-bold text-sm uppercase">Services</p> */}
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
          <br />
        </div>
        <div id="slider-2" className="container mx-auto">
          <div
            className="bg-cover bg-top h-auto text-white py-64 px-10 object-fill"
            style={{
              backgroundImage:
                "url(https://i.pinimg.com/originals/88/d7/17/88d7179b77df5a3d9ca76c8d079adba0.jpg)",
            }}
          >
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
          <br />
        </div>
      </div>
      {/* <div className="flex justify-between w-12 mx-auto pb-2">
        <button
          id="sButton1"
          onClick={window.sliderButton1}
          className="bg-purple-400 rounded-full w-4 pb-2"
        />
        <button
          id="sButton2"
          onClick={window.sliderButton2}
          className="bg-purple-400 rounded-full w-4 p-2"
        />
      </div> */}
    </>
  );
};

export default Slider;
