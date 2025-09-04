import {Badge} from "@/components/ui/badge";
import {CalendarDays, MoveRight, Clock, Plane} from "lucide-react";

export function FlightItinerary() {
  return (
    <div>
      <div className="space-y-2">
        {/* Itinerary dates */}
        <div className="text-secondary flex items-center gap-2 text-sm">
          <CalendarDays className="size-4" />{" "}
          <span>{"10 Oct, 15 at 15:30pm"}</span> -
          <span>{"10 Oct, 15 at 15:30pm"}</span>
        </div>

        {/* Itinerary Title */}
        <div className="text-primary flex items-center gap-2 text-xl font-semibold">
          <p>{"Mexico City"}</p>
          <MoveRight className="size-6" />
          <p>{"San Francisco"}</p>
        </div>

        {/* Itineraty Duration */}
        <div className="grid grid-cols-12 grid-rows-2 gap-2">
          <div className="col-span-8">
            <Badge variant="outline">
              <Clock />
              {"12h 40m"}
            </Badge>
          </div>
          <div className="col-span-4 row-span-2 text-sm">
            <p className="text-muted-foreground">{"0 stops"}</p>
            <p className="text-muted-foreground">
              {"1h 20m in Los Angeles (LAX)"}
            </p>
          </div>
        </div>

        {/* Itinerary Airline */}
        <div className="text-muted-foreground flex items-center gap-1 text-sm">
          <Plane className="size-4" /> <span>{"Aeromexico (AM)"}</span>
          <span>· Operated by {"Aeromexico (AM)"}</span>
        </div>
      </div>
    </div>
  );
}
