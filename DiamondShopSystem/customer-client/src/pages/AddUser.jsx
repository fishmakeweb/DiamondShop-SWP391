import React, { useEffect, useState } from "react";
import AuthService from "../components/AuthService";
import axios from "axios";

function AddUser({ isOpen, onClose, user }) {
    const [fullName, setFullName] = useState('');
    const [email, setEmail] = useState('');
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [role, setRole] = useState('');
    const [error, setError] = useState(null);
    const [validationErrors, setValidationErrors] = useState({});

    useEffect(() => {
        if (user) {
            setFullName(user.fullName);
            setEmail(user.email);
            setUsername(user.username);
            setRole(user.role.roleName);
            setPassword('');
        } else {
            setFullName('');
            setEmail('');
            setUsername('');
            setRole('');
            setPassword('');
        }
        setValidationErrors({});
        setError(null);
    }, [user]);

    const handleSaveUser = async (e) => {
        e.preventDefault();

        const errors = {};
        if (!fullName) errors.fullName = "Full name is required.";
        if (!email) {
            errors.email = "Email is required.";
        } else if (!/\S+@\S+\.\S+/.test(email)) {
            errors.email = "Email is invalid.";
        }
        if (!role) errors.role = "Role is required.";

        if (!user) {
            if (!username) errors.username = "Username is required.";
            if (!password) errors.password = "Password is required.";
        }

        if (Object.keys(errors).length > 0) {
            setValidationErrors(errors);
            return;
        }

        try {
            const staffData = {
                fullName,
                email,
                role: { roleName: role }
            };

            let response;
            if (user) {
                const updateUser = {
                    fullName,
                    email,
                    role: { roleName: role }
                };
                response = await axios.put(`http://localhost:8080/api/secure/staffs/${user.staffId}`, updateUser);
                console.log('Update successful: ', response);
            } else {
                response = await AuthService.registerStaff(staffData);
                console.log('Register successful: ', response);
            }
            onClose();
        } catch (error) {
            console.error('Operation failed: ', error);
            setError('Operation failed. Please try again.');
        }
    };

    if (!isOpen) return null;

    return (
        <div className={`overlay bg-black bg-opacity-50 fixed inset-0 z-50 flex items-center justify-center ${isOpen ? 'block' : 'hidden'}`}>
            <div className="modal bg-white p-8 rounded-lg w-96">
                <h2 className="text-lg font-semibold mb-4">{user ? 'Update Staff' : 'Add Staff'}</h2>
                {error && <div className="text-red-500 mb-4">{error}</div>}
                <form onSubmit={handleSaveUser}>
                    <div className="mb-4">
                        <label htmlFor="fullName" className="block">Full Name:</label>
                        <input
                            type="text"
                            id="fullName"
                            value={fullName}
                            onChange={(e) => setFullName(e.target.value)}
                            className="border border-gray-300 rounded px-3 py-2 w-full"
                        />
                        {validationErrors.fullName && <div className="text-red-500">{validationErrors.fullName}</div>}
                    </div>
                    <div className="mb-4">
                        <label htmlFor="email" className="block">Email:</label>
                        <input
                            type="email"
                            id="email"
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                            className="border border-gray-300 rounded px-3 py-2 w-full"
                        />
                        {validationErrors.email && <div className="text-red-500">{validationErrors.email}</div>}
                    </div>
                    <div className="mb-4">
                        <label htmlFor="role" className="block">Role:</label>
                        <select
                            id="role"
                            value={role}
                            onChange={(e) => setRole(e.target.value)}
                            className="border border-gray-300 rounded px-3 py-2 w-full"
                        >
                            <option value="">Select Role</option>
                            <option value="ROLE_ADMIN">Admin</option>
                            <option value="ROLE_SALE">Sales</option>
                        </select>
                        {validationErrors.role && <div className="text-red-500">{validationErrors.role}</div>}
                    </div>
                    {!user && (
                        <>
                            <div className="mb-4">
                                <label htmlFor="username" className="block">Username:</label>
                                <input
                                    type="text"
                                    id="username"
                                    value={username}
                                    onChange={(e) => setUsername(e.target.value)}
                                    className="border border-gray-300 rounded px-3 py-2 w-full"
                                />
                                {validationErrors.username && <div className="text-red-500">{validationErrors.username}</div>}
                            </div>
                            <div className="mb-4">
                                <label htmlFor="password" className="block">Password:</label>
                                <input
                                    type="password"
                                    id="password"
                                    value={password}
                                    onChange={(e) => setPassword(e.target.value)}
                                    className="border border-gray-300 rounded px-3 py-2 w-full"
                                />
                                {validationErrors.password && <div className="text-red-500">{validationErrors.password}</div>}
                            </div>
                        </>
                    )}
                    <div className="flex justify-end">
                        <button type="submit" className="bg-blue-500 text-white px-4 py-2 rounded mr-2">{user ? 'Update Staff' : 'Add Staff'}</button>
                        <button type="button" onClick={onClose} className="bg-gray-300 text-gray-700 px-4 py-2 rounded">Cancel</button>
                    </div>
                </form>
            </div>
        </div>
    );
}

export default AddUser;
