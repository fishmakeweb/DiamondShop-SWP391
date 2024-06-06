import React, { useState } from 'react';
import { Link } from 'react-router-dom';


function DiamondList({ items }) {
    const [currentPage, setCurrentPage] = useState(0);
    const itemsPerPage = 8;

    const totalPages = Math.ceil(items.length / itemsPerPage);
    const startIndex = currentPage * itemsPerPage;
    const endIndex = startIndex + itemsPerPage;
    const itemsToShow = items.slice(startIndex, endIndex);

    const changePage = (pageNumber) => setCurrentPage(pageNumber);
    const pageNumbers = Array.from({ length: totalPages }, (_, i) => i);

    return (
        <div>
            <div className="grid grid-cols-4 gap-x-4 gap-y-10 mx-auto">
                {itemsToShow.map(item => (
                    <div key={item.diamondId} className="text-center p-2 flex flex-col">
                        <Link to={`/diamonds/${item.diamondId}`}>
                            <img src={`/img/diamonds/${item.img}`} alt={item.name} className="w-64 h-64 object-cover mx-auto" />
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
        </div>
    );
}




export default DiamondList;
