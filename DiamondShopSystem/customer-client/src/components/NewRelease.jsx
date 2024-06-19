import React, { useEffect, useState } from "react";
import axios from '../axios';
import { useNavigate } from "react-router-dom";
import NewNavbar from "./NewNavbar";

const NewRelease = () => {
  const [products, setProducts] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);
  const [totalPages, setTotalPages] = useState(1);
  const [itemsPerPage, setItemsPerPage] = useState(6);
  const navigate = useNavigate();

  useEffect(() => {
    fetchProducts(currentPage, itemsPerPage);
  }, [currentPage, itemsPerPage]);

  const fetchProducts = async (page, size) => {
    try {
      const response = await axios.get(`/products/jewelry/page?page=${page}&size=${size}`);
      const sortedProducts = response.data.content.sort((a, b) => new Date(b.date) - new Date(a.date));
      setProducts(sortedProducts);
      setTotalPages(response.data.totalPages);
    } catch (error) {
      console.error('Error fetching product data:', error);
    }
  };

  const paginate = (pageNumber) => {
    setCurrentPage(pageNumber);
  };

  const handleSeeEverything = () => {
    setItemsPerPage(12);
    setCurrentPage(1); // Reset to the first page
  };

  const handleItemClick = (productId) => {
    navigate(`/products/${productId}`);
  };

  return (
    <>
      <NewNavbar />
      <style>
        {`
          @media (max-width: 1100px) and (min-width: 300px) {
            .custom-grid {
              display: grid;
              grid-template-columns: repeat(2, minmax(0, 1fr));
              gap: 1rem; /* Adjust the gap as needed */
            }
          }

          
        `}
      </style>
      <div className="bg-white">
        <div className="py-16 sm:py-24 lg:mx-auto lg:max-w-7xl lg:px-8">
          <div className="flex items-center justify-between px-4 sm:px-6 lg:px-0">
            <h2 className="text-2xl font-bold tracking-tight text-gray-900">
              Trending products
            </h2>
            <a
              href="#"
              onClick={handleSeeEverything}
              className="hidden text-sm font-semibold text-teal-600 hover:text-cyan-500 sm:block"
            >
              See everything
              <span aria-hidden="true"> →</span>
            </a>
          </div>
          <div className="relative mt-8">
            <div className="relative -mb-6 w-full overflow-x-auto pb-6">
              <ul
                role="list"
                className="custom-grid mx-4 sm:mx-6 lg:mx-0 lg:grid lg:grid-cols-4 lg:gap-x-8 lg:space-x-0"
              >
                {products.map((item) => (
                  <li 
                    key={item.productId} 
                    className="custom-grid-item flex flex-col text-center"
                    onClick={() => handleItemClick(item.productId)}
                  >
                    <div className="group relative cursor-pointer">
                      <div className="aspect-w-1 aspect-h-1 w-full overflow-hidden rounded-md bg-gray-200 custom-image">
                        <img
                          src={item.img}
                          alt={item.name}
                          className="h-full w-full object-cover object-center group-hover:opacity-75"
                        />
                      </div>
                      <div className="mt-2">
                        <h3 className="mt-1 font-semibold text-gray-900 text-sm">
                          <span className="absolute inset-0" />
                          {item.name}
                        </h3>
                        <p className="mt-1 text-gray-900 text-sm">${item.price}</p>
                      </div>
                    </div>
                  </li>
                ))}
              </ul>
            </div>
          </div>
          <div className="mt-12 flex justify-center">
            <Pagination
              totalPages={totalPages}
              paginate={paginate}
              currentPage={currentPage}
            />
          </div>
        </div>
      </div>
    </>
  );
};

const Pagination = ({ totalPages, paginate, currentPage }) => {
  const pageNumbers = [];

  for (let i = 1; i <= totalPages; i++) {
    pageNumbers.push(i);
  }

  return (
    <nav>
      <ul className="flex justify-center">
        {pageNumbers.map(number => (
          <li key={number} className="mx-1">
            <button
              onClick={() => paginate(number)}
              className={`px-3 py-1 border border-gray-300 rounded ${currentPage === number ? 'bg-gray-300' : ''}`}
            >
              {number}
            </button>
          </li>
        ))}
      </ul>
    </nav>
  );
};

export default NewRelease;
