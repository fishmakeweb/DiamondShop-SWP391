import React, { useEffect } from "react";
// import { Link } from 'react-router-dom';
import Footer from '../components/Footer';
import NewNavbar from "../components/NewNavbar";

function Homepage() {
  useEffect(() => {
    document.title = "Hephaetus"
  }, []);
  return (
    <div className="flex flex-col bg-white">
            <NewNavbar />
           
      <div className="self-center mt-11 text-4xl text-center text-black max-md:mt-10">
        Shop by category
      </div>
      <div className="self-center mt-6 text-3xl italic text-center text-black">
        Indulge in what we offer.
      </div>
      <div className="self-center px-5 mt-20 w-full max-w-[1182px] max-md:mt-10 max-md:max-w-full">
        <div className="flex gap-5 max-md:flex-col max-md:gap-0">
          <div className="flex flex-col w-3/12 max-md:ml-0 max-md:w-full">
            {/* wedding ring page */}
            <div className="flex flex-col grow justify-center max-md:mt-10">
              <div className="flex flex-col justify-center shadow-sm bg-stone-300">
                <img
                  loading="lazy"
                  srcSet="https://diamond-shop-swp-391.vercel.app/img/jewelry/DC-1.webp"
                  alt="wedding-ring-sample"
                  className="w-full aspect-[0.9]"
                />
              </div>
              <div className="self-center mt-5 text-2xl text-center text-black">
                Diamond Chains
              </div>
            </div>
          </div>
          <div className="flex flex-col ml-5 w-3/12 max-md:ml-0 max-md:w-full">
            {/* Engagement Ring page */}
            <div className="flex flex-col grow justify-center max-md:mt-10">
              <div className="flex flex-col justify-center shadow-sm bg-stone-300">
                <img
                  loading="lazy"
                  srcSet="img\homepage\eng-ring-sample.png"//item 2
                  alt="eng-ring-sample"
                  className="w-full aspect-[0.9]"
                />
              </div>
              <div className="self-center mt-5 text-2xl text-center text-black">
                Engagement Ring
              </div>
            </div>
          </div>
          <div className="flex flex-col ml-5 w-3/12 max-md:ml-0 max-md:w-full">
            {/* Diamond page */}
            <div className="flex flex-col grow justify-center max-md:mt-10">
              <div className="flex flex-col justify-center shadow-sm bg-stone-300">
                <img
                  loading="lazy"
                  srcSet="img\homepage\diamond.png"//item 3
                  alt="diamond"
                  className="w-full aspect-[0.9]"
                />
              </div>
              <div className="self-center mt-5 text-2xl text-center text-black">
                Diamond
              </div>
            </div>
          </div>
          <div className="flex flex-col ml-5 w-3/12 max-md:ml-0 max-md:w-full">
            {/* Jewelry page */}
            <div className="flex flex-col grow justify-center max-md:mt-10">
              <div className="flex flex-col justify-center shadow-sm bg-stone-300">
                <img
                  loading="lazy"
                  srcSet="img\homepage\jewerly-sample.png"//item 4
                  alt="jewerly-sample"
                  className="w-full aspect-[0.9]"
                />
              </div>
              <div className="self-center mt-5 text-2xl text-center text-black">
                Jewelry
              </div>
            </div>
          </div>
        </div>
      </div>
      <div className="mt-16 w-full bg-stone-950 max-md:mt-10 max-md:max-w-full">
        <div className="flex gap-5 max-md:flex-col max-md:gap-0">
          <div className="flex flex-col w-[39%] max-md:ml-0 max-md:w-full">
            <img
              loading="lazy"
              srcSet="img\homepage\Rectangle.png"//article img
              alt="Article-img"
              className="grow w-full aspect-[0.88] max-md:mt-10 max-md:max-w-full"
            />
          </div>
          <div className="flex flex-col ml-5 w-[61%] max-md:ml-0 max-md:w-full">
            <div className="flex flex-col px-5 mt-11 text-white max-md:mt-10 max-md:max-w-full">
              <div className="text-xl max-md:max-w-full">
                ARTICLE • SUMMER 2024
              </div>
              <div className="mt-20 text-4xl font-bold max-md:mt-10 max-md:max-w-full">
                During the golden hour.
              </div>
              <div className="mt-8 text-2xl font-[435] max-md:max-w-full">
                Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do
                eiusmod tempor incididunt ut labore et dolore magna aliqua.
                Metus vulputate eu scelerisque felis imperdiet proin fermentum.
                Cras semper auctor neque vitae tempus quam pellentesque.
                Elementum sagittis vitae et leo duis. <br />
                <br />
                Elementum sagittis vitae et leo duis. Libero nunc consequat
                interdum varius. Habitant morbi tristique senectus et netus et
                malesuada fames ac.{" "}
              </div>
              <div className="justify-center self-start hover:bg-white hover:text-black transition duration-300 p-5 mt-12 text-xl text-center border border-white border-solid max-md:mt-10">
                READ MORE
              </div>
            </div>
          </div>
        </div>
      </div>
      <Footer />
    </div>
  );
}


export default Homepage;


