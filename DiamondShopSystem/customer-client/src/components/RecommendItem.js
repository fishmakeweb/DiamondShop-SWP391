import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import axios from '../axios';

const RecommendItem = ({ currentItemId }) => {
    const [recommendedItems, setRecommendedItems] = useState([]);

    useEffect(() => {
        const fetchItems = async () => {
            const generatedIds = [];
            while (generatedIds.length < 4) {
                const randomId = Math.floor(Math.random() * 50) + 1;  // IDs range from 1 to 58
                if (randomId !== currentItemId && !generatedIds.includes(randomId)) {
                    generatedIds.push(randomId);
                }
            }

            try {
                const responses = await Promise.all(
                    generatedIds.map(id =>
                        axios.get(`http://localhost:8080/api/jewelry/${id}`)
                            .then(res => res.data)
                    )
                );
                setRecommendedItems(responses);
            } catch (error) {
                console.error('Error fetching recommended jewelry:', error);
            }
        };

        fetchItems();
    }, [currentItemId]);

    if (!recommendedItems.length) return <div>Loading recommendations...</div>;

    return (
        <div className="flex gap-x-20 justify-center max-md:flex-wrap">
            {recommendedItems.map(item => (
                <Link to={`/jewelry/${item.jewelryId}`} key={item.jewelryId}>
                    <div className="flex flex-col justify-center py-3 px-4 bg-white bg-opacity-0 text-zinc-800">
                        <img
                            loading="lazy"
                            src={`/img/jewelry/${item.img}`}
                            alt={item.name}
                            className="w-64 h-64 object-cover mx-auto"
                        />
                        <div className="flex flex-col justify-center pb-3.5 mt-3 bg-white bg-opacity-0">
                            <div className="z-10 text-zinc-800">{item.name}</div>
                            <div className="text-zinc-500">${item.price}</div>
                        </div>
                    </div>
                </Link>
            ))}
        </div>
    );
};

export default RecommendItem;
