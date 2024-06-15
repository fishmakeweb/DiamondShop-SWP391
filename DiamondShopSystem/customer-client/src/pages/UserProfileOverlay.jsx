import React from 'react';
import { Link } from 'react-router-dom';

const UserProfileOverlay = ({ onClose, isOpen, doLogout }) => {
    if (!isOpen) return null;

    return (
        <div className="absolute top-0 right-0 mt-14 ml-50 w-60 z-50">
            <div className="dropdown ml-3">
                <ul className="dropdown-menu shadow-md shadow-black/5 z-30 py-1.5 rounded-md bg-white border border-gray-100 w-full max-w-[140px]">
                    <li>
                        <Link to={"/profile"} className="flex items-center text-[13px] py-1.5 px-4 text-gray-600 hover:text-[#f84525] hover:bg-gray-50">
                        Profile 
                        </Link>
                    </li>
                    <li>
                        <div className='className="flex items-center text-[13px] py-1.5 px-4 text-gray-600 hover:text-[#f84525] hover:bg-gray-50"'>
                            Followed products
                        </div>
                    </li>
                    <li>
                        <button onClick={onClose} className="flex items-center text-[13px] py-1.5 px-4 text-gray-600 hover:text-[#f84525] hover:bg-gray-50">
                            Close
                        </button>
                    </li>
                </ul>
            </div>
        </div>
    );
};

export default UserProfileOverlay;
