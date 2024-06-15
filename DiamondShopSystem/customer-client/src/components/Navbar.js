import React, { useState, useContext } from 'react';
import { Link } from 'react-router-dom';
import LoginModal from './LoginModal';
import SignUpModal from './SignUpModal';
import Cart from './Cart';
import CartContext from './CartContext';
import AuthService from './AuthService';
import { useNavigate } from 'react-router-dom';
import Location from './Location';
import UserProfileOverlay from '../pages/UserProfileOverlay';

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
    const [isCartOpen, setCartOpen] = useState(false);
    const [isLocationOpen, setLocationOpen] = useState(false);
    const [isUserProfileOverlayOpen, setUserProfileOverlayOpen] = useState(false);

    const navigate = useNavigate();
    const { cart } = useContext(CartContext);

    const toggleLoginModal = () => {
        setLoginModalOpen(!isLoginModalOpen);
        setSignUpModalOpen(false);
        setCartOpen(false);
        setLocationOpen(false);
        setUserProfileOverlayOpen(false);
    };

    const toggleSignUpModal = () => {
        setSignUpModalOpen(!isSignUpModalOpen);
        setLoginModalOpen(false);
        setCartOpen(false);
        setLocationOpen(false);
        setUserProfileOverlayOpen(false);
    };

    const toggleCartModal = () => {
        setCartOpen(!isCartOpen);
        setLoginModalOpen(false);
        setSignUpModalOpen(false);
        setLocationOpen(false);
        setUserProfileOverlayOpen(false);
    };

    const toggleLocation = () => {
        setLocationOpen(!isLocationOpen);
        setLoginModalOpen(false);
        setSignUpModalOpen(false);
        setCartOpen(false);
        setUserProfileOverlayOpen(false);
    };

    const toggleUserProfileOverlay = () => {
        setUserProfileOverlayOpen(!isUserProfileOverlayOpen);
        setLoginModalOpen(false);
        setSignUpModalOpen(false);
        setCartOpen(false);
        setLocationOpen(false);
    };

    const handleLogout = () => {
        if (window.confirm('Are you sure you want to logout?')) {
            navigate('/');
            AuthService.logout();
            window.location.reload();
        }
    };

    const isAuthenticated = AuthService.isAuthenticated();

    return (
        <>
            <div className="absolute flex z-0 gap-5 px-12 py-6 w-full bg-white border border-solid border-stone-400 max-md:flex-wrap max-md:px-5 max-md:max-w-full">
                <div className="flex gap-5 items-center self-start text-lg text-center text-black text-opacity-70 max-md:flex-wrap max-md:max-w-full">
                    <button onClick={toggleLocation} className="shrink-0 self-stretch aspect-square w-[26px] cursor-pointer">
                        <img
                            loading="lazy"
                            src="/icon/homepage/location.svg"
                            alt="location-icon"
                        />
                    </button>
                    <Link to="/diamonds" className="flex justify-center items-center hover:text-custom-brown shrink-0 self-stretch my-auto font-karla text-custom font-normal">DIAMOND</Link>
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
                            src="https://diamond-shop-swp-391.vercel.app/icon/homepage/ant-design_search-outlined.svg"
                            alt="search-icon"
                            className="shrink-0 aspect-square w-[17px]"
                        />
                    </div>
                    <HoverImage defaultSrc="https://diamond-shop-swp-391.vercel.app/icon/homepage/HEADER%20HEART%20REAL" />
                    <div className="relative cursor-pointer" onClick={toggleCartModal}>
                        <HoverImage defaultSrc="https://diamond-shop-swp-391.vercel.app/icon/homepage/ep_shopping-bag" />
                        {cart.length > 0 && (
                            <div className="absolute bottom-0 right-0 w-2 h-2 bg-[#B6A69D] rounded-full"></div>
                        )}
                    </div>
                    {!isAuthenticated ? (
                        <div onClick={toggleLoginModal} className="cursor-pointer">
                            <HoverImage defaultSrc="https://diamond-shop-swp-391.vercel.app/icon/homepage/bi_person" />
                        </div>
                    ) : (
                        <>
                            <div onClick={toggleUserProfileOverlay} className="cursor-pointer">
                            <HoverImage defaultSrc="https://png.pngtree.com/png-clipart/20191120/original/pngtree-outline-user-icon-png-image_5045523.jpg" />
                            </div>
                            <div onClick={handleLogout} className="cursor-pointer">
                                Logout
                            </div>
                        </>
                    )}
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
                <Cart
                    isOpen={isCartOpen}
                    onClose={toggleCartModal}
                />
                <UserProfileOverlay
                    isOpen={isUserProfileOverlayOpen}
                    onClose={toggleUserProfileOverlay}
                />
            </div>
            {isLocationOpen && <Location onClose={toggleLocation} />}
        </>
    );
}

export default Navbar;
