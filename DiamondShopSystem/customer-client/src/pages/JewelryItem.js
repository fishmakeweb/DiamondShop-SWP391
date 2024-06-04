import React, { useEffect, useState, useContext } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import Navbar from '../components/Navbar';
import Footer from '../components/Footer';
import RecommendItem from '../components/RecommendItem';
import axios from '../axios.js';
import CartContext from '../components/CartContext.js';

const Item = () => {
  let navigate = useNavigate();
  return (
    <>
      <button onClick={() => navigate(-1)}>BACK TO SELECTION</button>
    </>
  );
};

function JewelryDetails({ jewelry }) {
  return (
    <div className="p-4 bg-white shadow rounded-lg">
      <table className="min-w-full">
        <tbody>
          {jewelry.name && (
            <tr>
              <th>Name:</th>
              <td>{jewelry.name}</td>
            </tr>
          )}
          {jewelry.price && (
            <tr>
              <th>Price:</th>
              <td>${jewelry.price}</td>
            </tr>
          )}
          {jewelry.material && jewelry.material.materialName && (
            <tr>
              <th>Material:</th>
              <td>{jewelry.material.materialName}</td>
            </tr>
          )}
          {jewelry.category && jewelry.category.categoryName && (
            <tr>
              <th>Category:</th>
              <td>{jewelry.category.categoryName}</td>
            </tr>
          )}
          {jewelry.gemstone && jewelry.gemstone.gemstoneName && (
            <tr>
              <th>Gemstone:</th>
              <td>{jewelry.gemstone.gemstoneName}</td>
            </tr>
          )}
          {jewelry.size && jewelry.size.sizeNumber && jewelry.size.unit && (
            <tr>
              <th>Size:</th>
              <td>{jewelry.size.sizeNumber} ({jewelry.size.unit})</td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
  );
}

function JewelryItemData({ data }) {
  const [showDetails, setShowDetails] = useState(false);

  return (
    <div>
      <button
        className="flex gap-5 hover:text-custom-brown justify-center px-0.5 py-3 mt-9 text-xl whitespace-nowrap border-b border-solid border-zinc-400 text-neutral-700 max-md:flex-wrap max-md:max-w-full"
        onClick={() => setShowDetails(!showDetails)}
      >
        DETAILS
        <img
          loading="lazy"
          alt=""
          src="https://cdn.builder.io/api/v1/image/assets/TEMP/8e79619d6d6b36f08d933bffaf65933ca66414fb4b6e9b0f6c0ed83858787e4f?"
          className="shrink-0 w-6 aspect-square"
        />
      </button>
      {showDetails && <JewelryDetails jewelry={data} />}
    </div>
  );
}

function JewelryItem() {
  const { jewelryId } = useParams();
  const [itemDetails, setItemDetails] = useState(null);
  const { addToCart } = useContext(CartContext);
  const [buttonText, setButtonText] = useState('ADD TO BAG');
 
  const handleClick = () => {
      setButtonText('ADDED');

      setTimeout(() => {
          setButtonText('ADD TO BAG');
      }, 1000); // Reverts back to 'Submit' after 2 seconds
  };

  useEffect(() => {
    axios.get(`/jewelry/${jewelryId}`)
      .then(response => setItemDetails(response.data))
      .catch(error => console.error('Error fetching jewelry details:', error));
  }, [jewelryId]);

  if (!itemDetails) return <div>Loading...</div>;

  return (
    <div className="flex flex-col bg-white">
      <div className="h-24 w-full"></div>
      <Navbar />
      <div className="self-center mt-20 w-full max-w-[1214px] max-md:mt-10 max-md:max-w-full">
        <div className="flex gap-5 max-md:flex-col max-md:gap-0">
          <div className="flex flex-col w-2/5 max-md:ml-0 max-md:w-full">
            <img
              loading="lazy"
              src={`/img/jewelry/${itemDetails.img}`}
              alt={itemDetails.name}
              className="mt-20 w-80 h-80 object-cover mx-auto"
            />
          </div>
          <div className="flex flex-col ml-5 w-3/5 max-md:ml-0 max-md:w-full">
            <div className="flex flex-col px-5 mt-10 max-md:mt-10 max-md:max-w-full">
              <div className="flex gap-5 justify-between max-md:flex-wrap max-md:max-w-full">
                <div className="flex flex-col">
                  <div className="text-base hover:text-custom-brown text-neutral-600"><Item /></div>
                  <div className="mt-11 text-4xl text-black max-md:mt-10">
                    {itemDetails.name}
                  </div>
                  <div className="mt-4 text-2xl font-bold text-black text-opacity-50">
                    ${itemDetails.price}
                  </div>
                </div>
                <img
                  loading="lazy"
                  alt=""
                  src="https://cdn.builder.io/api/v1/image/assets/TEMP/993591a3a81dab2d2c2ebb7f3b4a931dab5d810de16c88f48d4f1c172185adfc?"
                  className="shrink-0 my-auto aspect-[0.94] w-[35px]"
                />
              </div>
              <div className="mt-9 text-xl text-black font-[350] max-md:max-w-full">
                Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do
                eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut
                enim ad minim veniam, quis nostrud exercitation ullamco laboris
                nisi ut aliquip ex ea commodo consequat.
                <br />
              </div>
              <button
                className="justify-center w-36 self-start px-4 py-4 mt-9 text-lg border border-solid bg-opacity-0 border-neutral-700 text-neutral-700 hover:bg-custom-brown hover:text-white"
                onClick={() => {
                  addToCart(itemDetails);
                  handleClick();
                }}>
                {buttonText}
              </button>
              <JewelryItemData data={itemDetails} />
              <div className="flex gap-5 justify-center py-3 pr-2 pl-px mt-3 text-xl border-b border-solid border-zinc-400 text-neutral-700 max-md:flex-wrap max-md:max-w-full">
                <div className="flex-auto my-auto">TIPS & WARNINGS</div>
                <img
                  loading="lazy"
                  alt=""
                  src="https://cdn.builder.io/api/v1/image/assets/TEMP/9a20b583fc524c1afdb28207b9bc2fbcaafc28e514c302e5d668864cf5c97e49?"
                  className="shrink-0 w-6 aspect-square"
                />
              </div>
              <div className="flex gap-5 justify-center py-3 pr-1.5 pl-px mt-3 text-xl border-b border-solid border-zinc-400 text-neutral-700 max-md:flex-wrap max-md:max-w-full">
                <div className="flex-auto my-auto">LOCAL AVAILABILITY</div>
                <img
                  loading="lazy"
                  alt=""
                  src="https://cdn.builder.io/api/v1/image/assets/TEMP/35d01c5cc7d2f8e99764e480ad8a21ca61347d99af30fc8107408dfda4294276?"
                  className="shrink-0 aspect-square w-[26px]"
                />
              </div>
            </div>
          </div>
        </div>
      </div>
      <div className="flex z-10 flex-col text-center self-center px-5 pt-9 pb-4 mt-20 border-t border-solid border-neutral-300 max-md:mt-10 max-md:max-w-full">
        <div className="text-3xl text-black max-md:max-w-full">
          Recommended for you
        </div>
        <div className="mt-3 text-lg font-[353] text-neutral-500 max-md:max-w-full">
          Based on your history
        </div>
      </div>
      <RecommendItem currentItemId={itemDetails.jewelryId} />
      <Footer />
    </div>
  );
}

export default JewelryItem;
