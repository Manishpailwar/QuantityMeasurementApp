import { useState, type ChangeEvent } from "react";
import { useNavigate } from "react-router-dom";
import { signup } from "../../apis/authApi";
import { useAuth } from "../../context/AuthContext";
import { APP_ROUTES } from "../../routes";
import { EyeIcon, EyeSlashIcon } from "./VisibilityIcons";

function SignupForm() {
    const navigate = useNavigate();
    const { authenticateWithToken } = useAuth();
    const [name, setName] = useState<string>("");
    const [email, setEmail] = useState<string>("");
    const [password, setPassword] = useState<string>("");
    const [confirmPassword, setConfirmPassword] = useState<string>("");
    const [showPassword, setShowPassword] = useState<boolean>(false);
    const [showConfirmPassword, setShowConfirmPassword] = useState<boolean>(false);
    const [isSubmitting, setIsSubmitting] = useState<boolean>(false);
    const [errorMessage, setErrorMessage] = useState<string>("");

    function handleNameChange(event: ChangeEvent<HTMLInputElement>) {
        setName(event.target.value);
    }

    function handleEmailChange(event: ChangeEvent<HTMLInputElement>) {
        setEmail(event.target.value);
    }

    function handlePasswordChange(event: ChangeEvent<HTMLInputElement>) {
        setPassword(event.target.value);
    }

    function handleConfirmPasswordChange(event: ChangeEvent<HTMLInputElement>) {
        setConfirmPassword(event.target.value);
    }

    async function handleSignup() {
        setErrorMessage("");

        if (!name.trim() || !email.trim() || !password.trim() || !confirmPassword.trim()) {
            setErrorMessage("All fields are required.");
            return;
        }

        if (password !== confirmPassword) {
            setErrorMessage("Password and confirm password must match.");
            return;
        }

        setIsSubmitting(true);
        try {
            const tokenFromBackend = await signup({
                username: name,
                email,
                password,
            });
            authenticateWithToken(tokenFromBackend);
            navigate(APP_ROUTES.dashboard);
        } catch {
            setErrorMessage("Signup failed. Please try again.");
        } finally {
            setIsSubmitting(false);
        }
    }

    return (
        <div className="w-full max-w-md rounded-2xl border border-slate-200 bg-white p-6 shadow-xl sm:p-8">


            <div className="space-y-4">
                <div>
                    <label htmlFor="name" className="mb-1 block text-sm font-medium text-slate-700">
                        Name
                    </label>
                    <input
                        id="name"
                        type="text"
                        autoComplete="name"
                        value={name}
                        onChange={handleNameChange}
                        className="w-full rounded-lg border border-slate-300 bg-white px-3 py-2 text-slate-900 outline-none transition focus:border-cyan-500 focus:ring-2 focus:ring-cyan-200"
                        placeholder="Your name"
                    />
                </div>

                <div>
                    <label htmlFor="signup-email" className="mb-1 block text-sm font-medium text-slate-700">
                        Email
                    </label>
                    <input
                        id="signup-email"
                        type="email"
                        autoComplete="email"
                        value={email}
                        onChange={handleEmailChange}
                        className="w-full rounded-lg border border-slate-300 bg-white px-3 py-2 text-slate-900 outline-none transition focus:border-cyan-500 focus:ring-2 focus:ring-cyan-200"
                        placeholder="you@example.com"
                    />
                </div>

                <div>
                    <label htmlFor="signup-password" className="mb-1 block text-sm font-medium text-slate-700">
                        Password
                    </label>
                    <div className="relative">
                        <input
                            id="signup-password"
                            type={showPassword ? "text" : "password"}
                            autoComplete="new-password"
                            value={password}
                            onChange={handlePasswordChange}
                            className="w-full rounded-lg border border-slate-300 bg-white px-3 py-2 pr-20 text-slate-900 outline-none transition focus:border-cyan-500 focus:ring-2 focus:ring-cyan-200"
                            placeholder="Create password"
                        />
                        <button
                            type="button"
                            onClick={() => setShowPassword((prev) => !prev)}
                            className="absolute right-2 top-1/2 -translate-y-1/2 rounded p-2 text-cyan-700 hover:bg-cyan-50"
                            aria-label={showPassword ? "Hide password" : "Show password"}
                        >
                            {showPassword ? <EyeSlashIcon /> : <EyeIcon />}
                        </button>
                    </div>
                </div>

                <div>
                    <label htmlFor="confirm-password" className="mb-1 block text-sm font-medium text-slate-700">
                        Confirm password
                    </label>
                    <div className="relative">
                        <input
                            id="confirm-password"
                            type={showConfirmPassword ? "text" : "password"}
                            autoComplete="new-password"
                            value={confirmPassword}
                            onChange={handleConfirmPasswordChange}
                            className="w-full rounded-lg border border-slate-300 bg-white px-3 py-2 pr-20 text-slate-900 outline-none transition focus:border-cyan-500 focus:ring-2 focus:ring-cyan-200"
                            placeholder="Re-enter password"
                        />
                        <button
                            type="button"
                            onClick={() => setShowConfirmPassword((prev) => !prev)}
                            className="absolute right-2 top-1/2 -translate-y-1/2 rounded p-2 text-cyan-700 hover:bg-cyan-50"
                            aria-label={showConfirmPassword ? "Hide confirm password" : "Show confirm password"}
                        >
                            {showConfirmPassword ? <EyeSlashIcon /> : <EyeIcon />}
                        </button>
                    </div>
                </div>

                <button
                    type="button"
                    onClick={handleSignup}
                    disabled={isSubmitting}
                    className="w-full rounded-lg bg-cyan-600 px-4 py-2.5 font-semibold text-white transition hover:bg-cyan-700 disabled:cursor-not-allowed disabled:opacity-70"
                >
                    {isSubmitting ? "Creating account..." : "Sign up"}
                </button>

                {errorMessage && (
                    <p className="text-sm font-medium text-rose-600">{errorMessage}</p>
                )}
            </div>
        </div>
    );
}

export default SignupForm;
