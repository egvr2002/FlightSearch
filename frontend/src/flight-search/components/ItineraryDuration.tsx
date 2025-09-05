import {Badge} from "@/components/ui/badge";
import {Clock} from "lucide-react";
import {Address} from "./Address";
import {formatDuration} from "@/lib/utils";
import type {FlightStop} from "../types/flights/flight-stop.type";

interface Props {
  totalDuration: string;
  numberOfStops: number;
  stops: FlightStop[];
}

export function ItineraryDuration({
  totalDuration,
  numberOfStops,
  stops,
}: Props) {
  return (
    <div className="grid grid-cols-12 grid-rows-2 gap-2">
      <div className="col-span-8">
        <Badge variant="outline">
          <Clock />
          <span>{formatDuration(totalDuration)}</span>
          <span>·</span>
          <span>
            {numberOfStops === 0 ? "Nonstop" : `${numberOfStops} stops`}
          </span>
        </Badge>
      </div>

      {stops.map((stop) => (
        <div
          key={stop.iataCode}
          className="col-span-4 row-span-2 text-sm"
        >
          <p className="text-muted-foreground">
            <span>{formatDuration(stop.duration)} stop in </span>
            <Address iataCode={stop.iataCode} />
            <span> ({stop.iataCode})</span>
          </p>
        </div>
      ))}
    </div>
  );
}
