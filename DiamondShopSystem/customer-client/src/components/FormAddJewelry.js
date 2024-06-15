import React, { useState, useEffect } from "react";
import axios from "../axios";

const SubmitMessage = ({ onClose }) => {
  return (
    <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50">
      <div className="bg-white p-6 rounded shadow-md text-center">
        <h2 className="text-xl font-semibold mb-4">Add Jewelry Successfully</h2>
        <button
          className="bg-blue-500 text-white px-4 py-2 rounded"
          onClick={onClose}
        >
          OK
        </button>
      </div>
    </div>
  );
};

const FormAddJewelry = () => {
  const [imagePreviewUrl, setImagePreviewUrl] = useState(
    "https://ap-south-1.linodeobjects.com/diamondshop-img/1718362498585_Screenshot%202024-06-14%20175431.png"
  );
  const [categories, setCategories] = useState([]);
  const [materials, setMaterials] = useState([]);
  const [sizes, setSizes] = useState([]);
  const [jewelryName, setJewelryName] = useState("");
  const [jewelryPrice, setJewelryPrice] = useState("");
  const [jewelryUrl, setJewelryUrl] = useState("");
  const [selectedCategory, setSelectedCategory] = useState("");
  const [selectedMaterial, setSelectedMaterial] = useState("");
  const [selectedSize, setSelectedSize] = useState("");
  const [showSubmitMessage, setShowSubmitMessage] = useState(false);
  const [errors, setErrors] = useState({});

  useEffect(() => {
    // Fetch categories, materials, and sizes from the API
    const fetchData = async () => {
      try {
        const response = await axios.get("/jewelry/all");
        setCategories(response.data.categories);
        setMaterials(response.data.materials);
        setSizes(response.data.sizes);
      } catch (error) {
        console.error("Error fetching data: ", error);
      }
    };
    fetchData();
  }, []);

  const handleFileChange = async (event) => {
    const file = event.target.files[0];
    const formData = new FormData();
    formData.append("file", file);

    try {
      const response = await axios.post("/upload", formData, {
        headers: {
          "Content-Type": "multipart/form-data",
        },
      });

      const imageUrl = response.data; // Assuming the API returns the URL in 'imageUrl' field
      setJewelryUrl(imageUrl);
      setImagePreviewUrl(imageUrl);
    } catch (error) {
      console.error("Error uploading file: ", error);
    }
  };

  const validate = async () => {
    const newErrors = {};
    if (!jewelryName) newErrors.jewelryName = "Jewelry name is required.";
    if (!jewelryPrice) newErrors.jewelryPrice = "Jewelry price is required.";
    if (jewelryPrice && jewelryPrice <= 0)
      newErrors.jewelryPrice = "Jewelry price must be greater than 0.";
    if (!selectedCategory) newErrors.selectedCategory = "Category is required.";
    if (!selectedMaterial) newErrors.selectedMaterial = "Material is required.";
    if (!selectedSize) newErrors.selectedSize = "Size is required.";
    if (!jewelryUrl) newErrors.jewelryUrl = "File image is required";

    // Check if the jewelry name is unique
    if (jewelryName) {
      try {
        const response = await axios.get(
          `/api/jewelry/check-name/${jewelryName}`
        );
        if (response.data) {
          newErrors.jewelryName = "Jewelry name already exists.";
        }
      } catch (error) {
        console.error("Error checking jewelry name:", error);
      }
    }

    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const handleSubmit = async (e) => {
    console.log();
    e.preventDefault();
    const isValid = await validate();
    if (!isValid) return;

    const jewelryData = {
      name: jewelryName,
      img: jewelryUrl,
      price: jewelryPrice,
      material: {
        materialId: selectedMaterial,
      },
      category: {
        categoryId: selectedCategory,
      },
      size: {
        sizeId: selectedSize,
      },
    };

    try {
      const response = await axios.post("/secure/jewelry", jewelryData);
      setShowSubmitMessage(true);
    } catch (error) {
      console.error("Add jewelry fail:", error);
    }
  };

  const handleReload = () => {
    window.location.reload(); // Reload the page
  };

  return (
    <>
      <div className="p-6">
        <div className="sm:mx-32 lg:mx-32 xl:mx-72">
          <div className="flex justify-between container mx-auto">
            <div className="w-full border-2 border-collapse">
              <div className="mt-4 px-4">
                <h1 className="text-3xl font-semibold py-7 px-5">
                  ADD JEWELRY
                </h1>
                <form onSubmit={handleSubmit} className="mx-5 my-5">
                  <label
                    className="relative block p-3 border-2 border-black rounded mb-5"
                    htmlFor="name"
                  >
                    <span className="text-md font-semibold text-zinc-900">
                      Name
                    </span>
                    <input
                      className="w-full bg-transparent p-0 text-sm text-gray-500 focus:outline-none"
                      id="name"
                      type="text"
                      value={jewelryName}
                      onChange={(e) => setJewelryName(e.target.value)}
                      placeholder="Jewelry name"
                    />
                    {errors.jewelryName && (
                      <span className="text-red-500 text-sm">
                        {errors.jewelryName}
                      </span>
                    )}
                  </label>
                  <label
                    className="relative block p-3 border-2 border-black rounded mb-5"
                    htmlFor="category"
                  >
                    <span className="text-md font-semibold text-zinc-900">
                      Category
                    </span>
                    <select
                      className="w-full bg-transparent p-0 text-sm text-gray-500 focus:outline-none"
                      id="category"
                      value={selectedCategory}
                      onChange={(e) => setSelectedCategory(e.target.value)}
                    >
                      <option
                        className="text-md font-semibold text-zinc-900"
                        value=""
                      >
                        Select a category
                      </option>
                      {categories.map((category) => (
                        <option
                          className="text-md font-semibold text-zinc-900"
                          key={category.categoryId}
                          value={category.categoryId}
                        >
                          {category.categoryName}
                        </option>
                      ))}
                    </select>
                    {errors.selectedCategory && (
                      <span className="text-red-500 text-sm">
                        {errors.selectedCategory}
                      </span>
                    )}
                  </label>
                  <label
                    className="relative block p-3 border-2 border-black rounded mb-5"
                    htmlFor="material"
                  >
                    <span className="text-md font-semibold text-zinc-900">
                      Material
                    </span>
                    <select
                      className="w-full bg-transparent p-0 text-sm text-gray-500 focus:outline-none"
                      id="material"
                      value={selectedMaterial}
                      onChange={(e) => setSelectedMaterial(e.target.value)}
                    >
                      <option
                        className="text-md font-semibold text-zinc-900"
                        value=""
                      >
                        Select a material
                      </option>
                      {materials.map((material) => (
                        <option
                          className="text-md font-semibold text-zinc-900"
                          key={material.materialId}
                          value={material.materialId}
                        >
                          {material.materialName}
                        </option>
                      ))}
                    </select>
                    {errors.selectedMaterial && (
                      <span className="text-red-500 text-sm">
                        {errors.selectedMaterial}
                      </span>
                    )}
                  </label>
                  <label
                    className="relative block p-3 border-2 border-black rounded mb-5"
                    htmlFor="size"
                  >
                    <span className="text-md font-semibold text-zinc-900">
                      Size
                    </span>
                    <select
                      className="w-full bg-transparent p-0 text-sm text-gray-500 focus:outline-none"
                      id="size"
                      value={selectedSize}
                      onChange={(e) => setSelectedSize(e.target.value)}
                    >
                      <option
                        className="text-md font-semibold text-zinc-900"
                        value=""
                      >
                        Select a size
                      </option>
                      {sizes.map((size) => (
                        <option
                          className="text-md font-semibold text-zinc-900"
                          key={size.sizeId}
                          value={size.sizeId}
                        >
                          {`${size.sizeNumber} ${size.unit} (${size.type})`}
                        </option>
                      ))}
                    </select>
                    {errors.selectedSize && (
                      <span className="text-red-500 text-sm">
                        {errors.selectedSize}
                      </span>
                    )}
                  </label>
                  <label
                    className="relative block p-3 border-2 mt-5 border-black rounded"
                    htmlFor="price"
                  >
                    <span className="text-md font-semibold text-zinc-900">
                      Price
                    </span>
                    <input
                      className="w-full p-0 text-sm border-none bg-transparent text-gray-500 focus:outline-none"
                      id="price"
                      type="number"
                      value={jewelryPrice}
                      onChange={(e) => setJewelryPrice(e.target.value)}
                      placeholder="Jewelry Price"
                    />
                    {errors.jewelryPrice && (
                      <span className="text-red-500 text-sm">
                        {errors.jewelryPrice}
                      </span>
                    )}
                  </label>
                  <div className="shrink-0 mt-5">
                    <img
                      className="h-40 w-40 object-cover"
                      src={imagePreviewUrl}
                      alt="Current profile photo"
                    />
                  </div>
                  <label className="block pt-2">
                    <span className="sr-only t-2">Choose profile photo</span>
                    <input
                      type="file"
                      className="w-full text-sm text-slate-500 file:mr-4 file:py-2 file:px-4 file:rounded-full file:border-0 file:text-sm file:font-semibold file:bg-pink-300 file:text-zinc-900 hover:file:bg-rose-300"
                      onChange={handleFileChange}
                    />
                    {errors.jewelryUrl && (
                      <span className="text-red-500 text-sm">
                        {errors.jewelryUrl}
                      </span>
                    )}
                  </label>

                  <button className="mt-5 border-2 px-5 py-2 rounded-lg border-black border-b-4 font-black translate-y-2 border-l-4">
                    Submit
                  </button>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
      {showSubmitMessage && <SubmitMessage onClose={handleReload} />}
    </>
  );
};

export default FormAddJewelry;
