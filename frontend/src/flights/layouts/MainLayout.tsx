import {Outlet} from "react-router";

export function MainLayout() {
  return (
    <main className="bg-primary-foreground flex min-h-screen items-center justify-center">
      <div className="">
        <Outlet />
      </div>
    </main>
  );
}
