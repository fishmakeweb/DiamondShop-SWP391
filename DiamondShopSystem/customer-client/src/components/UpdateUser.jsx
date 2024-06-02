import React from 'react';
import PropTypes from 'prop-types';

function UpdateUser({ onClose }) {
  return (
    <div className="fixed inset-0 flex justify-center items-center bg-black bg-opacity-50">
      <div className="flex flex-col px-4 py-2.5 mt-24 w-full bg-white rounded-lg border border-solid border-slate-100 max-w-[1136px]">
        <div className="flex gap-5 text-lg font-semibold leading-4 text-right text-slate-800">
          <div className="flex-auto">Update Information</div>
          <button onClick={onClose} className="text-gray-500">&times;</button>
        </div>
        <div className="p-3 mt-12 bg-white rounded-lg border border-solid border-slate-100 text-slate-800">
          <label htmlFor="userId">User ID *</label>
          <input id="userId" type="text" className="w-full mt-1 p-2 border border-slate-300 rounded" />
        </div>
        <div className="flex gap-4 mt-4 text-slate-800">
          <div className="grow p-3 bg-white rounded-lg border border-solid border-slate-100">
            <label htmlFor="firstName">First Name *</label>
            <input id="firstName" type="text" className="w-full mt-1 p-2 border border-slate-300 rounded" />
          </div>
          <div className="grow p-3 bg-white rounded-lg border border-solid border-slate-100">
            <label htmlFor="lastName">Last Name *</label>
            <input id="lastName" type="text" className="w-full mt-1 p-2 border border-slate-300 rounded" />
          </div>
        </div>
        <div className="flex gap-4 mt-4 text-slate-800">
          <div className="p-3 bg-white rounded-lg border border-solid border-slate-100">
            <label htmlFor="email">Email ID *</label>
            <input id="email" type="email" className="w-full mt-1 p-2 border border-slate-300 rounded" />
          </div>
          <div className="p-3 bg-white rounded-lg border border-solid border-slate-100">
            <label htmlFor="mobile">Phone No</label>
            <input id="mobile" type="tel" className="w-full mt-1 p-2 border border-slate-300 rounded" />
          </div>
        </div>
        <div className="flex gap-5 justify-between self-end mt-24">
          <button className="px-6 py-2 font-semibold text-center text-white bg-sky-500 rounded-lg">
            Update
          </button>
          <button onClick={onClose} className="my-auto text-right text-slate-400">
            Cancel
          </button>
        </div>
      </div>
    </div>
  );
}

UpdateUser.propTypes = {
  onClose: PropTypes.func.isRequired,
};

export default UpdateUser;
