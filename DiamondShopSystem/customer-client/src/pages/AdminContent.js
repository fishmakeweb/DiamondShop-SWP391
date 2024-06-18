import React, { useEffect, useState } from 'react';
import axios from '../axios.js';
import AddUser from '../pages/AddUser';
import UsersTable from '../pages/UserTable';

function Content() {
    const [isAddUserOverlay, setAddUserOverlay] = useState(false);
    const [isUpdateUserOverlay, setUpdateUserOverlay] = useState(false);
    const [usersInfo, setUsersInfo] = useState([]);
    const [currentUser, setCurrentUser] = useState(null);

    useEffect(() => {
        document.title = "Admin Page";
        fetchUsers();
    }, []);

    const fetchUsers = async () => {
        try {
            const response = await axios.get("/secure/staffs");
            const transformedData = response.data.map(user => ({
                staffId: user.staffId,
                fullName: user.fullName,
                email: user.email,
                username: user.username,
                role: user.role
            }));
            setUsersInfo(transformedData);
        } catch (error) {
            console.error("Error fetching users data:", error);
        }
    };

    const toggleAddUserOverlay = () => {
        setAddUserOverlay(!isAddUserOverlay);
    };

    const handleAddUser = () => {
        toggleAddUserOverlay();
        fetchUsers();
    };

    const handleDelUser = () => {
        fetchUsers();
    };

    const handleUpdateUser = (user) => {
        setCurrentUser(user);
        setUpdateUserOverlay(true);
    };

    const handleUpdateUserClose = () => {
        setCurrentUser(null);
        setUpdateUserOverlay(false);
        fetchUsers();
    };

    return (
        <>
            <div className="p-6">
                <div id="search" className="flex mt-4 max-w-full">
                    <div className="flex items-center mt-10 mr-3">
                        <button onClick={toggleAddUserOverlay} className="flex items-center gap-2 px-4 py-2 bg-black text-white rounded-lg">
                            Add Staff +
                        </button>
                    </div>
                </div>
                <UsersTable 
                    usersInfo={usersInfo} 
                    onDelete={handleDelUser}
                    onUpdate={handleUpdateUser}
                />
            </div>
            <AddUser
                isOpen={isAddUserOverlay}
                onClose={handleAddUser}
            />
            {isUpdateUserOverlay && (
                <AddUser
                    isOpen={isUpdateUserOverlay}
                    onClose={handleUpdateUserClose}
                    user={currentUser}
                />
            )}
        </>
    );
}

export default Content;
