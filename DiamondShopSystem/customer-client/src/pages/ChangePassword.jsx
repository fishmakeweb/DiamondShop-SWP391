import React, { useState, useEffect } from 'react';
import axios from 'axios';

function ChangePassword({ onClose, profile }) {
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [passwordError, setPasswordError] = useState('');
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');

  useEffect(() => {
    if (profile && profile.customer) {
      const { password } = profile.customer;
      setPassword(password || '');
    }
  }, [profile]);

  const handleChangePassword = async (e) => {
    e.preventDefault();
    if (password !== confirmPassword) {
      setPasswordError('Passwords do not match');
      return;
    }

    setLoading(true);
    try {
      const userPassword = {
        password
      };
      const response = await axios.put(`http://localhost:8080/api/secure/customers/password/${profile.customer.userId}`, userPassword);
      console.log('Update successful', response);
      onClose();
    } catch (error) {
      console.error('Operation failed: ', error);
      setError('Operation failed. Please try again.');
    }
    setLoading(false);
  };

  return (
    <div className="fixed inset-0 flex justify-center items-center bg-black bg-opacity-50 z-50">
      <div className="flex flex-col px-6 py-5 mt-18 w-full bg-white rounded-lg border border-solid border-gray-200 max-w-3xl shadow-lg">
        <div className="flex justify-between items-center text-lg font-semibold text-gray-800">
          <div>Change Password</div>
          <button onClick={onClose} className="text-gray-500 text-2xl">&times;</button>
        </div>
        <form onSubmit={handleChangePassword}>
          <div className="flex flex-col gap-4 mt-4 text-gray-800">
            <div className="flex flex-col p-3 bg-white rounded-lg border border-solid border-gray-200">
              <label htmlFor="password" className="font-medium">New Password *</label>
              <input
                id="password"
                type="password"
                className="w-full mt-1 p-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-blue-500"
                onChange={(e) => setPassword(e.target.value)}
              />
            </div>
            <div className="flex flex-col p-3 bg-white rounded-lg border border-solid border-gray-200">
              <label htmlFor="confirmPassword" className="font-medium">Confirm New Password *</label>
              <input
                id="confirmPassword"
                type="password"
                className="w-full mt-1 p-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-blue-500"
                value={confirmPassword}
                onChange={(e) => setConfirmPassword(e.target.value)}
              />
              {passwordError && <span className="text-red-500 mt-1">{passwordError}</span>}
            </div>
          </div>
          <div className="flex justify-end gap-4 mt-6">
            <button onClick={onClose} className="px-6 py-2 font-semibold text-gray-500 border border-gray-300 rounded-lg hover:bg-gray-100">
              Cancel
            </button>
            <button type="submit" className="px-6 py-2 font-semibold text-white bg-black rounded-lg" disabled={loading}>
              {loading ? 'Changing...' : 'Change'}
            </button>
          </div>
        </form>
        {error && <div className="text-red-500 mt-4">{error}</div>}
      </div>
    </div>
  );
}

export default ChangePassword;
