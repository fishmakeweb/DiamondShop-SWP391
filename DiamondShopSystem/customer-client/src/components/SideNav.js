import React, { useState } from "react";
import { Link } from "react-router-dom";

const SideNav = ({ isOpen }) => {
  // States to control dropdown visibility
  const [isProductsDropdownOpen, setProductsDropdownOpen] = useState(false);
  const [isDeliveryDropdownOpen, setDeliveryDropdownOpen] = useState(false);

  // Toggle functions
  const toggleProductsDropdown = () =>
    setProductsDropdownOpen(!isProductsDropdownOpen);
  const toggleDeliveryDropdown = () =>
    setDeliveryDropdownOpen(!isDeliveryDropdownOpen);
  return (
    <>
      {/*sidenav */}
      <div
        className={`fixed top-0 left-0 w-64 h-full bg-[#f8f4f3] p-4 z-40 transition-transform duration-300 ${
          isOpen ? "translate-x-0" : "-translate-x-full"
        }`}
      >
        <a
          href="#"
          className="flex items-center pb-4 border-b border-b-gray-800"
        >
          <h2 className="font-bold text-2xl">H E P H A E S T U S</h2>
        </a>
        <ul className="mt-4">
          <span className="text-gray-400 font-bold">SALE STAFF</span>
          <li className="mb-1 group">
            <a
              href=""
              className="flex font-semibold items-center py-2 px-4 text-gray-900 hover:bg-gray-950 hover:text-gray-100 rounded-md group-[.active]:bg-gray-800 group-[.active]:text-white group-[.selected]:bg-gray-950 group-[.selected]:text-gray-100"
            >
              <i className="ri-home-2-line mr-3 text-lg" />
              <span className="text-sm">Dashboard</span>
            </a>
          </li>
          <li className="mb-1 group">
            <button
              onClick={toggleProductsDropdown}
              className="flex w-full font-semibold items-center py-2 px-4 text-gray-900 hover:bg-gray-950 hover:text-gray-100 rounded-md"
            >
              <i className="bx bx-user mr-3 text-lg" />
              <span className="text-sm">Products</span>
              <i
                className={`ri-arrow-right-s-line ml-auto ${
                  isProductsDropdownOpen ? "rotate-90" : ""
                }`}
              />
            </button>
            <ul
              className={`pl-7 mt-2 ${
                isProductsDropdownOpen ? "block" : "hidden"
              }`}
            >
              <li className="mb-4">
                <Link
                  to="/adddiamond"
                  href=""
                  className="text-gray-900 text-sm flex items-center hover:text-[#f84525]"
                >
                  Diamond
                </Link>
              </li>
              <li className="mb-4">
                <Link
                  to="/addjewelry"
                  href=""
                  className="text-gray-900 text-sm flex items-center hover:text-[#f84525]"
                >
                  Jewelry
                </Link>
              </li>
            </ul>
          </li>
          <li className="mb-1 group">
            <button
              onClick={toggleDeliveryDropdown}
              className="flex w-full font-semibold items-center py-2 px-4 text-gray-900 hover:bg-gray-950 hover:text-gray-100 rounded-md"
            >
              <i className="bx bxl-blogger mr-3 text-lg" />
              <span className="text-sm">Delivery</span>
              <i
                className={`ri-arrow-right-s-line ml-auto ${
                  isDeliveryDropdownOpen ? "rotate-90" : ""
                }`}
              />
            </button>
            <ul
              className={`pl-7 mt-2 ${
                isDeliveryDropdownOpen ? "block" : "hidden"
              }`}
            >
              <li className="mb-4">
                <a
                  href=""
                  className="text-gray-900 text-sm flex items-center hover:text-[#f84525]"
                >
                  All
                </a>
              </li>
              <li className="mb-4">
                <a
                  href=""
                  className="text-gray-900 text-sm flex items-center hover:text-[#f84525]"
                >
                  On-going
                </a>
              </li>
              <li className="mb-4">
                <a
                  href=""
                  className="text-gray-900 text-sm flex items-center hover:text-[#f84525]"
                >
                  Delivered
                </a>
              </li>
            </ul>
          </li>
          <span className="text-gray-400 font-bold">PERSONAL</span>
          <li className="mb-1 group">
            <a
              href=""
              className="flex font-semibold items-center py-2 px-4 text-gray-900 hover:bg-gray-950 hover:text-gray-100 rounded-md group-[.active]:bg-gray-800 group-[.active]:text-white group-[.selected]:bg-gray-950 group-[.selected]:text-gray-100"
            >
              <i className="bx bx-bell mr-3 text-lg" />
              <span className="text-sm">Notifications</span>
              <span className="md:block px-2 py-0.5 ml-auto text-xs font-medium tracking-wide text-red-600 bg-red-200 rounded-full">
                5
              </span>
            </a>
          </li>
          <li className="mb-1 group">
            <a
              href=""
              className="flex font-semibold items-center py-2 px-4 text-gray-900 hover:bg-gray-950 hover:text-gray-100 rounded-md group-[.active]:bg-gray-800 group-[.active]:text-white group-[.selected]:bg-gray-950 group-[.selected]:text-gray-100"
            >
              <i className="bx bx-envelope mr-3 text-lg" />
              <span className="text-sm">Messages</span>
              <span className="md:block px-2 py-0.5 ml-auto text-xs font-medium tracking-wide text-green-600 bg-green-200 rounded-full">
                2 New
              </span>
            </a>
          </li>
          <li className="mb-1 group">
            <a
              href=""
              className="flex font-semibold items-center py-2 px-4 text-gray-900 hover:bg-gray-950 hover:text-gray-100 rounded-md group-[.active]:bg-gray-800 group-[.active]:text-white group-[.selected]:bg-gray-950 group-[.selected]:text-gray-100"
            >
              <svg
                xmlns="http://www.w3.org/2000/svg"
                width={24}
                height={24}
                viewBox="0 0 24 24"
                fill="none"
              >
                <path
                  d="M11.9996 8C13.0605 8 14.0779 8.42143 14.8281 9.17157C15.5782 9.92172 15.9996 10.9391 15.9996 12C15.9996 13.0609 15.5782 14.0783 14.8281 14.8284C14.0779 15.5786 13.0605 16 11.9996 16C10.9388 16 9.92137 15.5786 9.17122 14.8284C8.42108 14.0783 7.99965 13.0609 7.99965 12C7.99965 10.9391 8.42108 9.92172 9.17122 9.17157C9.92137 8.42143 10.9388 8 11.9996 8ZM11.9996 10C11.4692 10 10.9605 10.2107 10.5854 10.5858C10.2104 10.9609 9.99965 11.4696 9.99965 12C9.99965 12.5304 10.2104 13.0391 10.5854 13.4142C10.9605 13.7893 11.4692 14 11.9996 14C12.5301 14 13.0388 13.7893 13.4139 13.4142C13.7889 13.0391 13.9996 12.5304 13.9996 12C13.9996 11.4696 13.7889 10.9609 13.4139 10.5858C13.0388 10.2107 12.5301 10 11.9996 10ZM9.99965 22C9.74965 22 9.53965 21.82 9.49965 21.58L9.12965 18.93C8.49965 18.68 7.95965 18.34 7.43965 17.94L4.94965 18.95C4.72965 19.03 4.45965 18.95 4.33965 18.73L2.33965 15.27C2.27844 15.167 2.25687 15.0452 2.27895 14.9274C2.30104 14.8096 2.36527 14.7039 2.45965 14.63L4.56965 12.97L4.49965 12L4.56965 11L2.45965 9.37C2.36527 9.29613 2.30104 9.19042 2.27895 9.07263C2.25687 8.95484 2.27844 8.83304 2.33965 8.73L4.33965 5.27C4.45965 5.05 4.72965 4.96 4.94965 5.05L7.43965 6.05C7.95965 5.66 8.49965 5.32 9.12965 5.07L9.49965 2.42C9.53965 2.18 9.74965 2 9.99965 2H13.9996C14.2496 2 14.4596 2.18 14.4996 2.42L14.8696 5.07C15.4996 5.32 16.0396 5.66 16.5596 6.05L19.0496 5.05C19.2696 4.96 19.5396 5.05 19.6596 5.27L21.6596 8.73C21.7896 8.95 21.7297 9.22 21.5396 9.37L19.4296 11L19.4996 12L19.4296 13L21.5396 14.63C21.7297 14.78 21.7896 15.05 21.6596 15.27L19.6596 18.73C19.5396 18.95 19.2696 19.04 19.0496 18.95L16.5596 17.95C16.0396 18.34 15.4996 18.68 14.8696 18.93L14.4996 21.58C14.4596 21.82 14.2496 22 13.9996 22H9.99965ZM11.2496 4L10.8796 6.61C9.67965 6.86 8.61965 7.5 7.84965 8.39L5.43965 7.35L4.68965 8.65L6.79965 10.2C6.39965 11.3667 6.39965 12.6333 6.79965 13.8L4.67965 15.36L5.42965 16.66L7.85965 15.62C8.62965 16.5 9.67965 17.14 10.8696 17.38L11.2396 20H12.7596L13.1296 17.39C14.3196 17.14 15.3696 16.5 16.1396 15.62L18.5696 16.66L19.3196 15.36L17.1996 13.81C17.5996 12.64 17.5996 11.37 17.1996 10.2L19.3096 8.65L18.5596 7.35L16.1496 8.39C15.3639 7.48032 14.298 6.85767 13.1196 6.62L12.7496 4H11.2496Z"
                  fill="#757575"
                />
              </svg>
              <span className="text-sm ml-2">Setting</span>
            </a>
          </li>
        </ul>
      </div>
      <div className="fixed top-0 left-0 w-full h-full bg-black/50 z-40 md:hidden sidebar-overlay" />
      {/* end sidenav */}
    </>
  );
};
export default SideNav;
