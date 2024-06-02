import * as React from "react";
import PropTypes from "prop-types";

function DeletePopUp({ onClose }) {
  return (
    <div className="fixed inset-0 flex justify-center items-center px-16 py-20 text-2xl font-bold text-black bg-slate-800 bg-opacity-50">
      <div className="flex justify-center items-center px-16 py-12 bg-white rounded-[30px] w-[761px] max-md:px-5">
        <div className="flex flex-col items-center">
          <img
            loading="lazy"
            src="https://cdn.builder.io/api/v1/image/assets/TEMP/0e582e82400f05d6f3e0c3707ccda6c55d9d7a2a78088c96724c16cfa8a4e55c?"
            className="max-w-full aspect-square w-[100px]"
          />
          <div className="mt-10">Delete successfully</div>
          <button
            onClick={onClose}
            className="mt-5 px-4 py-2 bg-blue-500 text-white rounded"
          >
            Close
          </button>
        </div>
      </div>
    </div>
  );
}

DeletePopUp.propTypes = {
  onClose: PropTypes.func.isRequired,
};

export default DeletePopUp;
