import React, { useState } from 'react';
import { Link } from 'react-router-dom';

function HoverImage({defaultSrc}) {
    // Define the initial and hover image sources
    const hoverSrc = defaultSrc+"-hover";
    // State to manage the image source
    const [imgSrc, setImgSrc] = useState(defaultSrc+".svg");

    return (
        <img
            src={imgSrc}
            alt="defaultSrc"
            onMouseEnter={() => setImgSrc(hoverSrc+".svg")} // Change src to hover image on mouse enter
            onMouseLeave={() => setImgSrc(defaultSrc+".svg")} // Revert src to default on mouse leave
            className="w-5 h-5"
        />
    );
}



function Navbar() {
    return (
        <div className="flex z-10 gap-5 px-12 py-6 w-full bg-white border border-solid border-stone-400 max-md:flex-wrap max-md:px-5 max-md:max-w-full">
            <div className="flex gap-5 items-center self-start text-lg text-center text-black text-opacity-70 max-md:flex-wrap max-md:max-w-full">
                <img
                    loading="lazy"
                    src="/icon/homepage/location.svg"
                    alt="location-icon"
                    className="shrink-0 self-stretch aspect-square w-[26px]"
                />
                <div class="flex justify-center items-center hover:text-custom-brown shrink-0 self-stretch my-auto font-karla text-custom font-normal" >DIAMOND</div>
                <Link to="/jewelry" className="flex justify-center items-center hover:text-custom-brown shrink-0 self-stretch my-auto font-karla text-custom font-normal">JEWELRY</Link>
                <div className="flex justify-center items-center hover:text-custom-brown shrink-0 self-stretch my-auto font-karla text-custom font-normal">NEW RELEASES</div>
            </div>
            <Link to="/" className="flex-auto my-auto hover:text-custom-brown text-center text-black font-cormorant text-2xl font-normal">
                H E P H A E S T U S
            </Link>
            <div className="flex gap-5 justify-between items-center text-base whitespace-nowrap text-neutral-500">
                <div className="flex gap-5 justify-center self-stretch px-4 py-2 border-b border-solid border-stone-300 border-opacity-90">
                    <div className="my-auto">Search</div>
                    <img
                        loading="lazy"
                        src="icon/homepage/ant-design_search-outlined.svg"
                        alt="search-icon"
                        className="shrink-0 aspect-square w-[17px]"
                    />
                </div>
                <HoverImage defaultSrc="icon\homepage\HEADER HEART REAL"/>
                <HoverImage defaultSrc=" icon\homepage\ep_shopping-bag"/>
                <HoverImage defaultSrc="icon\homepage\bi_person"/>
            </div>
        </div>
    );
}

export default Navbar;

