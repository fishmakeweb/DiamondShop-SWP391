import React, { useEffect, useState, useRef } from 'react';
import { useParams, Link } from 'react-router-dom';
import Navbar from '../components/Navbar';
import Footer from '../components/Footer';
import axios from '../axios.js';

function Gemstone() {
    const { gemstoneid } = useParams();
    const [ gemstone, setGemstone ] = useState([]);
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
        axios.get(`/gemstones/${gemstoneid}`)
            .then(response => {
                setGemstone(response.data.gemstoneName);
            })
            .catch(error => console.error('Error fetching jewelry data:', error));
    }, [gemstoneid]);


    useEffect(() => {
        axios.get(`/jewelry/gemstones/${gemstoneid}`)
            .then(response => {
                setJewelryItems(response.data);
            })
            .catch(error => console.error('Error fetching jewelry data:', error));
    }, [gemstoneid]);

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
                .then(response => setCategories(response.data))
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
            <div className="h-24 w-full"></div>
            <Navbar />
            <div className="self-center mt-16 text-4xl text-center text-black">
                {gemstone}
            </div>
            <div className="flex justify-center items-center px-16 py-5 mt-5 text-lg text-black border-b border-solid border-neutral-300">
                <div className="flex gap-5 justify-between w-full max-w-4xl">
                    <div className="text-center">Sort by Popularity</div>
                    <div ref={categoriesRef} onClick={toggleCategoriesDropdown} className="text-center cursor-pointer">
                        Type
                        {showCategoriesDropdown && (
                            <div className="absolute left-1/2 transform -translate-x-1/2 mt-2 bg-white border border-gray-300 rounded shadow-lg z-10 max-h-60 overflow-y-auto w-40">
                                {categories.map(category => (
                                    <Link key={category.categoryId} to={`/jewelry/categories/${category.categoryId}`} className="p-2 block hover:bg-gray-200">
                                        {category.categoryName}
                                    </Link>
                                ))}
                            </div>
                        )}
                    </div>
                    <div ref={gemstonesRef} onClick={toggleGemstonesDropdown} className="text-center cursor-pointer">
                        Gemstone
                        {showGemstonesDropdown && (
                            <div className="absolute transform -translate-x-1.5 mt-2 bg-white border border-gray-300 rounded shadow-lg z-10 max-h-60 overflow-y-auto w-40">
                                {gemstones.map(gemstone => (
                                    <Link key={gemstone.gemstoneId} to={`/jewelry/gemstones/${gemstone.gemstoneId}`} className="p-2 block hover:bg-gray-200">
                                        {gemstone.gemstoneName}
                                    </Link>
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
        </div>
    );
}

export default Gemstone;