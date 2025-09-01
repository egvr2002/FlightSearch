import {Outlet} from "react-router";

export function MainLayout() {
  return (
    <main className="bg-background flex min-h-svh flex-col items-center justify-center gap-6 p-6 md:p-10">
      <div className="w-full max-w-sm">
        <Outlet />
      </div>
    </main>
  );
}
