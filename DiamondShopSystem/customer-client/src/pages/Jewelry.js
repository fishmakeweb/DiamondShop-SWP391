import React, { useEffect, useState } from 'react';
import Navbar from '../components/Navbar';
import Footer from '../components/Footer';
import JewelryList from '../components/JewelryList';
import FilterDropdown from '../components/FilterDropdown';
import axios from '../axios.js';
import ApiDataFetcher from '../components/ApiDataFetcher';

function Jewelry() {
    const [jewelryItems, setJewelryItems] = useState([]);
    const [categories, setCategories] = useState([]);
    const [gemstones, setGemstones] = useState([]);

    useEffect(() => {
        axios.get('http://localhost:8080/api/jewelry')
            .then(response => {
                setJewelryItems(response.data);
            })
            .catch(error => console.error('Error fetching jewelry data:', error));
    }, []);

    const handleCategoriesFetched = (data) => {
        setCategories(data.map(category => ({
            id: category.categoryId,
            name: category.categoryName,
            link: `/jewelry/categories/${category.categoryId}`
        })));
    };

    const handleGemstonesFetched = (data) => {
        setGemstones(data.map(gemstone => ({
            id: gemstone.gemstoneId,
            name: gemstone.gemstoneName,
            link: `/jewelry/gemstones/${gemstone.gemstoneId}`
        })));
    };

    return (
        <div className="flex flex-col bg-white">
            <div className="h-24 w-full"></div>
            <Navbar />
            
            <div className="self-center mt-16 text-4xl text-center text-black">Jewelry</div>
            <ApiDataFetcher endpoint="/categories" onDataFetched={handleCategoriesFetched} />
            <ApiDataFetcher endpoint="/gemstones" onDataFetched={handleGemstonesFetched} />

            <div className="flex justify-center items-center px-16 py-5 mt-5 text-lg text-black border-b border-solid border-neutral-300">
                <div className="flex gap-5 justify-between w-full max-w-4xl">
                    <div className="text-center">Sort by Popularity</div>
                    <FilterDropdown title="Type" items={categories} onItemSelect={() => {}} />
                    <FilterDropdown title="Gemstone" items={gemstones} onItemSelect={() => {}} />
                    <div className="text-center">Price</div>
                </div>
            </div>
            <JewelryList items={jewelryItems} />
            <Footer />
        </div>
    );
}

export default Jewelry;