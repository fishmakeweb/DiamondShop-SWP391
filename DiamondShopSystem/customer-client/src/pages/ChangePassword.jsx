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
    <div className="flex flex-col items-center justify-center min-h-screen bg-gray-100">
      <div className="w-full max-w-md p-6 bg-white rounded-lg shadow-lg">
        <div className="flex justify-between items-center text-lg font-semibold text-gray-800 mb-6">
          <div>Change Password</div>
        </div>
        <form onSubmit={handleChangePassword} className="space-y-4">
          <div>
            <label htmlFor="password" className="block font-medium">New Password *</label>
            <input
              id="password"
              type="password"
              className="w-full mt-1 p-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-blue-500"
              onChange={(e) => setPassword(e.target.value)}
            />
          </div>
          <div>
            <label htmlFor="confirmPassword" className="block font-medium">Confirm New Password *</label>
            <input
              id="confirmPassword"
              type="password"
              className="w-full mt-1 p-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-blue-500"
              value={confirmPassword}
              onChange={(e) => setConfirmPassword(e.target.value)}
            />
            {passwordError && <span className="text-red-500 mt-1">{passwordError}</span>}
          </div>
          <div className="flex justify-end space-x-4 mt-6">
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
