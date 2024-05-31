import React, { useState } from 'react';
import ApiDataFetcher from './ApiDataFetcher';
import FilterDropdown from './FilterDropdown';

function FilterControlPanel({ onCategorySelect, onGemstoneSelect }) {
    const [categories, setCategories] = useState([]);
    const [gemstones, setGemstones] = useState([]);

    const handleCategoriesFetched = (data) => {
        const formattedCategories = data.map(category => ({
            id: category.categoryId,
            name: category.categoryName,
            link: `/jewelry/categories/${category.categoryId}`
        }));
        setCategories(formattedCategories);
    };

    const handleGemstonesFetched = (data) => {
        const formattedGemstones = data.map(gemstone => ({
            id: gemstone.gemstoneId,
            name: gemstone.gemstoneName,
            link: `/jewelry/gemstones/${gemstone.gemstoneId}`
        }));
        setGemstones(formattedGemstones);
    };

    return (
        <div>
            <ApiDataFetcher endpoint="/categories" onDataFetched={handleCategoriesFetched} />
            <ApiDataFetcher endpoint="/gemstones" onDataFetched={handleGemstonesFetched} />
            <div className="flex justify-center items-center px-16 py-5 mt-5 text-lg text-black border-b border-solid border-neutral-300">
                <div className="flex gap-5 justify-between w-full max-w-4xl">
                    <div className="text-center">Sort by Popularity</div>
                    <FilterDropdown title="Type" items={categories} onItemSelect={onCategorySelect} />
                    <FilterDropdown title="Gemstone" items={gemstones} onItemSelect={onGemstoneSelect} />
                    <div className="text-center">Price</div>
                </div>
            </div>
        </div>
    );
}

export default FilterControlPanel;
