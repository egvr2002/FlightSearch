import {AppTitle} from "@/shared/components/AppTitle";
import {Outlet} from "react-router";

export function MainLayout() {
  return (
    <main className="bg-background h-dvh min-h-dvh gap-6">
      <div className="mx-auto flex w-full flex-col space-y-8 overflow-hidden p-6 md:max-w-6/12">
        <AppTitle />
        <Outlet />
      </div>
    </main>
  );
}
