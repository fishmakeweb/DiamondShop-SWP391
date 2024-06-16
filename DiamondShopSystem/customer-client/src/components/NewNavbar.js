import React, { useState, useContext } from "react";
import { Link } from "react-router-dom";
import Cart from "./Cart";

const NewNavbar = () => {
  const [isCartOpen, setIsCartOpen] = useState(false);

  const toggleCartModal = () => {
    setIsCartOpen(!isCartOpen);
  };

  return (
    <>
      <nav className="bg-white w-full flex relative justify-between items-center mx-auto px-7 h-20">
        {/* Dropdown for small screens */}
        <div className="block md:hidden">
          <div className="relative inline-block text-left">
            <span className="rounded-md shadow-sm">
              <button
                className="inline-flex justify-center w-full px-4 py-2 text-sm font-medium leading-5 text-gray-700 transition duration-150 ease-in-out bg-white border border-gray-300 rounded-md hover:text-gray-500 focus:outline-none focus:border-blue-300 focus:shadow-outline-blue active:bg-gray-50 active:text-gray-800"
                type="button"
                aria-haspopup="true"
                aria-expanded="true"
                aria-controls="headlessui-menu-items-117"
                onClick={() => {
                  document
                    .getElementById("headlessui-menu-items-117")
                    .classList.toggle("hidden");
                }}
              >
                <span className="font-bold">MENU</span>
                <svg
                  className="w-5 h-5 ml-2 -mr-1"
                  viewBox="0 0 20 20"
                  fill="currentColor"
                >
                  <path
                    fillRule="evenodd"
                    d="M5.293 7.293a1 1 0 011.414 0L10 10.586l3.293-3.293a1 1 0 111.414 1.414l-4 4a1 1 0 01-1.414 0l-4-4a1 1 0 010-1.414z"
                    clipRule="evenodd"
                  />
                </svg>
              </button>
            </span>
            <div
              className="hidden dropdown-menu transition-all duration-300 transform origin-top-right -translate-y-2 scale-95"
              id="headlessui-menu-items-117"
            >
              <div
                className="absolute left-0 w-56 mt-2 origin-top-right bg-white border border-gray-200 divide-y divide-gray-100 rounded-md shadow-lg outline-none"
                aria-labelledby="headlessui-menu-button-1"
                role="menu"
              >
                <div className="px-4 py-3 font-semibold">
                  <p className="text-sm leading-5">HOME</p>
                </div>
                <div className="py-1">
                  <Link
                    to="/diamonds"
                    tabIndex={0}
                    className="text-gray-700 flex justify-between w-full px-4 py-2 text-sm leading-5 text-left font-semibold"
                    role="menuitem"
                  >
                    Diamond
                  </Link>
                  <Link
                    to="/jewelry/page/1"
                    tabIndex={1}
                    className="text-gray-700 flex justify-between w-full px-4 py-2 text-sm leading-5 text-left font-semibold"
                    role="menuitem"
                  >
                    Jewelry
                  </Link>
                  <a
                    href="javascript:void(0)"
                    tabIndex={2}
                    className="text-gray-700 flex justify-between w-full px-4 py-2 text-sm leading-5 text-left font-semibold"
                    role="menuitem"
                  >
                    New Release
                  </a>
                </div>
                <div className="py-1">
                  <a
                    href="javascript:void(0)"
                    tabIndex={3}
                    className="text-gray-700 flex justify-between w-full px-4 py-2 text-sm leading-5 text-left font-semibold"
                    role="menuitem"
                  >
                    Setting
                  </a>
                </div>
              </div>
            </div>
          </div>
        </div>
        {/* end dropdown */}
        {/* logo */}
        <div className="inline-flex">
          <a className="_o6689fn" href="/">
            <div className="md:flex custom-hide">
              <Link
                to="/diamonds"
                className="inline-block py-2 px-3 hover:bg-gray-200 rounded-full font-semibold"
              >
                <div className="flex items-center relative cursor-pointer whitespace-nowrap">
                  Diamond
                </div>
              </Link>
              <Link
                to="/jewelry/page/1"
                className="inline-block py-2 px-3 hover:bg-gray-200 rounded-full font-semibold"
              >
                <div className="flex items-center relative cursor-pointer whitespace-nowrap">
                  Jewelry
                </div>
              </Link>
              <a
                className="inline-block py-2 px-3 hover:bg-gray-200 rounded-full font-semibold"
                href="#"
              >
                <div className="flex items-center relative cursor-pointer whitespace-nowrap">
                  New release
                </div>
              </a>
            </div>
          </a>
        </div>
        {/* end logo */}
        {/* search bar */}
        <div className="hidden sm:block flex-shrink flex-grow-0 justify-start px-2">
          <div className="inline-block">
            <div className="inline-flex items-center max-w-full">
              <Link
                to="/"
                className="flex items-center pb-4 border-b border-b-gray-400 md:block custom-hide"
              >
                <h2 className="font-bold text-2xl">H E P H A E S T U S</h2>
              </Link>
            </div>
          </div>
        </div>
        {/* end search bar */}
        {/* login */}
        <div className="flex-initial">
          <div className="flex justify-end items-center relative">
            <div className="flex mr-4 items-center">
              <a
                className="inline-block py-2 px-3 hover:bg-gray-200 rounded-full"
                href="#"
              >
                <div className="flex items-center relative cursor-pointer whitespace-nowrap">
                  <button
                    className="flex items-center flex-grow-0 flex-shrink pl-2 relative w-60 border rounded-full px-1 py-1"
                    type="button"
                  >
                    <div className="block flex-grow flex-shrink overflow-hidden">
                      Start your search
                    </div>
                    <div className="flex items-center justify-center relative h-8 w-8 rounded-full">
                      <svg
                        viewBox="0 0 32 32"
                        xmlns="http://www.w3.org/2000/svg"
                        aria-hidden="true"
                        role="presentation"
                        focusable="false"
                        style={{
                          display: "block",
                          fill: "none",
                          height: 12,
                          width: 12,
                          stroke: "currentcolor",
                          strokeWidth: "5.33333",
                          overflow: "visible",
                        }}
                      >
                        <g fill="none">
                          <path d="m13 24c6.0751322 0 11-4.9248678 11-11 0-6.07513225-4.9248678-11-11-11-6.07513225 0-11 4.92486775-11 11 0 6.0751322 4.92486775 11 11 11zm8-3 9 9" />
                        </g>
                      </svg>
                    </div>
                  </button>
                </div>
              </a>
              <div className="block relative">
                <button
                  type="button"
                  className="inline-block py-2 px-3 hover:bg-gray-200 rounded-full relative"
                  onClick={toggleCartModal}
                >
                  <div className="flex items-center h-5">
                    <div className="_xpkakx">
                      <svg
                        className="flex-1 w-8 h-6 fill-current"
                        viewBox="0 0 24 24"
                      >
                        <path d="M17,18C15.89,18 15,18.89 15,20A2,2 0 0,0 17,22A2,2 0 0,0 19,20C19,18.89 18.1,18 17,18M1,2V4H3L6.6,11.59L5.24,14.04C5.09,14.32 5,14.65 5,15A2,2 0 0,0 7,17H19V15H7.42C7.28,15 7.17,14.89 7.17,14.75L7.2,14.65L8.1,13H15.55C16.3,13 16.96,12.58 17.3,11.97L20.88,5.59C21,5.39 21.06,5.2 21.06,5A1,1 0 0,0 20.06,4H5.21L4.27,2H1Z" />
                      </svg>
                      {/* {cart.length > 0 && (
                        <div className="absolute top-0 right-0 w-2 h-2 bg-[#B6A69D] rounded-full"></div>
                      )} */}
                    </div>
                  </div>
                </button>
              </div>
            </div>
            <div className="block">
              <div className="inline relative">
                <button
                  type="button"
                  className="inline-flex items-center relative px-2 border rounded-full hover:shadow-lg"
                >
                  <div className="pl-1">
                    <svg
                      className="w-6 h-6 text-gray-700"
                      viewBox="0 0 32 32"
                      xmlns="http://www.w3.org/2000/svg"
                    >
                      <g fill="none">
                        <path
                          d="m16 2c3.866 0 7 3.134 7 7v5h1c1.1046 0 2 .89543 2 2v13c0 1.1046-.8954 2-2 2h-16c-1.10457 0-2-.8954-2-2v-13c0-1.10457.89543-2 2-2h1v-5c0-3.866 3.13401-7 7-7zm0 18c-2.2091 0-4 1.7909-4 4h8c0-2.2091-1.7909-4-4-4zm0-13c-2.2091 0-4 1.7909-4 4v5h8v-5c0-2.2091-1.7909-4-4-4z"
                          fill="currentColor"
                        />
                      </g>
                    </svg>
                  </div>
                </button>
              </div>
            </div>
          </div>
        </div>
        {/* end login */}
      </nav>
      <Cart
        isOpen={isCartOpen}
        onClose={toggleCartModal}
      />
    </>
  );
};

export default NewNavbar;
