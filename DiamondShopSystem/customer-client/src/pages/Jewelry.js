
// import React, { useEffect, useState } from "react";
// import Navbar from '../components/Navbar';
// import Footer from '../components/Footer';

// function Jewelry() {
//     const [jewelryItems, setJewelryItems] = useState([]);
//     const [loading, setLoading] = useState(false);
//     const [error, setError] = useState(null);

//     useEffect(() => {
//         setLoading(true);
//         fetch('http://localhost:8080/api/jewelry')
//             .then(response => response.json())
//             .then(data => {
//                 setJewelryItems(data);
//                 setLoading(false);
//             })
//             .catch(error => {
//                 console.error('Error fetching jewelry data:', error);
//                 setError(error.toString());
//                 setLoading(false);
//             });
//     }, []);

//     if (loading) return <p>Loading...</p>;
//     if (error) return <p>Error loading jewelry: {error}</p>;

//     return (
//         <div className="flex flex-col bg-white">
//             <div className="self-center mt-16 text-4xl text-center text-black max-md:mt-10">
//                 Jewelry
//             </div>
//             <div className="self-center mt-5 text-xl text-center text-stone-500">
//                 Lorem ipsum dolor sit amet.
//             </div>
//             <div className="flex justify-center items-center px-16 py-5 mt-40 text-lg text-black border-b border-solid border-neutral-300 border-opacity-80 max-md:px-5">
//                 {/* Sorting options */}
//                 <div className="flex gap-5 justify-between max-w-full w-[648px] max-md:flex-wrap">
//                     <div className="my-auto text-neutral-500">SORT BY:</div>
//                     <div className="text-center">Popularity</div>
//                     <div className="text-center">Type</div>
//                     <div className="text-center">Gemstone</div>
//                     <div className="text-center">Price</div>
//                 </div>
//             </div>
//             <div className="flex flex-wrap justify-center items-center px-16 py-5 text-lg text-black border-b border-solid border-neutral-300 max-md:px-5">
//                 {/* Dynamic jewelry items */}
//                 {jewelryItems.map(item => (
//                     <div key={item.jewelryId} className="text-center p-5 m-2">
//                         <img src={`/img/jewelry/${item.img}`} alt={item.name} className="max-w-xs h-auto" />
//                         <div className="flex flex-col px-5 text-base">
//                             <div className="text-darkgray font-karla text-base font-normal">
//                                 {item.name}  {/* Name with custom dark gray color */}
//                             </div>
//                             <div className="text-lightgray font-karla text-base font-normal">
//                                 ${item.price} {/* Price with custom light gray color */}
//                             </div>
//                         </div>
//                     </div>
//                 ))}
//             </div>
//         </div>
//     );
// }

// export default Jewelry;

import React, { useEffect, useState } from "react";
import Navbar from '../components/Navbar';
import Footer from '../components/Footer';

function Jewelry() {
    const [jewelryItems, setJewelryItems] = useState([]);
    const [currentPage, setCurrentPage] = useState(0);
    const [itemsPerPage] = useState(8);

    useEffect(() => {
        fetch('http://localhost:8080/api/jewelry')
            .then(response => response.json())
            .then(data => {
                setJewelryItems(data);
            })
            .catch(error => console.error('Error fetching jewelry data:', error));
    }, []);
    const totalPages = Math.ceil(jewelryItems.length / itemsPerPage);
    const changePage = (pageNumber) => {
        setCurrentPage(pageNumber);
    };
    const startIndex = currentPage * itemsPerPage;
    const endIndex = startIndex + itemsPerPage;
    const itemsToShow = jewelryItems.slice(startIndex, endIndex);
    const pageNumbers = [];
    for (let i = 0; i < totalPages; i++) {
        pageNumbers.push(i);
    }

    return (
        <div className="flex flex-col bg-white">
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
                    <div className="text-center">Type</div>
                    <div className="text-center">Gemstone</div>
                    <div className="text-center">Price</div>
                </div>
            </div>

            <div className="grid grid-cols-4 gap-x-4 gap-y-10 mx-auto">
                {itemsToShow.map(item => (
                    <div key={item.jewelryId} className="text-center p-2 flex flex-col">
                        <img src={`/img/jewelry/${item.img}`} alt={item.name} className="w-64 h-64 object-cover mx-auto" />
                        <div className="px-2 py-1">
                            <div className="text-darkgray font-karla text-sm font-normal">
                                {item.name}
                            </div>
                            <div className="text-lightgray font-karla text-sm font-normal">
                                ${item.price}
                            </div>
                        </div>
                    </div>
                ))}
            </div>


            <div className="flex justify-center mt-4">
                {pageNumbers.map(number => (
                    <button
                        key={number}
                        onClick={() => changePage(number)}
                        className={`mt-5 text-xl text-center cursor-pointer hover:underline ease-in-out p-2 inline-block w-12 ${currentPage === number ? "font-semibold" : "font-thin"}`}
                    >
                        {number + 1}
                    </button>
                ))}
            </div>

        </div>
    );
}

export default Jewelry;
