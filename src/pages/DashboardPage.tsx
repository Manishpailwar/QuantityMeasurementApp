
import { NavBar } from "../component/dashboard/NavBar";
import { SideBar } from "../component/dashboard/SideBar";
import { Outlet } from "react-router-dom";

function DashboardPage() {
  return (
    <main className="min-h-screen bg-[radial-gradient(circle_at_20%_15%,#fde68a_0,rgba(253,230,138,0.18)_24%,transparent_50%),radial-gradient(circle_at_80%_85%,#99f6e4_0,rgba(153,246,228,0.16)_26%,transparent_54%),linear-gradient(120deg,#0f172a_0%,#111827_40%,#1f2937_100%)]">
      <NavBar />

      <section className="mx-auto grid w-full max-w-7xl gap-5 px-4 py-6 sm:px-6 lg:grid-cols-[300px_1fr] lg:px-8">
        <SideBar />
        <Outlet />
      </section>
    </main>
  );
}

export default DashboardPage;