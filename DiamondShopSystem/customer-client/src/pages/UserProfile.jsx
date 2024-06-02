import React, { useState } from 'react';
import PropTypes from 'prop-types';
import DeletePopUp from '../components/DeleteUser.jsx';
import UpdateUser from '../components/UpdateUser.jsx';
import Navbar from '../components/Navbar.js';
import Footer from '../components/Footer.js';

function App() {
  const [showDeletePopUp, setShowDeletePopUp] = useState(false);
  const [showUpdateUser, setShowUpdateUser] = useState(false);

  const handleDeleteClick = () => {
    setShowDeletePopUp(true);
  };

  const handleUpdateClick = () => {
    setShowUpdateUser(true);
  };

  const handleClosePopUp = () => {
    setShowDeletePopUp(false);
  };

  const handleCloseUpdate = () => {
    setShowUpdateUser(false);
  };

  return (
    <div className="flex flex-col bg-white">
        <Navbar />
      <div className="w-full min-h-[32px] max-md:max-w-full" />
      <div className="flex flex-col self-center mt-20 w-full max-w-[1121px] max-md:mt-10 max-md:max-w-full">
        <div className="text-4xl text-center text-black max-md:max-w-full">
          YOUR PROFILE
        </div>
        <div className="mt-7 max-md:max-w-full">
          <div className="flex gap-5 max-md:flex-col max-md:gap-0">
            <div className="flex flex-col w-[27%] max-md:ml-0 max-md:w-full">
              <img
                loading="lazy"
                srcSet="..."
                className="shrink-0 max-w-full aspect-square w-[200px] max-md:mt-10 max-sm:ml-20"
              />
            </div>
            <div className="flex flex-col ml-5 w-[73%] max-md:ml-0 max-md:w-full">
              <div className="flex flex-col grow mt-7 max-md:mt-10 max-md:max-w-full">
                <div className="text-4xl font-semibold text-black max-md:max-w-full">
                  Name: <span className="font-light">User 1</span>
                </div>
                <div className="mt-14 text-4xl font-semibold text-black max-md:mt-10 max-md:max-w-full">
                  Email: <span className="font-light">User1@gmail.com</span>
                </div>
                <div className="mt-11 text-4xl font-semibold text-black max-md:mt-10 max-md:max-w-full">
                  Phone Number: <span className="font-light">555-555-555</span>
                </div>
                <div className="mt-12 text-4xl font-semibold text-black max-md:mt-10 max-md:max-w-full">
                  Address:{" "}
                  <span className="font-light">
                    123 Chestnut St, New York, NY 12345
                  </span>
                </div>
                <div className="flex gap-5 justify-between items-start mt-12 max-w-full w-[332px] max-md:mt-10">
                  <button
                    className="px-4 py-2 bg-[#EFEBE8] text-black rounded max-md:mt-10"
                    onClick={handleUpdateClick}
                  >
                    Update Profile
                  </button>
                  <button
                    className="px-4 py-2 bg-[#EFEBE8] text-black rounded max-md:mt-10"
                    onClick={handleDeleteClick}
                  >
                    Delete Profile
                  </button>
                </div>
                {showUpdateUser && (
                  <UpdateUser onClose={handleCloseUpdate} />
                )}
                {showDeletePopUp && (
                  <DeletePopUp onClose={handleClosePopUp} />
                )}
              </div>
            </div>
          </div>
        </div>
      </div>
      <Footer />
    </div>
  );
}

App.propTypes = {
  onClose: PropTypes.func,
};

export default App;
