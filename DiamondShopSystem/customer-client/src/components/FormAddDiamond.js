import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import axios from "../axios";

const SubmitMessage = ({ onClose }) => {
  return (
    <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50">
      <div className="bg-white p-6 rounded shadow-md text-center">
        <h2 className="text-xl font-semibold mb-4">Add Diamond Successfully</h2>
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

const FormAddDiamond = () => {
  const [imagePreviewUrl, setImagePreviewUrl] = useState(
    "https://diamondshop-img.ap-south-1.linodeobjects.com/1718429728643_Screenshot%202024-06-15%20123518.png"
  );
  const [shapes, setShapes] = useState([]);
  const [measurements, setMeasurements] = useState([]);
  const [colors, setColors] = useState([]);
  const [cuts, setCuts] = useState([]);
  const [carats, setCarats] = useState([]);
  const [clarities, setClarities] = useState([]);
  const [giaIssueDate, setGiaIssueDate] = useState("");
  const [diamondShape, setDiamondShape] = useState("");
  const [diamondMeasurement, setDiamondMeasurement] = useState("");
  const [diamondColor, setDiamondColor] = useState("");
  const [diamondCut, setDiamondCut] = useState("");
  const [diamondCarat, setDiamondCarat] = useState("");
  const [diamondClarity, setDiamondClarity] = useState("");
  const [diamondPrice, setDiamondPrice] = useState("");
  const [diamondUrl, setDiamondUrl] = useState("");
  const [showSubmitMessage, setShowSubmitMessage] = useState(false);
  const [errors, setErrors] = useState({});

  useEffect(() => {
    // Fetch diamond attributes from the API
    const fetchData = async () => {
      try {
        const response = await axios.get("/diamonds/all");
        setShapes(response.data.shapes);
        setMeasurements(response.data.measurements);
        setColors(response.data.colors);
        setCuts(response.data.cuts);
        setCarats(response.data.carats);
        setClarities(response.data.clarities);
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
      setDiamondUrl(imageUrl);
      setImagePreviewUrl(imageUrl);
    } catch (error) {
      console.error("Error uploading file: ", error);
    }
  };

  const validate = () => {
    const newErrors = {};
    if (!diamondShape) newErrors.diamondShape = "Shape is required.";
    if (!diamondMeasurement)
      newErrors.diamondMeasurement = "Measurement is required.";
    if (!diamondColor) newErrors.diamondColor = "Color is required.";
    if (!diamondCut) newErrors.diamondCut = "Cut is required.";
    if (!diamondCarat) newErrors.diamondCarat = "Carat is required.";
    if (!diamondClarity) newErrors.diamondClarity = "Clarity is required.";
    if (!giaIssueDate) newErrors.giaIssueDate = "GIA issue date is required.";
    if (!diamondPrice) newErrors.diamondPrice = "Price is required.";
    if (diamondPrice && diamondPrice <= 0)
      newErrors.diamondPrice = "Price must be greater than 0.";
    if (!diamondUrl) newErrors.diamondUrl = "File image is required.";

    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!validate()) return;

    const diamondData = {
      shape: { shapeId: diamondShape },
      measurement: { measurementId: diamondMeasurement },
      color: { colorId: diamondColor },
      cut: { cutId: diamondCut },
      carat: { caratId: diamondCarat },
      clarity: { clarityId: diamondClarity },
      gia: { issueDate: giaIssueDate },
      price: diamondPrice,
      img: diamondUrl,
    };

    try {
      await axios.post("/secure/diamonds", diamondData);
      setShowSubmitMessage(true);
    } catch (error) {
      console.error("Add diamond fail:", error);
    }
  };

  const handleReload = () => {
    window.location.reload(); // Reload the page
  };

  return (
    <>
      <div className="p-6">
        <div className="sm:mx-32 lg:mx-32 xl:mx-72">
          <div className="flex justify-between container-fluid mx-auto">
            <div className="w-full border-2 border-collapse">
              <div className="mt-4 px-4">
                <h1 className="text-3xl font-semibold py-7 px-5">
                  ADD DIAMOND
                </h1>
                <div className="flex items-end justify-end">
                <button className="flex items-end justify-end w-1/2 px-5 py-2 text-sm tracking-wide text-white transition-colors duration-200 bg-blue-500 rounded-lg shrink-0 sm:w-auto gap-x-2 hover:bg-blue-600 dark:hover:bg-blue-500 dark:bg-blue-600">
              <svg
                xmlns="http://www.w3.org/2000/svg"
                fill="none"
                viewBox="0 0 24 24"
                strokeWidth="1.5"
                stroke="currentColor"
                className="w-5 h-5"
              >
                <path
                  strokeLinecap="round"
                  strokeLinejoin="round"
                  d="M12 9v6m3-3H9m12 0a9 9 0 11-18 0 9 9 0 0118 0z"
                />
              </svg>
              <Link to="/viewalldiamond">
                <span>View Diamond</span>
              </Link>
            </button>
                </div>
                
                <form onSubmit={handleSubmit} className="mx-5 my-5">
                  <label
                    className="relative block p-3 border-2 border-black rounded mb-5"
                    htmlFor="shape"
                  >
                    <span className="text-md font-semibold text-zinc-900">
                      Shape
                    </span>
                    <select
                      className="w-full bg-transparent p-0 text-sm text-gray-500 focus:outline-none"
                      id="shape"
                      value={diamondShape}
                      onChange={(e) => setDiamondShape(e.target.value)}
                    >
                      <option
                        className="text-md font-semibold text-zinc-900"
                        value=""
                      >
                        Select a shape
                      </option>
                      {shapes.map((shape) => (
                        <option
                          className="text-md font-semibold text-zinc-900"
                          key={shape.shapeId}
                          value={shape.shapeId}
                        >
                          {shape.shapeDescription}
                        </option>
                      ))}
                    </select>
                    {errors.diamondShape && (
                      <span className="text-red-500 text-sm">
                        {errors.diamondShape}
                      </span>
                    )}
                  </label>

                  <label
                    className="relative block p-3 border-2 border-black rounded mb-5"
                    htmlFor="measurement"
                  >
                    <span className="text-md font-semibold text-zinc-900">
                      Measurement
                    </span>
                    <select
                      className="w-full bg-transparent p-0 text-sm text-gray-500 focus:outline-none"
                      id="measurement"
                      value={diamondMeasurement}
                      onChange={(e) => setDiamondMeasurement(e.target.value)}
                    >
                      <option
                        className="text-md font-semibold text-zinc-900"
                        value=""
                      >
                        Select a measurement
                      </option>
                      {measurements.map((measurement) => (
                        <option
                          className="text-md font-semibold text-zinc-900"
                          key={measurement.measurementId}
                          value={measurement.measurementId}
                        >
                          {`Length: ${measurement.length}, Width: ${measurement.width}, Height: ${measurement.height}`}
                        </option>
                      ))}
                    </select>
                    {errors.diamondMeasurement && (
                      <span className="text-red-500 text-sm">
                        {errors.diamondMeasurement}
                      </span>
                    )}
                  </label>

                  <label
                    className="relative block p-3 border-2 border-black rounded mb-5"
                    htmlFor="color"
                  >
                    <span className="text-md font-semibold text-zinc-900">
                      Color
                    </span>
                    <select
                      className="w-full bg-transparent p-0 text-sm text-gray-500 focus:outline-none"
                      id="color"
                      value={diamondColor}
                      onChange={(e) => setDiamondColor(e.target.value)}
                    >
                      <option
                        className="text-md font-semibold text-zinc-900"
                        value=""
                      >
                        Select a color
                      </option>
                      {colors.map((color) => (
                        <option
                          className="text-md font-semibold text-zinc-900"
                          key={color.colorId}
                          value={color.colorId}
                        >
                          {color.colorDescription}
                        </option>
                      ))}
                    </select>
                    {errors.diamondColor && (
                      <span className="text-red-500 text-sm">
                        {errors.diamondColor}
                      </span>
                    )}
                  </label>

                  <label
                    className="relative block p-3 border-2 border-black rounded mb-5"
                    htmlFor="cut"
                  >
                    <span className="text-md font-semibold text-zinc-900">
                      Cut
                    </span>
                    <select
                      className="w-full bg-transparent p-0 text-sm text-gray-500 focus:outline-none"
                      id="cut"
                      value={diamondCut}
                      onChange={(e) => setDiamondCut(e.target.value)}
                    >
                      <option
                        className="text-md font-semibold text-zinc-900"
                        value=""
                      >
                        Select a cut
                      </option>
                      {cuts.map((cut) => (
                        <option
                          className="text-md font-semibold text-zinc-900"
                          key={cut.cutId}
                          value={cut.cutId}
                        >
                          {cut.cutDescription}
                        </option>
                      ))}
                    </select>
                    {errors.diamondCut && (
                      <span className="text-red-500 text-sm">
                        {errors.diamondCut}
                      </span>
                    )}
                  </label>

                  <label
                    className="relative block p-3 border-2 border-black rounded mb-5"
                    htmlFor="carat"
                  >
                    <span className="text-md font-semibold text-zinc-900">
                      Carat
                    </span>
                    <select
                      className="w-full bg-transparent p-0 text-sm text-gray-500 focus:outline-none"
                      id="carat"
                      value={diamondCarat}
                      onChange={(e) => setDiamondCarat(e.target.value)}
                    >
                      <option
                        className="text-md font-semibold text-zinc-900"
                        value=""
                      >
                        Select a carat
                      </option>
                      {carats.map((carat) => (
                        <option
                          className="text-md font-semibold text-zinc-900"
                          key={carat.caratId}
                          value={carat.caratId}
                        >
                          {carat.carat}
                        </option>
                      ))}
                    </select>
                    {errors.diamondCarat && (
                      <span className="text-red-500 text-sm">
                        {errors.diamondCarat}
                      </span>
                    )}
                  </label>

                  <label
                    className="relative block p-3 border-2 border-black rounded mb-5"
                    htmlFor="clarity"
                  >
                    <span className="text-md font-semibold text-zinc-900">
                      Clarity
                    </span>
                    <select
                      className="w-full bg-transparent p-0 text-sm text-gray-500 focus:outline-none"
                      id="clarity"
                      value={diamondClarity}
                      onChange={(e) => setDiamondClarity(e.target.value)}
                    >
                      <option
                        className="text-md font-semibold text-zinc-900"
                        value=""
                      >
                        Select a clarity
                      </option>
                      {clarities.map((clarity) => (
                        <option
                          className="text-md font-semibold text-zinc-900"
                          key={clarity.clarityId}
                          value={clarity.clarityId}
                        >
                          {clarity.clarityDescription}
                        </option>
                      ))}
                    </select>
                    {errors.diamondClarity && (
                      <span className="text-red-500 text-sm">
                        {errors.diamondClarity}
                      </span>
                    )}
                  </label>

                  <label
                    className="relative block p-3 border-2 border-black rounded mb-5"
                    htmlFor="gia"
                  >
                    <span className="text-md font-semibold text-zinc-900">
                      GIA Issue Date
                    </span>
                    <input
                      className="w-full bg-transparent p-0 text-sm text-gray-500 focus:outline-none"
                      id="gia"
                      type="date"
                      value={giaIssueDate}
                      onChange={(e) => setGiaIssueDate(e.target.value)}
                    />
                    {errors.giaIssueDate && (
                      <span className="text-red-500 text-sm">
                        {errors.giaIssueDate}
                      </span>
                    )}
                  </label>

                  <label
                    className="relative block p-3 border-2 border-black rounded mb-5"
                    htmlFor="price"
                  >
                    <span className="text-md font-semibold text-zinc-900">
                      Price
                    </span>
                    <input
                      className="w-full p-0 text-sm border-none bg-transparent text-gray-500 focus:outline-none"
                      id="price"
                      type="number"
                      value={diamondPrice}
                      onChange={(e) => setDiamondPrice(e.target.value)}
                      placeholder="Diamond Price"
                    />
                    {errors.diamondPrice && (
                      <span className="text-red-500 text-sm">
                        {errors.diamondPrice}
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
                    {errors.diamondUrl && (
                      <span className="text-red-500 text-sm">
                        {errors.diamondUrl}
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

export default FormAddDiamond;
