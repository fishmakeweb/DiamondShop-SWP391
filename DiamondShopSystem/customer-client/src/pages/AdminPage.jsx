import React, { useState } from "react";
import NavbarStaff from "../components/NavbarStaff";
import AdminSideNav from "./AdminNavbar";
import Content from "./AdminContent";
function AdminPage() {
  const [isSidebarOpen, setIsSidebarOpen] = useState(true);

  const toggleSidebar = () => {
    setIsSidebarOpen(!isSidebarOpen);
  };
  return (
    <>
      {/* component */}
      <meta charSet="UTF-8" />
      <meta name="viewport" content="width=device-width, initial-scale=1.0" />
      <meta httpEquiv="X-UA-Compatible" content="ie=edge" />
      <link rel="preconnect" href="https://fonts.bunny.net" />
      <link
        href="https://fonts.bunny.net/css?family=figtree:400,500,600&display=swap"
        rel="stylesheet"
      />
      <link
        href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css"
        rel="stylesheet"
      />
      <link
        href="https://cdn.jsdelivr.net/npm/remixicon@3.5.0/fonts/remixicon.css"
        rel="stylesheet"
      />
      <title>SALESTAFF</title>

      {/*sidenav */}
      <AdminSideNav isOpen={isSidebarOpen} />
      <main
        className={`main ${
          isSidebarOpen ? "md:ml-64" : "md:ml-0"
        } transition-all`}
      >
        <NavbarStaff onToggleSidebar={toggleSidebar} />
        {/* end navbar */}
        {/* Content */}
        <Content />
        {/* End Content */}
      </main>
    </>
  );
}

export default AdminPage;
