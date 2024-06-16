import React from "react";
import { Link } from "react-router-dom";

const NewNavbar = () => {
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
                  <Link to="/diamonds"
                    href="javascript:void(0)"
                    tabIndex={0}
                    className="text-gray-700 flex justify-between w-full px-4 py-2 text-sm leading-5 text-left font-semibold"
                    role="menuitem"
                  >
                    Diamond
                  </Link>
                  <Link to="/jewelry/page/1"
                    href="javascript:void(0)"
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
              <Link to="/diamonds"
                className="inline-block py-2 px-3 hover:bg-gray-200 rounded-full font-semibold"
              >
                <div className="flex items-center relative cursor-pointer whitespace-nowrap">
                  Diamond
                </div>
              </Link>
              <Link to="/jewelry/page/1"
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
              <Link to="/"
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
                >
                  <div className="flex items-center h-5">
                    <div className="_xpkakx">
                      <svg
                        className="flex-1 w-8 h-6 fill-current"
                        viewBox="0 0 24 24"
                      >
                        <path d="M17,18C15.89,18 15,18.89 15,20A2,2 0 0,0 17,22A2,2 0 0,0 19,20C19,18.89 18.1,18 17,18M1,2V4H3L6.6,11.59L5.24,14.04C5.09,14.32 5,14.65 5,15A2,2 0 0,0 7,17H19V15H7.42A0.25,0.25 0 0,1 7.17,14.75C7.17,14.7 7.18,14.66 7.2,14.63L8.1,13H15.55C16.3,13 16.96,12.58 17.3,11.97L20.88,5.5C20.95,5.34 21,5.17 21,5A1,1 0 0,0 20,4H5.21L4.27,2M7,18C5.89,18 5,18.89 5,20A2,2 0 0,0 7,22A2,2 0 0,0 9,20C9,18.89 8.1,18 7,18Z" />
                      </svg>
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
                      viewBox="0 0 32 32"
                      xmlns="http://www.w3.org/2000/svg"
                      aria-hidden="true"
                      role="presentation"
                      focusable="false"
                      style={{
                        display: "block",
                        fill: "none",
                        height: 16,
                        width: 16,
                        stroke: "currentcolor",
                        strokeWidth: 3,
                        overflow: "visible",
                      }}
                    >
                      <g fill="none" fillRule="nonzero">
                        <path d="m2 16h28" />
                        <path d="m2 24h28" />
                        <path d="m2 8h28" />
                      </g>
                    </svg>
                  </div>
                  <div className="block flex-grow-0 flex-shrink-0 h-10 w-12 pl-5">
                    <svg
                      viewBox="0 0 32 32"
                      xmlns="http://www.w3.org/2000/svg"
                      aria-hidden="true"
                      role="presentation"
                      focusable="false"
                      style={{
                        display: "block",
                        height: "100%",
                        width: "100%",
                        fill: "currentcolor",
                      }}
                    >
                      <path d="m16 .7c-8.437 0-15.3 6.863-15.3 15.3s6.863 15.3 15.3 15.3 15.3-6.863 15.3-15.3-6.863-15.3-15.3-15.3zm0 28c-4.021 0-7.605-1.884-9.933-4.81a12.425 12.425 0 0 1 6.451-4.4 6.507 6.507 0 0 1 -3.018-5.49c0-3.584 2.916-6.5 6.5-6.5s6.5 2.916 6.5 6.5a6.513 6.513 0 0 1 -3.019 5.491 12.42 12.42 0 0 1 6.452 4.4c-2.328 2.925-5.912 4.809-9.933 4.809z" />
                    </svg>
                  </div>
                </button>
              </div>
            </div>
          </div>
        </div>
        {/* end login */}
      </nav>

      <style jsx>{`
        @media (max-width: 996px) {
          .custom-hide {
            display: none;
          }
        }
        .dropdown:focus-within .dropdown-menu {
          opacity: 1;
          transform: translate(0) scale(1);
          visibility: visible;
        }
      `}</style>
    </>
  );
};

export default NewNavbar;
