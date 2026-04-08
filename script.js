const tabButtons = document.querySelectorAll(".tab-button");
const authForms = document.querySelectorAll(".auth-form");

tabButtons.forEach((button) => {
    button.addEventListener("click", () => {
        const targetFormId = button.dataset.target;

        tabButtons.forEach((item) => item.classList.remove("is-active"));
        button.classList.add("is-active");

        authForms.forEach((form) => {
            form.classList.toggle("hidden", form.id !== targetFormId);
        });
    });
});

const toggleButtons = document.querySelectorAll("[data-password-toggle]");

toggleButtons.forEach((toggleButton) => {
    toggleButton.addEventListener("click", () => {
        const inputId = toggleButton.getAttribute("data-password-toggle");
        const passwordInput = inputId ? document.getElementById(inputId) : null;
        const openEyeIcon = toggleButton.querySelector(".eye-open");
        const closedEyeIcon = toggleButton.querySelector(".eye-closed");

        if (!passwordInput || !openEyeIcon || !closedEyeIcon) {
            return;
        }

        const isHidden = passwordInput.type === "password";
        passwordInput.type = isHidden ? "text" : "password";
        openEyeIcon.classList.toggle("hidden", isHidden);
        closedEyeIcon.classList.toggle("hidden", !isHidden);
        toggleButton.setAttribute("aria-label", isHidden ? "Hide password" : "Show password");
    });
});