import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import Navbar from '../components/Navbar';
import Footer from '../components/Footer';
import axios from '../axios.js';
import ApiDataFetcher from '../components/ApiDataFetcher';
import FilterDropdown from '../components/FilterDropdown';
import JewelryList from '../components/JewelryList';

function Gemstone() {
    const { gemstoneid } = useParams();
    const [gemstoneName, setGemstoneName] = useState('');
    const [jewelryItems, setJewelryItems] = useState([]);
    const [categories, setCategories] = useState([]);
    const [gemstones, setGemstones] = useState([]);

    useEffect(() => {
        axios.get(`/gemstones/${gemstoneid}`)
            .then(response => {
                setGemstoneName(response.data.gemstoneName);
            })
            .catch(error => console.error('Error fetching category data:', error));

        axios.get(`/jewelry/gemstones/${gemstoneid}`)
            .then(response => {
                setJewelryItems(response.data);
            })
            .catch(error => console.error('Error fetching jewelry items:', error));
    }, [gemstoneid]);

    // Handlers for fetched data
    const handleCategoriesFetched = data => {
        setCategories(data.map(category => ({
            id: category.categoryId,
            name: category.categoryName,
            link: `/jewelry/categories/${category.categoryId}`
        })));
    };

    const handleGemstonesFetched = data => {
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
            <div className="self-center mt-16 text-4xl text-center text-black">{gemstoneName}</div>
            <ApiDataFetcher endpoint="/categories" onDataFetched={handleCategoriesFetched} />
            <ApiDataFetcher endpoint="/gemstones" onDataFetched={handleGemstonesFetched} />
            <div className="flex justify-center items-center px-16 py-5 mt-5 text-lg text-black border-b border-solid border-neutral-300">
                <div className="flex gap-5 justify-between w-full max-w-4xl">
                    <div className="text-center">Sort by Popularity</div>
                    <FilterDropdown title="Type" items={categories} onItemSelect={() => { }} />
                    <FilterDropdown title="Gemstone" items={gemstones} onItemSelect={() => { }} />
                    <div className="text-center">Price</div>
                </div>
            </div>
            <JewelryList items={jewelryItems} />
            <Footer />
        </div>
    );
}

export default Gemstone;
