
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../../context/AuthContext";
import { APP_ROUTES, DASHBOARD_ROUTES } from "../../routes";

export function NavBar() {
    const [isMenuOpen, setIsMenuOpen] = useState<boolean>(false);
    const navigate = useNavigate();
    const { username, logout } = useAuth();

    const handleLogout = () => {
        logout();
        setIsMenuOpen(false);
        navigate(APP_ROUTES.home);
    };

    const handleHistory = () => {
        setIsMenuOpen(false);
        navigate(`${APP_ROUTES.dashboard}/${DASHBOARD_ROUTES.history}`);
    };

    return (
        <header className="sticky top-0 z-30 border-b border-slate-700/60 bg-slate-950/70 px-4 py-3 backdrop-blur">
                <div className="flex items-center justify-between gap-3">
                    <div className="flex items-center gap-3">
                    <span className="inline-flex h-10 w-10 items-center justify-center rounded-xl bg-linear-to-br from-cyan-300 to-emerald-200 text-lg font-black text-slate-900">
                        Q
                    </span>
                    <div>
                        <h1 className="text-base font-semibold tracking-wide text-slate-100 sm:text-lg">
                            Quantity Measurement App
                        </h1>
                        <p className="text-xs text-slate-300">Dashboard</p>
                    </div>
                    </div>

                    <div className="relative flex items-center gap-3">
                        <p className="text-sm font-medium text-slate-100">
                            Welcome, <span className="font-semibold text-cyan-200">{username ?? "User"}</span>
                        </p>
                        <button
                            type="button"
                            onClick={() => setIsMenuOpen((prev) => !prev)}
                            className="rounded-lg border border-slate-600 bg-slate-900/80 px-3 py-1.5 text-sm font-semibold text-slate-100 transition hover:bg-slate-800"
                        >
                            Menu
                        </button>

                        {isMenuOpen && (
                            <div className="absolute right-0 top-11 w-40 rounded-lg border border-slate-700 bg-slate-900 p-1.5 shadow-xl">
                                <button
                                    type="button"
                                    onClick={handleHistory}
                                    className="w-full rounded-md px-3 py-2 text-left text-sm font-medium text-slate-100 transition hover:bg-slate-800"
                                >
                                    History
                                </button>
                                <button
                                    type="button"
                                    onClick={handleLogout}
                                    className="w-full rounded-md px-3 py-2 text-left text-sm font-medium text-rose-300 transition hover:bg-slate-800"
                                >
                                    Logout
                                </button>
                            </div>
                        )}
                    </div>

            </div>
        </header>
    );
}