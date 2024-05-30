// import React, { useState } from 'react';
// import { Link } from 'react-router-dom';
// import LoginModal from './LoginModal'; // Import the LoginModal component


// function HoverImage({ defaultSrc }) {
//     const hoverSrc = defaultSrc + "-hover";
//     const [imgSrc, setImgSrc] = useState(defaultSrc + ".svg");
//     return (
//         <img
//             src={imgSrc}
//             alt={defaultSrc}
//             onMouseEnter={() => setImgSrc(hoverSrc + ".svg")} // Change src to hover image on mouse enter
//             onMouseLeave={() => setImgSrc(defaultSrc + ".svg")} // Revert src to default on mouse leave
//             className="w-5 h-5"
//         />
//     );
// }



// function Navbar() {
//     const [isLoginModalOpen, setLoginModalOpen] = useState(false);

//     const toggleLoginModal = () => {
//         setLoginModalOpen(!isLoginModalOpen);
//     };
//     return (
//         <div className="relative flex z-0 gap-5 px-12 py-6 w-full bg-white border border-solid border-stone-400 max-md:flex-wrap max-md:px-5 max-md:max-w-full">
//             <div className="flex gap-5 items-center self-start text-lg text-center text-black text-opacity-70 max-md:flex-wrap max-md:max-w-full">
//                 <img
//                     loading="lazy"
//                     src="/icon/homepage/location.svg"
//                     alt="location-icon"
//                     className="shrink-0 self-stretch aspect-square w-[26px]"
//                 />
//                 <div class="flex justify-center items-center hover:text-custom-brown shrink-0 self-stretch my-auto font-karla text-custom font-normal" >DIAMOND</div>
//                 <Link to="/jewelry" className="flex justify-center items-center hover:text-custom-brown shrink-0 self-stretch my-auto font-karla text-custom font-normal">JEWELRY</Link>
//                 <div className="flex justify-center items-center hover:text-custom-brown shrink-0 self-stretch my-auto font-karla text-custom font-normal">NEW RELEASES</div>
//             </div>
//             <Link to="/" className="flex-auto my-auto hover:text-custom-brown text-center text-black font-cormorant text-2xl font-normal">
//                 H E P H A E S T U S
//             </Link>
//             <div className="flex gap-5 justify-between items-center text-base whitespace-nowrap text-neutral-500">
//                 <div className="flex gap-5 justify-center self-stretch px-4 py-2 border-b border-solid border-stone-300 border-opacity-90">
//                     <div className="my-auto">Search</div>
//                     <img
//                         loading="lazy"
//                         src="http://localhost:3000/icon/homepage/ant-design_search-outlined.svg"
//                         alt="search-icon"
//                         className="shrink-0 aspect-square w-[17px]"
//                     />
//                 </div>
//                 <HoverImage defaultSrc="http://localhost:3000/icon/homepage/HEADER%20HEART%20REAL" />
//                 <HoverImage defaultSrc="http://localhost:3000/icon/homepage/ep_shopping-bag" />
//                 <div onClick={toggleLoginModal} className="cursor-pointer">
//                     <HoverImage defaultSrc="http://localhost:3000/icon/homepage/bi_person" />
//                 </div>
//             </div>
//             <LoginModal isOpen={isLoginModalOpen} onClose={toggleLoginModal} />
//         </div>
//     );
// }

// export default Navbar;




import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import LoginModal from './LoginModal'; 
import SignUpModal from './SignUpModal'; 

function HoverImage({ defaultSrc }) {
    const hoverSrc = defaultSrc + "-hover";
    const [imgSrc, setImgSrc] = useState(defaultSrc + ".svg");
    return (
        <img
            src={imgSrc}
            alt={defaultSrc}
            onMouseEnter={() => setImgSrc(hoverSrc + ".svg")} 
            onMouseLeave={() => setImgSrc(defaultSrc + ".svg")} 
            className="w-5 h-5"
        />
    );
}

function Navbar() {
    const [isLoginModalOpen, setLoginModalOpen] = useState(false);
    const [isSignUpModalOpen, setSignUpModalOpen] = useState(false);

    const toggleLoginModal = () => {
        setLoginModalOpen(!isLoginModalOpen);
        setSignUpModalOpen(false);
    };

    const toggleSignUpModal = () => {
        setSignUpModalOpen(!isSignUpModalOpen);
        setLoginModalOpen(false);
    };

    return (
        <div className="relative flex z-0 gap-5 px-12 py-6 w-full bg-white border border-solid border-stone-400 max-md:flex-wrap max-md:px-5 max-md:max-w-full">
            <div className="flex gap-5 items-center self-start text-lg text-center text-black text-opacity-70 max-md:flex-wrap max-md:max-w-full">
                <img
                    loading="lazy"
                    src="/icon/homepage/location.svg"
                    alt="location-icon"
                    className="shrink-0 self-stretch aspect-square w-[26px]"
                />
                <div className="flex justify-center items-center hover:text-custom-brown shrink-0 self-stretch my-auto font-karla text-custom font-normal">DIAMOND</div>
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
                        src="http://localhost:3000/icon/homepage/ant-design_search-outlined.svg"
                        alt="search-icon"
                        className="shrink-0 aspect-square w-[17px]"
                    />
                </div>
                <HoverImage defaultSrc="http://localhost:3000/icon/homepage/HEADER%20HEART%20REAL" />
                <HoverImage defaultSrc="http://localhost:3000/icon/homepage/ep_shopping-bag" />
                <div onClick={toggleLoginModal} className="cursor-pointer">
                    <HoverImage defaultSrc="http://localhost:3000/icon/homepage/bi_person" />
                </div>
            </div>
            <LoginModal 
                isOpen={isLoginModalOpen} 
                onClose={toggleLoginModal} 
                openSignUp={toggleSignUpModal} 
            />
            <SignUpModal 
                isOpen={isSignUpModalOpen} 
                onClose={toggleSignUpModal} 
                openLogin={toggleLoginModal} 
            />
        </div>
    );
}

export default Navbar;
