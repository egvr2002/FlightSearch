import {Outlet} from "react-router";
import {AppTitle} from "@/components/shared/components/AppTitle";

export function MainLayout() {
  return (
    <main className="bg-background h-dvh min-h-dvh gap-6">
      <div className="mx-auto flex w-full flex-col space-y-8 overflow-hidden p-6 lg:max-w-8/12">
        <AppTitle />
        <Outlet />
      </div>
    </main>
  );
}
