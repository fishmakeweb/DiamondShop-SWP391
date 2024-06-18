import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import axios from "../axios.js";
import UpdateJewelry from "./UpdateJewelry";

const ViewAllJewelry = () => {
  const [jewelry, setJewelry] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [currentPage, setCurrentPage] = useState(1);
  const [totalPages, setTotalPages] = useState(1);
  const [editingJewelryId, setEditingJewelryId] = useState(null);
  const [showActionOverlay, setShowActionOverlay] = useState(false);
  const [showDeleteConfirmation, setShowDeleteConfirmation] = useState(false);
  const [showUpdateForm, setShowUpdateForm] = useState(false);

  const fetchJewelry = async (page = 1) => {
    setLoading(true);
    try {
      const response = await axios.get(`/jewelry/page?page=${page}&size=8`);
      setJewelry(response.data.content);
      setTotalPages(response.data.totalPages);
      setLoading(false);
    } catch (error) {
      setError("Failed to fetch jewelry data.");
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchJewelry(currentPage);
  }, [currentPage]);

  const handlePageChange = (newPage) => {
    if (newPage > 0 && newPage <= totalPages) {
      setCurrentPage(newPage);
    }
  };

  const handleEditClick = (jewelryId) => {
    if (editingJewelryId === jewelryId && showActionOverlay) {
      setShowActionOverlay(false);
      setEditingJewelryId(null);
    } else {
      setEditingJewelryId(jewelryId);
      setShowActionOverlay(true);
    }
  };

  const handleActionClick = (action) => {
    setShowActionOverlay(false);
    if (action === "update") {
      setShowUpdateForm(true);
    } else if (action === "delete") {
      setShowDeleteConfirmation(true);
    }
  };

  const handleDelete = async (jewelryId) => {
    try {
      await axios.delete(`/secure/jewelry/${jewelryId}`);
      setShowDeleteConfirmation(false);
      fetchJewelry(currentPage);
    } catch (error) {
      console.error("Delete jewelry failed:", error);
    }
  };

  const handleCloseForm = () => {
    setShowUpdateForm(false);
    setEditingJewelryId(null);
  };

  const handleOutsideClick = (event) => {
    if (event.target === event.currentTarget) {
      handleCloseForm();
    }
  };

  const handleOverlayClick = () => {
    setShowActionOverlay(false);
    setEditingJewelryId(null);
  };

  if (loading) {
    return <div>Loading...</div>;
  }

  if (error) {
    return <div>{error}</div>;
  }

  return (
    <>
      <section className="container px-4 mx-auto">
        <div className="sm:flex sm:items-center sm:justify-between">
          <div>
            <div className="flex items-center gap-x-3">
              <h2 className="text-lg font-medium text-gray-800 dark:text-white">
                Jewelry
              </h2>
              <span className="px-3 py-1 text-xs text-blue-600 bg-blue-100 rounded-full dark:bg-gray-800 dark:text-blue-400">
                {jewelry.length} products
              </span>
            </div>
          </div>
          <div className="flex items-center mt-4 gap-x-3">
            <button className="flex items-center justify-center w-1/2 px-5 py-2 text-sm text-gray-700 transition-colors duration-200 bg-white border rounded-lg gap-x-2 sm:w-auto dark:hover:bg-gray-800 dark:bg-gray-900 hover:bg-gray-100 dark:text-gray-200 dark:border-gray-700">
              <svg
                width={20}
                height={20}
                viewBox="0 0 20 20"
                fill="none"
                xmlns="http://www.w3.org/2000/svg"
              >
                <g clipPath="url(#clip0_3098_154395)">
                  <path
                    d="M13.3333 13.3332L9.99997 9.9999M9.99997 9.9999L6.66663 13.3332M9.99997 9.9999V17.4999M16.9916 15.3249C17.8044 14.8818 18.4465 14.1806 18.8165 13.3321C19.1866 12.4835 19.2635 11.5359 19.0351 10.6388C18.8068 9.7417 18.2862 8.94616 17.5555 8.37778C16.8248 7.80939 15.9257 7.50052 15 7.4999H13.95C13.6977 6.52427 13.2276 5.61852 12.5749 4.85073C11.9222 4.08295 11.104 3.47311 10.1817 3.06708C9.25943 2.66104 8.25709 2.46937 7.25006 2.50647C6.24304 2.54358 5.25752 2.80849 4.36761 3.28129C3.47771 3.7541 2.70656 4.42249 2.11215 5.23622C1.51774 6.04996 1.11554 6.98785 0.935783 7.9794C0.756025 8.97095 0.803388 9.99035 1.07431 10.961C1.34523 11.9316 1.83267 12.8281 2.49997 13.5832"
                    stroke="currentColor"
                    strokeWidth="1.67"
                    strokeLinecap="round"
                    strokeLinejoin="round"
                  />
                </g>
                <defs>
                  <clipPath id="clip0_3098_154395">
                    <rect width={20} height={20} fill="white" />
                  </clipPath>
                </defs>
              </svg>
              <span>Home</span>
            </button>
            <button className="flex items-center justify-center w-1/2 px-5 py-2 text-sm tracking-wide text-white transition-colors duration-200 bg-blue-500 rounded-lg shrink-0 sm:w-auto gap-x-2 hover:bg-blue-600 dark:hover:bg-blue-500 dark:bg-blue-600">
              <svg
                xmlns="http://www.w3.org/2000/svg"
                fill="none"
                viewBox="0 0 24 24"
                strokeWidth="1.5"
                stroke="currentColor"
                className="w-5 h-5"
              >
                <path
                  strokeLinecap="round"
                  strokeLinejoin="round"
                  d="M12 9v6m3-3H9m12 0a9 9 0 11-18 0 9 9 0 0118 0z"
                />
              </svg>
              <Link to="/addjewelry">
                <span>Add Jewelry</span>
              </Link>
            </button>
          </div>
        </div>
        <div className="mt-6 md:flex md:items-center md:justify-between">
          <div className="inline-flex overflow-hidden bg-white border divide-x rounded-lg dark:bg-gray-900 rtl:flex-row-reverse dark:border-gray-700 dark:divide-gray-700">
            <button className="px-5 py-2 text-xs font-medium text-gray-600 transition-colors duration-200 bg-gray-100 sm:text-sm dark:bg-gray-800 dark:text-gray-300">
              View all
            </button>
            <button className="px-5 py-2 text-xs font-medium text-gray-600 transition-colors duration-200 sm:text-sm dark:hover:bg-gray-800 dark:text-gray-300 hover:bg-gray-100">
              Monitored
            </button>
            <button className="px-5 py-2 text-xs font-medium text-gray-600 transition-colors duration-200 sm:text-sm dark:hover:bg-gray-800 dark:text-gray-300 hover:bg-gray-100">
              Unmonitored
            </button>
          </div>
          <div className="relative flex items-center mt-4 md:mt-0">
            <span className="absolute">
              <svg
                xmlns="http://www.w3.org/2000/svg"
                fill="none"
                viewBox="0 0 24 24"
                strokeWidth="1.5"
                stroke="currentColor"
                className="w-5 h-5 mx-3 text-gray-400 dark:text-gray-600"
              >
                <path
                  strokeLinecap="round"
                  strokeLinejoin="round"
                  d="M21 21l-5.197-5.197m0 0A7.5 7.5 0 105.196 5.196a7.5 7.5 0 0010.607 10.607z"
                />
              </svg>
            </span>
            <input
              type="text"
              placeholder="Search"
              className="block w-full py-1.5 pr-5 text-gray-700 bg-white border border-gray-200 rounded-lg md:w-80 placeholder-gray-400/70 pl-11 rtl:pr-11 rtl:pl-5 dark:bg-gray-900 dark:text-gray-300 dark:border-gray-600 focus:border-blue-400 dark:focus:border-blue-300 focus:ring-blue-300 focus:outline-none focus:ring focus:ring-opacity-40"
            />
          </div>
        </div>
        <div className="flex flex-col mt-6">
          <div className="-mx-4 -my-2 overflow-x-auto sm:-mx-6 lg:-mx-8">
            <div className="inline-block min-w-full py-2 align-middle md:px-6 lg:px-8">
              <div className="overflow-hidden border border-gray-200 dark:border-gray-700 md:rounded-lg">
                <table className="min-w-full divide-y divide-gray-200 dark:divide-gray-700">
                  <thead className="bg-gray-50 dark:bg-gray-800">
                    <tr>
                      <th
                        scope="col"
                        className="py-3 px-2 text-sm font-normal text-center text-gray-500 dark:text-gray-400"
                      >
                        JewelryID
                      </th>
                      <th
                        scope="col"
                        className="py-3 px-2 text-sm font-normal text-center text-gray-500 dark:text-gray-400"
                      >
                        Jewelry Name
                      </th>
                      <th
                        scope="col"
                        className="py-3 px-2 text-sm font-normal text-center text-gray-500 dark:text-gray-400"
                      >
                        Diamond
                      </th>
                      <th
                        scope="col"
                        className="py-3 px-2 text-sm font-normal text-center text-gray-500 dark:text-gray-400"
                      >
                        Material
                      </th>
                      <th
                        scope="col"
                        className="py-3 px-2 text-sm font-normal text-center text-gray-500 dark:text-gray-400"
                      >
                        Category
                      </th>
                      <th
                        scope="col"
                        className="py-3 px-2 text-sm font-normal text-center text-gray-500 dark:text-gray-400"
                      >
                        Size
                      </th>
                      <th
                        scope="col"
                        className="py-3 px-2 text-sm font-normal text-center text-gray-500 dark:text-gray-400"
                      >
                        Price
                      </th>
                      <th
                        scope="col"
                        className="py-3 px-2 text-sm font-normal text-center text-gray-500 dark:text-gray-400"
                      >
                        ImageURL
                      </th>
                      <th
                        scope="col"
                        className="py-3 px-2 text-sm font-normal text-center text-gray-500 dark:text-gray-400"
                      >
                        Quantity
                      </th>
                      <th
                        scope="col"
                        className="py-3 px-2 text-sm font-normal text-center text-gray-500 dark:text-gray-400"
                      >
                        Import Date
                      </th>
                      <th scope="col" className="relative py-3 px-2">
                        <span className="sr-only">Edit</span>
                      </th>
                    </tr>
                  </thead>
                  <tbody className="bg-white divide-y divide-gray-200 dark:divide-gray-700 dark:bg-gray-900">
                    {jewelry.map((item) => (
                      <tr key={item.jewelryId}>
                        <td className="px-2 py-2 text-sm font-medium text-center whitespace-nowrap">
                          <div>
                            <h2 className="font-medium text-gray-800 dark:text-white ">
                              {item.jewelryId}
                            </h2>
                          </div>
                        </td>
                        <td className="px-2 py-2 text-sm font-medium text-center whitespace-nowrap">
                          <div>
                            <h2 className="font-medium text-gray-800 dark:text-white ">
                              {item.name}
                            </h2>
                          </div>
                        </td>
                        <td className="px-2 py-2 text-sm font-medium text-center whitespace-nowrap">
                          <div className="inline px-3 py-1 text-sm font-normal text-gray-500 bg-gray-100 rounded-full dark:text-gray-400 gap-x-2 dark:bg-gray-800">
                            {item.diamond
                              ? item.diamond.shape.shapeDescription
                              : "No Diamond"}
                          </div>
                        </td>
                        <td className="px-2 py-2 text-sm text-center whitespace-nowrap">
                          <div>
                            <h4 className="text-gray-700 dark:text-gray-200">
                              {item.material.materialName}
                            </h4>
                          </div>
                        </td>
                        <td className="px-2 py-2 text-sm text-center whitespace-nowrap">
                          <div>
                            <h4 className="text-gray-700 dark:text-gray-200">
                              {item.category.categoryName}
                            </h4>
                          </div>
                        </td>
                        <td className="px-2 py-2 text-sm text-center whitespace-nowrap">
                          <div>
                            <h4 className="text-gray-700 dark:text-gray-200">
                              {`${item.size.sizeNumber} ${item.size.unit}`}
                            </h4>
                          </div>
                        </td>
                        <td className="px-2 py-2 text-sm text-center whitespace-nowrap">
                          <div>
                            <h4 className="text-gray-700 dark:text-gray-200">
                              ${item.price}
                            </h4>
                          </div>
                        </td>
                        <td className="px-2 py-2 text-sm text-center whitespace-nowrap">
                          <div>
                            <h4 className="text-gray-700 dark:text-gray-200">
                              {item.img}
                            </h4>
                          </div>
                        </td>
                        <td className="px-2 py-2 text-sm text-center whitespace-nowrap">
                          <div>
                            <h4 className="text-gray-700 dark:text-gray-200">
                              {item.quantity}
                            </h4>
                          </div>
                        </td>
                        <td className="px-2 py-2 text-sm text-center whitespace-nowrap">
                          <div>
                            <h4 className="text-gray-700 dark:text-gray-200">
                              {new Date(item.date).toLocaleDateString()}
                            </h4>
                          </div>
                        </td>
                        <td className="px-2 py-2 text-sm whitespace-nowrap">
                          <div className="relative">
                            <button
                              className="px-1 py-1 text-gray-500 transition-colors duration-200 rounded-lg dark:text-gray-300 hover:bg-gray-100"
                              onClick={() => handleEditClick(item.jewelryId)}
                            >
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
                                  d="M12 6.75a.75.75 0 110-1.5.75.75 0 010 1.5zM12 12.75a.75.75 0 110-1.5.75.75 0 010 1.5zM12 18.75a.75.75 0 110-1.5.75.75 0 010 1.5z"
                                />
                              </svg>
                            </button>
                            {editingJewelryId === item.jewelryId &&
                              showActionOverlay && (
                                <div
                                  className="fixed right-0 z-50 mt-2 w-48 bg-white border border-gray-200 rounded-lg shadow-lg"
                                  onClick={(e) => e.stopPropagation()}
                                >
                                  <button
                                    className="block w-full px-4 py-2 text-left text-gray-800 hover:bg-gray-100"
                                    onClick={() => handleActionClick("update")}
                                  >
                                    Update
                                  </button>
                                  <button
                                    className="block w-full px-4 py-2 text-left text-gray-800 hover:bg-gray-100"
                                    onClick={() => handleActionClick("delete")}
                                  >
                                    Delete
                                  </button>
                                </div>
                              )}
                          </div>
                        </td>
                      </tr>
                    ))}
                  </tbody>
                </table>
              </div>
            </div>
          </div>
        </div>
        <div className="mt-6 sm:flex sm:items-center sm:justify-between ">
          <div className="text-sm text-gray-500 dark:text-gray-400">
            Page {currentPage} of {totalPages}
          </div>
          <div className="flex items-center mt-4 gap-x-4 sm:mt-0">
            <button
              onClick={() => handlePageChange(currentPage - 1)}
              className={`flex items-center justify-center w-1/2 px-5 py-2 text-sm text-gray-700 capitalize transition-colors duration-200 bg-white border rounded-md sm:w-auto gap-x-2 hover:bg-gray-100 dark:bg-gray-900 dark:text-gray-200 dark:border-gray-700 dark:hover:bg-gray-800 ${
                currentPage === 1 ? "opacity-50 cursor-not-allowed" : ""
              }`}
              disabled={currentPage === 1}
            >
              <svg
                xmlns="http://www.w3.org/2000/svg"
                fill="none"
                viewBox="0 0 24 24"
                strokeWidth="1.5"
                stroke="currentColor"
                className="w-5 h-5 rtl:-scale-x-100"
              >
                <path
                  strokeLinecap="round"
                  strokeLinejoin="round"
                  d="M6.75 15.75L3 12m0 0l3.75-3.75M3 12h18"
                />
              </svg>
              <span>Previous</span>
            </button>
            <button
              onClick={() => handlePageChange(currentPage + 1)}
              className={`flex items-center justify-center w-1/2 px-5 py-2 text-sm text-gray-700 capitalize transition-colors duration-200 bg-white border rounded-md sm:w-auto gap-x-2 hover:bg-gray-100 dark:bg-gray-900 dark:text-gray-200 dark:border-gray-700 dark:hover:bg-gray-800 ${
                currentPage === totalPages
                  ? "opacity-50 cursor-not-allowed"
                  : ""
              }`}
              disabled={currentPage === totalPages}
            >
              <span>Next</span>
              <svg
                xmlns="http://www.w3.org/2000/svg"
                fill="none"
                viewBox="0 0 24 24"
                strokeWidth="1.5"
                stroke="currentColor"
                className="w-5 h-5 rtl:-scale-x-100"
              >
                <path
                  strokeLinecap="round"
                  strokeLinejoin="round"
                  d="M17.25 8.25L21 12m0 0l-3.75 3.75M21 12H3"
                />
              </svg>
            </button>
          </div>
        </div>
      </section>
      {editingJewelryId && showUpdateForm && (
        <UpdateJewelry jewelryId={editingJewelryId} onClose={handleCloseForm} />
      )}
      {showDeleteConfirmation && (
        <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50 z-50">
          <div className="bg-white p-6 rounded shadow-md text-center">
            <h2 className="text-xl font-semibold mb-4">
              Are you sure you want to delete?
            </h2>
            <button
              className="bg-red-500 text-white px-4 py-2 rounded mr-2"
              onClick={() => handleDelete(editingJewelryId)}
            >
              Yes
            </button>
            <button
              className="bg-gray-500 text-white px-4 py-2 rounded"
              onClick={() => setShowDeleteConfirmation(false)}
            >
              No
            </button>
          </div>
        </div>
      )}
    </>
  );
};

export default ViewAllJewelry;
