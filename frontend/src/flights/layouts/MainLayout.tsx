import {Outlet} from "react-router";

export function MainLayout() {
  return (
    <main className="bg-muted flex min-h-screen items-center justify-center">
      <div className="w-full max-w-xl p-2">
        <Outlet />
      </div>
    </main>
  );
}
