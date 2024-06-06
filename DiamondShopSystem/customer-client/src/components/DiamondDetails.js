import React, { useEffect, useState, useContext } from 'react';
import { useParams } from 'react-router-dom';
import Navbar from '../components/Navbar';
import Footer from '../components/Footer';
import axios from '../axios.js';
import CartContext from '../components/CartContext.js';

function DiamondDetails() {
  const { diamondId } = useParams();
  const [diamondDetails, setDiamondDetails] = useState(null);
  const { addToCart } = useContext(CartContext);

  useEffect(() => {
    axios.get(`/diamonds/${diamondId}`)
      .then(response => setDiamondDetails(response.data))
      .catch(error => console.error('Error fetching diamond details:', error));
  }, [diamondId]);

  if (!diamondDetails) return <div>Loading...</div>;

  return (
    <div className="flex flex-col items-center pb-20 bg-white">
      <Navbar />
      <div className="self-stretch w-full bg-black min-h-[32px] max-md:max-w-full" />
      <div className="mt-36 text-4xl text-center text-black max-md:mt-10">
        Diamond Details
      </div>
      <div className="mt-8 text-xl text-center text-stone-500">
        {diamondDetails.name}
      </div>
      <div className="flex flex-col mt-32 w-full max-w-[1265px] max-md:mt-10 max-md:max-w-full">
        <img src={`/img/diamonds/${diamondDetails.img}`} alt={diamondDetails.name} className="w-64 h-64 object-cover mx-auto" />
        <div className="px-2 py-1">
          <div className="text-darkgray font-karla text-sm font-normal">{diamondDetails.name}</div>
          <div className="text-lightgray font-karla text-sm font-normal">${diamondDetails.price}</div>
          <div className="text-lightgray font-karla text-sm font-normal">Shape: {diamondDetails.shape}</div>
          <div className="text-lightgray font-karla text-sm font-normal">Millimetres: {diamondDetails.millimetre}</div>
          <div className="text-lightgray font-karla text-sm font-normal">Carat: {diamondDetails.carat}</div>
          <button
            onClick={() => addToCart(diamondDetails)}
            className="justify-center self-center px-7 py-4 mt-7 ml-14 text-2xl text-black bg-orange-200 rounded-xl max-md:pr-5"
          >
            Add to Cart
          </button>
        </div>
      </div>
      <Footer />
    </div>
  );
}

export default DiamondDetails;
