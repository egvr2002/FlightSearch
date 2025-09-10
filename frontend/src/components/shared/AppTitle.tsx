import {Link} from "react-router";
import {PlaneTakeoff} from "lucide-react";

export function AppTitle() {
  return (
    <div className="flex flex-col gap-6">
      <div className="flex flex-col items-center gap-2">
        <Link
          to="/"
          className="flex flex-col items-center gap-2 font-medium"
        >
          <div className="bg-secondary text-accent flex size-16 items-center justify-center rounded-sm">
            <PlaneTakeoff
              data-testid="plane-takeoff-icon"
              className="size-12"
            />
          </div>
          <span className="sr-only">FlightSearch</span>
        </Link>
        <h1 className="text-2xl font-bold">
          Flight<span className="text-primary">Search</span>
        </h1>
      </div>
    </div>
  );
}
