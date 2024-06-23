import React, {useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import axios from "../axios";
import { Link } from 'react-router-dom';
import SortBar from "../components/sortbar";
// import Pagination from "../components/Pagination";
import NewNavbar from "../components/NewNavbar";



export default function NewRelease() {
  const { page_number } = useParams(); // Get page_number from URL
  // const navigate = useNavigate();
  const [items, setItems] = useState([]);
  // const [totalPages, setTotalPages] = useState(1);
  // const [totalElements, setTotalElements] = useState();

  useEffect(() => {
    // const pageNumber = parseInt(page_number, 10) || 1;
    axios
      .get(`/products/newrelease/${page_number}`)
      .then(response => {
        setItems(response.data);
        // setTotalPages(response.data.totalPages);
        // setTotalElements(response.data.totalElements);
        console.log(response);
      })

      .catch(error => console.error("Error fetching jewelry data:", error));
  }, [page_number]);

  // const handlePageChange = (newPage) => {
  //   navigate(`/jewelry/page/${newPage}`); // Update URL when page changes
  // };

  return (
    <div className="bg-white">
      <NewNavbar/>
      <h1 className="self-center mt-32 mb-32 text-4xl text-center text-black">Jewelry</h1>
      <SortBar />
      <section className="w-fit mx-auto grid grid-cols-1 lg:grid-cols-3 md:grid-cols-2 justify-items-center justify-center gap-y-20 gap-x-14 mt-10 mb-5">
        {items.map((item) => (
          <div key={item.productId} className="w-72 bg-white rounded-xl hover:shadow-md duration-500">
            <Link to={`/jewelry/${item.productId}`}>
              <img
                src={`${item.img}`}
                alt={item.name}
                className="w-64 h-64 mt-2 mb-2 object-cover mx-auto"
              />
              <div className="px-4 py-3">
                <p className="text-darkgray text-sm font-normal truncate capitalize">{item.name}</p>
                <div className="flex items-center">
                  <p className="text-lightgray text-sm font-normal my-3">${item.price}</p>
                </div>
              </div>
            </Link>
          </div>
        ))}
      </section>
      {/* <Pagination totalPages={totalPages} currentPage={page_number} onPageChange={handlePageChange} /> */}
    </div>
  );
}
