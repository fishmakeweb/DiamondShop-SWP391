import * as React from "react";

function Login() {
  return (
    <div className="flex flex-col pb-4 max-w-screen-sm rounded shadow-sm z-10">
      <div className="flex gap-5 justify-between px-11 py-8 w-full text-3xl text-black bg-white border-b border-solid border-stone-400 max-md:flex-wrap max-md:px-5 max-md:max-w-full">
        <div className="flex gap-5 justify-between">
          <img
            loading="lazy"
            src="https://cdn.builder.io/api/v1/image/assets/TEMP/0613006e3e56e9621b17c3cd1f1af563d72391d04585cc0a8f01c454d5b110fa?"
            className="shrink-0 self-start aspect-square w-[31px]"
          />
          <div>Log In</div>
        </div>
        <img
          loading="lazy"
          src="https://cdn.builder.io/api/v1/image/assets/TEMP/bfc96218bf522623257aa77aa9fc8238e2e164baa393e0f71d35ec4a2f1ef1fb?"
          className="shrink-0 self-start w-7 aspect-square"
        />
      </div>
      <div className="flex flex-col px-14 mt-10 w-full text-xl text-zinc-500 max-md:px-5 max-md:mt-10 max-md:max-w-full">
        <div className="justify-center items-start p-5 rounded-sm border border-solid border-neutral-300 max-md:px-5 max-md:max-w-full">
          Email address
        </div>
        <div className="justify-center items-start p-5 mt-10 whitespace-nowrap rounded-sm border border-solid border-neutral-300 max-md:px-5 max-md:mt-10 max-md:max-w-full">
          Password
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
    </div>
  );
}

export default Login;
