import React, { useState, useEffect } from "react";
import axios from '../axios';
import NewNavbar from "./NewNavbar";

const NewDiamond = () => {
  const [diamonds, setDiamonds] = useState([]);
  const [searchTerm, setSearchTerm] = useState("");
  const [sortBy, setSortBy] = useState("");

  useEffect(() => {
    fetchDiamonds();
  }, []);

  const fetchDiamonds = async () => {
    try {
      const response = await axios.get('/diamonds'); // Update the API endpoint as needed
      setDiamonds(response.data);
    } catch (error) {
      console.error('Error fetching diamond data:', error);
    }
  };

  const handleSearchChange = (event) => {
    setSearchTerm(event.target.value.toLowerCase());
  };
  const handleSortChange = (event) => {
    setSortBy(event.target.value);
    sortDiamonds(event.target.value);
  };

  const sortDiamonds = (criteria) => {
    let sortedDiamonds = [...diamonds];
    switch (criteria) {
      case "priceAsc":
        sortedDiamonds.sort((a, b) => a.price - b.price);
        break;
      case "priceDesc":
        sortedDiamonds.sort((a, b) => b.price - a.price);
        break;
      case "caratAsc":
        sortedDiamonds.sort((a, b) => a.carat.carat - b.carat.carat);
        break;
      case "caratDesc":
        sortedDiamonds.sort((a, b) => b.carat.carat - a.carat.carat);
        break;
      case "colorAsc":
        sortedDiamonds.sort((a, b) => a.color.colorDescription.localeCompare(b.color.colorDescription));
        break;
      case "colorDesc":
        sortedDiamonds.sort((a, b) => b.color.colorDescription.localeCompare(a.color.colorDescription));
        break;
      case "clarityAsc":
        sortedDiamonds.sort((a, b) => a.clarity.clarityDescription.localeCompare(b.clarity.clarityDescription));
        break;
      case "clarityDesc":
        sortedDiamonds.sort((a, b) => b.clarity.clarityDescription.localeCompare(a.clarity.clarityDescription));
        break;
      case "cutAsc":
        sortedDiamonds.sort((a, b) => a.cut.cutDescription.localeCompare(b.cut.cutDescription));
        break;
      case "cutDesc":
        sortedDiamonds.sort((a, b) => b.cut.cutDescription.localeCompare(a.cut.cutDescription));
        break;
      default:
        break;
    }
    setDiamonds(sortedDiamonds);
  };
  const filteredDiamonds = diamonds.filter(diamond =>
    diamond.shape.shapeDescription.toLowerCase().includes(searchTerm) ||
    diamond.color.colorDescription.toLowerCase().includes(searchTerm) ||
    diamond.price.toString().includes(searchTerm)
  );

  return (
    <>
      <NewNavbar />
      <div className="max-w-full mx-auto">
        <div className="relative overflow-x-auto shadow-md sm:rounded-lg">
          <div className="p-4">
            <label htmlFor="table-search" className="sr-only">
              Search
            </label>
            <button
              type="button"
              className="inline-flex items-center gap-x-2 rounded-md bg-transparent px-3.5 py-2.5 text-sm font-semibold text-gray-400 shadow-sm hover:bg-gray-400 hover:text-white focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-gray-600"
            >
              <span className="">
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  fill="none"
                  viewBox="0 0 24 24"
                  strokeWidth="1.5"
                  stroke="currentColor"
                  className="h-6 w-6"
                >
                  <path
                    strokeLinecap="round"
                    strokeLinejoin="round"
                    d="M6.633 10.25c.806 0 1.533-.446 2.031-1.08a9.041 9.041 0 0 1 2.861-2.4c.723-.384 1.35-.956 1.653-1.715a4.498 4.498 0 0 0 .322-1.672V2.75a.75.75 0 0 1 .75-.75 2.25 2.25 0 0 1 2.25 2.25c0 1.152-.26 2.243-.723 3.218-.266.558.107 1.282.725 1.282m0 0h3.126c1.026 0 1.945.694 2.054 1.715.045.422.068.85.068 1.285a11.95 11.95 0 0 1-2.649 7.521c-.388.482-.987.729-1.605.729H13.48c-.483 0-.964-.078-1.423-.23l-3.114-1.04a4.501 4.501 0 0 0-1.423-.23H5.904m10.598-9.75H14.25M5.904 18.5c.083.205.173.405.27.602.197.4-.078.898-.523.898h-.908c-.889 0-1.713-.518-1.972-1.368a12 12 0 0 1-.521-3.507c0-1.553.295-3.036.831-4.398C3.387 9.953 4.167 9.5 5 9.5h1.053c.472 0 .745.556.5.96a8.958 8.958 0 0 0-1.302 4.665c0 1.194.232 2.333.654 3.375Z"
                  />
                </svg>
              </span>
              <span className="">Need Consultant?</span>
            </button>
            <button
              type="button"
              className="inline-flex items-center gap-x-2 rounded-md bg-transparent px-3.5 py-2.5 text-sm font-semibold text-gray-400 shadow-sm hover:bg-gray-400 hover:text-white focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-gray-600"
            >
              <span className="">
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  fill="none"
                  viewBox="0 0 24 24"
                  strokeWidth="1.5"
                  stroke="currentColor"
                  className="w-6 h-6"
                >
                  <path
                    strokeLinecap="round"
                    strokeLinejoin="round"
                    d="M17.593 3.322c1.1.128 1.907 1.077 1.907 2.185V21L12 17.25 4.5 21V5.507c0-1.108.806-2.057 1.907-2.185a48.507 48.507 0 0 1 11.186 0Z"
                  />
                </svg>
              </span>
              <span className="">4C Diamonds</span>
            </button>
            <button
              type="button"
              className="inline-flex items-center gap-x-2 rounded-md bg-transparent px-3.5 py-2.5 text-sm font-semibold text-gray-400 shadow-sm hover:bg-gray-400 hover:text-white focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-gray-600"
            >
              <span className="">
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  fill="none"
                  viewBox="0 0 24 24"
                  strokeWidth="1.5"
                  stroke="currentColor"
                  className="w-6 h-6"
                >
                  <path
                    strokeLinecap="round"
                    strokeLinejoin="round"
                    d="M17.593 3.322c1.1.128 1.907 1.077 1.907 2.185V21L12 17.25 4.5 21V5.507c0-1.108.806-2.057 1.907-2.185a48.507 48.507 0 0 1 11.186 0Z"
                  />
                </svg>
              </span>
              <span className="">FILTER BY</span>
              <select
              className="inline-flexitems-center gap-x-2 rounded-md bg-transparent px-3.5 py-2.5 text-sm font-semibold text-gray-400 shadow-sm hover:bg-gray-400 hover:text-white focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-gray-600"
              onChange={handleSortChange}
              value={sortBy}
            >
              <option value="">Sort By</option>
              <option value="priceAsc">Price: Low to High</option>
              <option value="priceDesc">Price: High to Low</option>
              <option value="caratAsc">Carat: Low to High</option>
              <option value="caratDesc">Carat: High to Low</option>
              <option value="colorAsc">Color: A to Z</option>
              <option value="colorDesc">Color: Z to A</option>
              <option value="clarityAsc">Clarity: A to Z</option>
              <option value="clarityDesc">Clarity: Z to A</option>
              <option value="cutAsc">Cut: A to Z</option>
              <option value="cutDesc">Cut: Z to A</option>
            </select>
            </button>
            
            

            <div className="relative mt-1">
              <div className="absolute inset-y-0 left-0 flex items-center pl-3 pointer-events-none">
                <svg
                  className="w-5 h-5 text-gray-500 dark:text-gray-400"
                  fill="currentColor"
                  viewBox="0 0 20 20"
                  xmlns="http://www.w3.org/2000/svg"
                >
                  <path
                    fillRule="evenodd"
                    d="M8 4a4 4 0 100 8 4 4 0 000-8zM2 8a6 6 0 1110.89 3.476l4.817 4.817a1 1 0 01-1.414 1.414l-4.816-4.816A6 6 0 012 8z"
                    clipRule="evenodd"
                  />
                </svg>
              </div>
              <input
                type="text"
                id="table-search"
                value={searchTerm}
                onChange={handleSearchChange}
                className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-80 pl-10 p-2.5  dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                placeholder="Search for items"
              />
            </div>
          </div>
          <table className="w-full text-sm text-center text-gray-500 dark:text-gray-400">
            <thead className="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
              <tr>
                <th scope="col" className="px-6 py-3">
                  Shape
                </th>
                <th scope="col" className="px-6 py-3">
                  Carat
                </th>
                <th scope="col" className="px-6 py-3">
                  Color
                </th>
                <th scope="col" className="px-6 py-3">
                  Clarity
                </th>
                <th scope="col" className="px-6 py-3">
                  Cut
                </th>
                <th scope="col" className="px-6 py-3">
                  Price
                </th>
                <th scope="col" className="px-6 py-3">
                  GIA number
                </th>
                <th scope="col" className="px-6 py-3">
                  Measurements (LxWxH)
                </th>
              </tr>
            </thead>
            <tbody>
              {filteredDiamonds.map(diamond => (
                <tr
                  key={diamond.id}
                  className="bg-white border-b dark:bg-gray-800 dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-600"
                >
                  <td className="px-6 py-4">{diamond.shape.shapeDescription}</td>
                  <td className="px-6 py-4">{diamond.carat.carat}</td>
                  <td className="px-6 py-4">{diamond.color.colorDescription}</td>
                  <td className="px-6 py-4">{diamond.clarity.clarityDescription}</td>
                  <td className="px-6 py-4">{diamond.cut.cutDescription}</td>
                  <td className="px-6 py-4">{diamond.price}</td>
                  <td className="px-6 py-4"><a href="#">{diamond.gia.giaNumber}</a></td>
                  <td className="px-6 py-4">{`${diamond.measurement.length} x ${diamond.measurement.width} x ${diamond.measurement.height}`}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </>
  );
};

export default NewDiamond;
