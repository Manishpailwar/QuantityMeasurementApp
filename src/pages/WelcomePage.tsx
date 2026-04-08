import { useState } from "react";
import LoginForm from "../component/auth/LoginForm";
import SignupForm from "../component/auth/SignupForm";
import type { WelcomeTabModel } from "../types/models/WelcomeTabModel";

function WelcomePage() {
  const [activeTab, setActiveTab] = useState<WelcomeTabModel>("login");

  return (
    <main className="grid min-h-screen place-items-center bg-[radial-gradient(circle_at_20%_15%,#fde68a_0,rgba(253,230,138,0.25)_26%,transparent_50%),radial-gradient(circle_at_80%_85%,#99f6e4_0,rgba(153,246,228,0.22)_28%,transparent_54%),linear-gradient(120deg,#0f172a_0%,#111827_40%,#1f2937_100%)] p-6 text-slate-200">
      <section className="grid w-full max-w-270 overflow-hidden rounded-3xl border border-slate-400/30 bg-slate-900/55 shadow-[0_30px_80px_rgba(2,6,23,0.45)] backdrop-blur md:grid-cols-[1.2fr_1fr]">
        <div className="flex items-center justify-center border-b border-slate-400/20 p-8 sm:p-10 md:border-b-0 md:border-r md:border-slate-400/20 md:p-12">
          <div className="w-full max-w-md space-y-3 text-center">
            <span className="inline-flex items-center  px-3 py-1 text-4xl font-semibold uppercase tracking-[0.12em] text-cyan-100">
              Quantity Measurement App
            </span>
            <p className="mx-auto max-w-md text-sm leading-6 text-slate-300">
              Track and convert measurements quickly with a clean and reliable workflow.
            </p>
          </div>
        </div>

        <div className="flex flex-col gap-4 p-6 sm:p-8 lg:p-9">
          <div className="mt-1 flex gap-2 rounded-xl border border-slate-400/25 bg-slate-900/70 p-1.5">
            <button
              type="button"
              className={`flex-1 rounded-lg px-3 py-2.5 text-sm font-bold transition ${
                activeTab === "login"
                  ? "bg-linear-to-br from-cyan-300 to-emerald-200 text-slate-900"
                  : "text-slate-300 hover:bg-slate-400/20 hover:text-slate-50"
              }`}
              onClick={() => setActiveTab("login")}
            >
              Login
            </button>
            <button
              type="button"
              className={`flex-1 rounded-lg px-3 py-2.5 text-sm font-bold transition ${
                activeTab === "signup"
                  ? "bg-linear-to-br from-cyan-300 to-emerald-200 text-slate-900"
                  : "text-slate-300 hover:bg-slate-400/20 hover:text-slate-50"
              }`}
              onClick={() => setActiveTab("signup")}
            >
              Sign up
            </button>
          </div>

          <div className="mt-1 flex h-110 justify-center overflow-hidden">
            <div className="w-full max-w-md overflow-y-auto pr-1">
              {activeTab === "login" ? <LoginForm /> : <SignupForm />}
            </div>
          </div>
        </div>
      </section>
    </main>
  );
}

export default WelcomePage;