import React, { useState, useEffect } from "react";
import axios from "../axios";

const UpdateDiamond = ({ diamondId, onClose }) => {
  const [diamond, setDiamond] = useState(null);
  const [shape, setShape] = useState("");
  const [color, setColor] = useState("");
  const [cut, setCut] = useState("");
  const [clarity, setClarity] = useState("");
  const [carat, setCarat] = useState("");
  const [price, setPrice] = useState("");
  const [errors, setErrors] = useState({});
  const [showSubmitMessage, setShowSubmitMessage] = useState(false);

  useEffect(() => {
    const fetchDiamondDetails = async () => {
      try {
        const response = await axios.get(`/diamonds/${diamondId}`);
        const diamond = response.data;
        setDiamond(diamond);
        setShape(diamond.shape.shapeDescription);
        setColor(diamond.color.colorDescription);
        setCut(diamond.cut.cutDescription);
        setClarity(diamond.clarity.clarityDescription);
        setCarat(diamond.carat.carat);
        setPrice(diamond.price);
      } catch (error) {
        console.error("Error fetching diamond details: ", error);
      }
    };

    fetchDiamondDetails();
  }, [diamondId]);

  const validate = () => {
    const newErrors = {};
    if (!shape) newErrors.shape = "Shape is required.";
    if (!color) newErrors.color = "Color is required.";
    if (!cut) newErrors.cut = "Cut is required.";
    if (!clarity) newErrors.clarity = "Clarity is required.";
    if (!carat) newErrors.carat = "Carat is required.";
    if (carat && carat <= 0) newErrors.carat = "Carat must be greater than 0.";
    if (!price) newErrors.price = "Price is required.";
    if (price && price <= 0) newErrors.price = "Price must be greater than 0.";

    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const isValid = validate();
    if (!isValid) return;

    const diamondData = {
      shape: { shapeDescription: shape },
      color: { colorDescription: color },
      cut: { cutDescription: cut },
      clarity: { clarityDescription: clarity },
      carat: { carat },
      price,
    };

    try {
      const response = await axios.put(
        `/secure/diamonds/${diamondId}`,
        diamondData
      );
      console.log("Update diamond response: ", response.data);
      setShowSubmitMessage(true);
    } catch (error) {
      console.error("Update diamond failed:", error);
    }
  };

  const handleReload = () => {
    window.location.reload(); // Reload the page
  };

  return (
    <>
      <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50 z-50">
        <div className="bg-white p-6 rounded shadow-md w-full max-w-lg">
          <h2 className="text-xl font-semibold mb-4">
            Update Diamond ID: {diamondId}
          </h2>
          <form onSubmit={handleSubmit}>
            <label
              className="relative block p-2 border-2 border-black rounded mb-3"
              htmlFor="shape"
            >
              <span className="text-md font-semibold text-zinc-900">Shape</span>
              <input
                className="w-full bg-transparent p-0 text-sm text-gray-500 focus:outline-none"
                id="shape"
                type="text"
                value={shape}
                onChange={(e) => setShape(e.target.value)}
                placeholder="Shape"
              />
              {errors.shape && (
                <span className="text-red-500 text-sm">{errors.shape}</span>
              )}
            </label>
            <label
              className="relative block p-2 border-2 border-black rounded mb-3"
              htmlFor="color"
            >
              <span className="text-md font-semibold text-zinc-900">Color</span>
              <input
                className="w-full bg-transparent p-0 text-sm text-gray-500 focus:outline-none"
                id="color"
                type="text"
                value={color}
                onChange={(e) => setColor(e.target.value)}
                placeholder="Color"
              />
              {errors.color && (
                <span className="text-red-500 text-sm">{errors.color}</span>
              )}
            </label>
            <label
              className="relative block p-2 border-2 border-black rounded mb-3"
              htmlFor="cut"
            >
              <span className="text-md font-semibold text-zinc-900">Cut</span>
              <input
                className="w-full bg-transparent p-0 text-sm text-gray-500 focus:outline-none"
                id="cut"
                type="text"
                value={cut}
                onChange={(e) => setCut(e.target.value)}
                placeholder="Cut"
              />
              {errors.cut && (
                <span className="text-red-500 text-sm">{errors.cut}</span>
              )}
            </label>
            <label
              className="relative block p-2 border-2 border-black rounded mb-3"
              htmlFor="clarity"
            >
              <span className="text-md font-semibold text-zinc-900">
                Clarity
              </span>
              <input
                className="w-full bg-transparent p-0 text-sm text-gray-500 focus:outline-none"
                id="clarity"
                type="text"
                value={clarity}
                onChange={(e) => setClarity(e.target.value)}
                placeholder="Clarity"
              />
              {errors.clarity && (
                <span className="text-red-500 text-sm">{errors.clarity}</span>
              )}
            </label>
            <label
              className="relative block p-2 border-2 border-black rounded mb-3"
              htmlFor="carat"
            >
              <span className="text-md font-semibold text-zinc-900">Carat</span>
              <input
                className="w-full bg-transparent p-0 text-sm text-gray-500 focus:outline-none"
                id="carat"
                type="number"
                value={carat}
                onChange={(e) => setCarat(e.target.value)}
                placeholder="Carat"
              />
              {errors.carat && (
                <span className="text-red-500 text-sm">{errors.carat}</span>
              )}
            </label>
            <label
              className="relative block p-2 border-2 border-black rounded mb-3"
              htmlFor="price"
            >
              <span className="text-md font-semibold text-zinc-900">Price</span>
              <input
                className="w-full bg-transparent p-0 text-sm text-gray-500 focus:outline-none"
                id="price"
                type="number"
                value={price}
                onChange={(e) => setPrice(e.target.value)}
                placeholder="Price"
              />
              {errors.price && (
                <span className="text-red-500 text-sm">{errors.price}</span>
              )}
            </label>
            <div className="flex justify-between mt-5">
              <button className="border-2 px-5 py-2 rounded-lg border-black border-b-4 font-black translate-y-2 border-l-4">
                Update
              </button>
              <button
                type="button"
                className="bg-gray-500 text-white px-4 py-2 rounded"
                onClick={onClose}
              >
                Cancel
              </button>
            </div>
          </form>
        </div>
      </div>
      {showSubmitMessage && (
        <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50 z-50">
          <div className="bg-white p-6 rounded shadow-md text-center">
            <h2 className="text-xl font-semibold mb-4">Update Successfully</h2>
            <button
              className="bg-blue-500 text-white px-4 py-2 rounded"
              onClick={handleReload}
            >
              OK
            </button>
          </div>
        </div>
      )}
    </>
  );
};

export default UpdateDiamond;
