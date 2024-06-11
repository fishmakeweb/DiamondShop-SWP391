import React from "react";

function Location({ onClose }) {
    return (
        <div 
            className="fixed top-0 left-0 w-full h-full bg-black bg-opacity-50 flex justify-center items-center z-50"
            onClick={onClose}
        >
            <div 
                className="relative bg-white p-5 rounded"
                onClick={(e) => e.stopPropagation()} // Prevent closing when clicking inside the modal
            >
                <button 
                    className="absolute top-2 right-2 bg-red-500 text-white rounded-full w-6 h-6 flex justify-center items-center"
                    onClick={onClose}
                >
                    &times;
                </button>
                <iframe 
                    src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d979.6524982885437!2d106.80923926960885!3d10.841128916501571!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31752731176b07b1%3A0xb752b24b379bae5e!2zVHLGsOG7nW5nIMSQ4bqhaSBo4buNYyBGUFQgVFAuIEhDTQ!5e0!3m2!1svi!2s!4v1718106331955!5m2!1svi!2s" 
                    width="600" 
                    height="450" 
                    style={{border:0}} 
                    allowFullScreen="" 
                    loading="lazy" 
                    referrerPolicy="no-referrer-when-downgrade">
                </iframe>
            </div>
        </div>
    );
}

export default Location;
