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
      <div className="sliderAx h-auto">
        <div id="slider-1" className="container mx-auto">
          <div
            className="bg-cover bg-center h-auto text-white py-24 px-10 object-fill"
            style={{
              backgroundImage:
                "url(https://images.unsplash.com/photo-1544427920-c49ccfb85579?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1422&q=80)",
            }}
          >
            <div className="md:w-1/2">
              <p className="font-bold text-sm uppercase">Services</p>
              <p className="text-3xl font-bold">Hello world</p>
              <p className="text-2xl mb-10 leading-none">
                Carousel with TailwindCSS and jQuery
              </p>
              <a
                href="#"
                className="bg-purple-800 py-4 px-8 text-white font-bold uppercase text-xs rounded hover:bg-gray-200 hover:text-gray-800"
              >
                Contact us
              </a>
            </div>
          </div>
          <br />
        </div>
        <div id="slider-2" className="container mx-auto">
          <div
            className="bg-cover bg-top h-auto text-white py-24 px-10 object-fill"
            style={{
              backgroundImage:
                "url(https://images.unsplash.com/photo-1544144433-d50aff500b91?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1350&q=80)",
            }}
          >
            <p className="font-bold text-sm uppercase">Services</p>
            <p className="text-3xl font-bold">Hello world</p>
            <p className="text-2xl mb-10 leading-none">
              Carousel with TailwindCSS and jQuery
            </p>
            <a
              href="#"
              className="bg-purple-800 py-4 px-8 text-white font-bold uppercase text-xs rounded hover:bg-gray-200 hover:text-gray-800"
            >
              Contact us
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
