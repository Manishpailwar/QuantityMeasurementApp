import type { ReactNode } from "react";
import { BrowserRouter, Navigate, Route, Routes } from "react-router-dom";
import { measurementCards } from "./component/cards/MeasurementCard";
import { CategoryPanel } from "./component/dashboard/CategoryPanel";
import { useAuth } from "./context/AuthContext";
import DashboardPage from "./pages/DashboardPage";
import HistoryPage from "./pages/HistoryPage";
import WelcomePage from "./pages/WelcomePage";
import { APP_ROUTES, DASHBOARD_ROUTES } from "./routes";

function ProtectedRoute({ children }: { children: ReactNode }) {
  const { isAuthenticated } = useAuth();

  if (!isAuthenticated) {
    return <Navigate to={APP_ROUTES.home} replace />;
  }

  return children;
}

function AppRouter() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path={APP_ROUTES.home} element={<WelcomePage />} />
        <Route
          path={APP_ROUTES.dashboard}
          element={
            // <ProtectedRoute>
              <DashboardPage />
            // </ProtectedRoute>
          }
        >
          <Route index element={<Navigate to={DASHBOARD_ROUTES.length} replace />} />
          <Route path={DASHBOARD_ROUTES.history} element={<HistoryPage />} />
          {measurementCards.map((card) => (
            <Route key={card.route}>
              <Route path={card.route} element={<Navigate to={card.operations[0]} replace />} />
              <Route
                path={`${card.route}/:operation`}
                element={
                  <CategoryPanel
                    categoryRoute={card.route}
                    title={card.name}
                    description={card.description}
                    quantities={card.quantities}
                    operations={card.operations}
                  />
                }
              />
            </Route>
          ))}
        </Route>
        <Route path="*" element={<Navigate to={APP_ROUTES.home} replace />} />
      </Routes>
    </BrowserRouter>
  );
}

export default AppRouter;