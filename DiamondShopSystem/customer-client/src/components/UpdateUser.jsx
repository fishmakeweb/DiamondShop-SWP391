import React, { useState, useEffect } from 'react';
import axios from 'axios';

function UpdateUser({ onClose, profile }) {
  const [fullName, setFullName] = useState('');
  const [email, setEmail] = useState('');
  const [address, setAddress] = useState('');
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    if (profile && profile.customer) {
      const { fullName, email, address } = profile.customer;
      setFullName(fullName || '');
      setEmail(email || '');
      setAddress(address || '');
    }
  }, [profile]);

  const validateInputs = () => {
    if (!fullName.trim() || !email.trim() || !address.trim()) {
      setError('All fields are required');
      return false;
    }

    if (!validateEmail(email)) {
      setError('Please enter a valid email address');
      return false;
    }

    setError('');
    return true;
  };

  const validateEmail = (email) => {
    // Basic email validation regex
    const re =
      /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    return re.test(String(email).toLowerCase());
  };

  const handleUpdate = async (e) => {
    e.preventDefault();

    if (!validateInputs()) {
      return;
    }

    setLoading(true);

    try {
      const userData = {
        fullName,
        email,
        address,
      };
      const response = await axios.put(`http://localhost:8080/api/secure/customers/${profile.customer.userId}`, userData);
      console.log('Update successful', response);
      onClose();
    } catch (error) {
      console.error('Operation failed: ', error);
      setError('Operation failed. Please try again.');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="fixed inset-0 flex justify-center items-center bg-black bg-opacity-50 z-50">
      <div className="flex flex-col px-6 py-5 mt-22 w-full bg-white rounded-lg border border-solid border-gray-200 max-w-3xl shadow-lg">
        <div className="flex justify-between items-center text-lg font-semibold text-gray-800">
          <div>Update Information</div>
          <button onClick={onClose} className="text-gray-500 text-2xl">&times;</button>
        </div>
        <form onSubmit={handleUpdate}>
          <div className="flex flex-col gap-4 mt-4 text-gray-800">
            <div className="flex flex-col p-3 bg-white rounded-lg border border-solid border-gray-200">
              <label htmlFor="fullName" className="font-medium">Full Name *</label>
              <input
                id="fullName"
                type="text"
                value={fullName}
                onChange={(e) => setFullName(e.target.value)}
                className="w-full mt-1 p-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-blue-500"
              />
            </div>
            <div className="flex flex-col p-3 bg-white rounded-lg border border-solid border-gray-200">
              <label htmlFor="email" className="font-medium">Email *</label>
              <input
                id="email"
                type="email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                className="w-full mt-1 p-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-blue-500"
              />
            </div>
            <div className="flex flex-col p-3 bg-white rounded-lg border border-solid border-gray-200">
              <label htmlFor="address" className="font-medium">Address *</label>
              <input
                id="address"
                type="text"
                value={address}
                onChange={(e) => setAddress(e.target.value)}
                className="w-full mt-1 p-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-blue-500"
              />
            </div>
            {error && <div className="text-red-500">{error}</div>}
          </div>
          <div className="flex justify-end gap-4 mt-6">
            <button type="button" onClick={onClose} className="px-6 py-2 font-semibold text-gray-500 border border-gray-300 rounded-lg hover:bg-gray-100">
              Cancel
            </button>
            <button type="submit" className="px-6 py-2 font-semibold text-white bg-black rounded-lg" disabled={loading}>
              {loading ? 'Updating...' : 'Update'}
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}

export default UpdateUser;
