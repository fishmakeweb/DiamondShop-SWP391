import React, { useState } from 'react';

function LoginModal({ isOpen, onClose }) {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const handleLogin = (e) => {
        e.preventDefault();
        console.log('Login attempt with:', email, password);
        // Here you would typically handle the login logic, such as an API call.
        onClose(); // Close modal after login attempt (or handle differently based on response)
    };

    if (!isOpen) return null;

    return (
        <div className="fixed inset-0 bg-gray-500 bg-opacity-75 flex justify-center items-center ">
            <div className="bg-white p-5 rounded-lg shadow-lg">
                <div className="flex gap-5 justify-between px-11 py-8 w-full text-3xl text-black bg-white border-b border-solid border-stone-400 max-md:flex-wrap max-md:px-5">
                    <div className="flex gap-5 justify-between">
                        <img
                            loading="lazy"
                            src="https://cdn.builder.io/api/v1/image/assets/TEMP/0613006e3e56e9621b17c3cd1f1af563d72391d04585cc0a8f01c454d5b110fa?"
                            alt=""
                            className="shrink-0 self-start aspect-square w-[31px]"
                        />
                        <div>Log In</div>
                    </div>
                    <img
                        loading="lazy"
                        src="https://cdn.builder.io/api/v1/image/assets/TEMP/bfc96218bf522623257aa77aa9fc8238e2e164baa393e0f71d35ec4a2f1ef1fb?"
                        alt=""
                        className="shrink-0 self-start w-7 aspect-square"
                        onClick={onClose}
                    />
                </div>
                <form onSubmit={handleLogin}>
                    <div className="flex flex-col px-14 mt-10 w-full text-xl text-zinc-500 max-md:px-5 max-md:mt-10 max-md:max-w-full">
                        <div className="justify-center items-start p-5 rounded-sm max-md:px-5 max-md:max-w-full">
                            <input type="email"
                                placeholder="Email address"
                                value={email}
                                onChange={(e) => setEmail(e.target.value)}
                                className="w-full mb-4 px-4 py-6 border rounded"
                            />

                        </div>
                        <div className="justify-center items-start p-5 mt-10 whitespace-nowrap rounded-sm max-md:px-5 max-md:mt-10 max-md:max-w-full">
                            <input
                                type="password"
                                placeholder="Password"
                                value={password}
                                onChange={(e) => setPassword(e.target.value)}
                                className="w-full mb-4 px-4 py-6 border rounded"
                            />
                        </div>
                        <div className="self-end mt-3.5 text-base text-right font-[435] text-neutral-500">
                            Forgot Password
                        </div>
                    </div>
                    <div className="flex flex-col px-14 mt-6 w-full text-lg font-[435] max-md:px-5 max-md:max-w-full">
                        <div className="justify-center items-center px-16 py-6 text-xl text-center text-white rounded-sm border border-black border-solid bg-black bg-opacity-80 max-md:px-5 max-md:max-w-full">
                            LOG IN
                        </div>
                        <div className="flex gap-4 self-start mt-5 text-lg text-neutral-700">
                            <div className="shrink-0 self-start border border-solid border-neutral-400 h-[17px] w-[17px]" />
                            <div className="flex-auto">Keep me logged in</div>
                        </div>
                        <div className="self-center mt-9 text-center text-neutral-600">
                            Don’t have an account?
                        </div>
                        <div className="self-center mt-4 text-center text-stone-500">
                            Sign up now.
                        </div>
                    </div>
                </form>
            </div>
        </div>
    );
}

export default LoginModal;
