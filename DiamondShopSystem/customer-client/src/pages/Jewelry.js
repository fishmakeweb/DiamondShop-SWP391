import React, { useEffect, useState, useRef } from "react";
import { Link } from 'react-router-dom';
import Navbar from '../components/Navbar';
import Footer from '../components/Footer';
import axios from '../axios.js';

function Jewelry() {
    const [jewelryItems, setJewelryItems] = useState([]);
    const [currentPage, setCurrentPage] = useState(0);
    const [itemsPerPage] = useState(8);
    const [categories, setCategories] = useState([]);
    const [showCategoriesDropdown, setShowCategoriesDropdown] = useState(false);
    const [gemstones, setGemstones] = useState([]);
    const [showGemstonesDropdown, setShowGemstonesDropdown] = useState(false);
    const categoriesRef = useRef(null);
    const gemstonesRef = useRef(null);

    useEffect(() => {
        axios.get('/jewelry')
            .then(response => {
                setJewelryItems(response.data);
            })
            .catch(error => console.error('Error fetching jewelry data:', error));
    }, []);

    useEffect(() => {
        const handleClickOutside = (event) => {
            if (categoriesRef.current && !categoriesRef.current.contains(event.target)) {
                setShowCategoriesDropdown(false);
            }
            if (gemstonesRef.current && !gemstonesRef.current.contains(event.target)) {
                setShowGemstonesDropdown(false);
            }
        };
        document.addEventListener("mousedown", handleClickOutside);
        return () => {
            document.removeEventListener("mousedown", handleClickOutside);
        };
    }, []);

    const toggleCategoriesDropdown = () => {
        setShowCategoriesDropdown(!showCategoriesDropdown);
        if (!showCategoriesDropdown && categories.length === 0) {
            axios.get('/categories')
                .then(response =>  setCategories(response.data))
                .catch(error => console.error('Error fetching categories:', error));
        }
    };

    const toggleGemstonesDropdown = () => {
        setShowGemstonesDropdown(!showGemstonesDropdown);
        if (!showGemstonesDropdown && gemstones.length === 0) {
            axios.get('/gemstones')
                .then(response => setGemstones(response.data))
                .catch(error => console.error('Error fetching gemstones:', error));
        }
    };

    const totalPages = Math.ceil(jewelryItems.length / itemsPerPage);
    const changePage = pageNumber => setCurrentPage(pageNumber);
    const startIndex = currentPage * itemsPerPage;
    const endIndex = startIndex + itemsPerPage;
    const itemsToShow = jewelryItems.slice(startIndex, endIndex);
    const pageNumbers = Array.from({ length: totalPages }, (_, i) => i);

    return (
        <div className="flex flex-col bg-white">
            <Navbar />
            <div className="self-center mt-16 text-4xl text-center text-black max-md:mt-10">
                Jewelry
            </div>
            <div className="self-center mt-5 text-xl text-center text-stone-500">
                Lorem ipsum dolor sit amet.
            </div>
            <div className="flex justify-center items-center px-16 py-5 mt-40 text-lg text-black border-b border-solid border-neutral-300 border-opacity-80 max-md:px-5">
                <div className="flex gap-5 justify-between max-w-full w-[648px] max-md:flex-wrap">
                    <div className="my-auto text-neutral-500">SORT BY:</div>
                    <div className="text-center">Popularity</div>
                    <div className="relative" ref={categoriesRef}>
                        <div className="text-center cursor-pointer" onClick={toggleCategoriesDropdown}>
                            Type
                        </div>
                        {showCategoriesDropdown && (
                            <div className="absolute left-1/2 transform -translate-x-1/2 mt-2 bg-white border border-gray-300 rounded shadow-lg z-10 max-h-60 overflow-y-auto w-40 custom-scrollbar">
                                {categories.map(category => (
                                    <div key={category.categoryId} className="p-2 hover:bg-gray-200 cursor-pointer">
                                        {category.categoryName}
                                    </div>
                                ))}
                            </div>
                        )}
                    </div>
                    <div className="relative" ref={gemstonesRef}>
                        <div className="text-center cursor-pointer z-0" onClick={toggleGemstonesDropdown}>
                            Gemstone
                        </div>
                        {showGemstonesDropdown && (
                            <div className="absolute left-1/2 transform -translate-x-1/2 mt-2 bg-white border border-gray-300 rounded shadow-lg z-10 max-h-60 overflow-y-auto w-40 custom-scrollbar">
                                {gemstones.map(gemstone => (
                                    <div key={gemstone.gemstoneId} className="p-2 hover:bg-gray-200 cursor-pointer">
                                        {gemstone.gemstoneName}
                                    </div>
                                ))}
                            </div>
                        )}
                    </div>
                    <div className="text-center">Price</div>
                </div>
            </div>

            <div className="grid grid-cols-4 gap-x-4 gap-y-10 mx-auto">
                {itemsToShow.map(item => (
                    <div key={item.jewelryId} className="text-center p-2 flex flex-col">
                        <Link to={`/jewelry/${item.jewelryId}`}>
                            <img src={`/img/jewelry/${item.img}`} alt={item.name} className="w-64 h-64 object-cover mx-auto" />
                            <div className="px-2 py-1">
                                <div className="text-darkgray font-karla text-sm font-normal">{item.name}</div>
                                <div className="text-lightgray font-karla text-sm font-normal">${item.price}</div>
                            </div>
                        </Link>
                    </div>
                ))}
            </div>

            <div className="flex justify-center mt-4">
                {pageNumbers.map(number => (
                    <button key={number} onClick={() => changePage(number)} className={`hover:bg-gray-200 mt-5 text-xl text-center cursor-pointer ease-in-out p-2 inline-block w-12 ${currentPage === number ? "font-semibold" : "font-thin"}`}>
                        {number + 1}
                    </button>
                ))}
            </div>
            <Footer />
            <style jsx>{`
                .custom-scrollbar::-webkit-scrollbar {
                    width: 0;
                    background: transparent;
                }

                .custom-scrollbar {
                    -ms-overflow-style: none;
                    scrollbar-width: none;
                }
            `}</style>
        </div>
    );
}

export default Jewelry;