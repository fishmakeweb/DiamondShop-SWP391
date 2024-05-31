import React, { useEffect, useState } from 'react';
import axios from '../axios.js';

function ApiDataFetcher({ endpoint, onDataFetched }) {
    const [isLoading, setIsLoading] = useState(true);

    useEffect(() => {
        axios.get(endpoint)
            .then(response => {
                onDataFetched(response.data);  // Pass the data back to the parent component
                setIsLoading(false);
            })
            .catch(error => {
                console.error(`Error fetching data from ${endpoint}:`, error);
                setIsLoading(false);
            });
    }, [endpoint, onDataFetched]);

    if (isLoading) {
        return <div>Loading...</div>;
    }

    return null;  // Render nothing when not loading
}

export default ApiDataFetcher;
