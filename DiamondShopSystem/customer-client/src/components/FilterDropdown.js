import React, { useState, useRef, useEffect } from 'react';
import { Link } from 'react-router-dom';

function FilterDropdown({ title, items, onItemSelect }) {
    const [isOpen, setIsOpen] = useState(false);
    const dropdownRef = useRef(null);

    useEffect(() => {
        const handleClickOutside = (event) => {
            if (dropdownRef.current && !dropdownRef.current.contains(event.target)) {
                setIsOpen(false);
            }
        };

        document.addEventListener("mousedown", handleClickOutside);
        return () => {
            document.removeEventListener("mousedown", handleClickOutside);
        };
    }, []);

    const toggleDropdown = () => setIsOpen(!isOpen);

    return (
        <div ref={dropdownRef} className="relative text-center cursor-pointer">
            <div onClick={toggleDropdown}>
                {title}
            </div>
            {isOpen && (
                <div className="absolute left-1/2 transform -translate-x-1/2 mt-2 bg-white border border-gray-300 rounded shadow-lg z-10 max-h-60 overflow-y-auto w-40">
                    {items.map(item => (
                        <Link key={item.id} to={item.link} onClick={() => onItemSelect(item.id)} className="p-2 block hover:bg-gray-200">
                            {item.name}
                        </Link>
                    ))}
                </div>
            )}
        </div>
    );
}

export default FilterDropdown;
