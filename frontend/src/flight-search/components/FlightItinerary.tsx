import {Badge} from "@/components/ui/badge";
import {CalendarDays, MoveRight, Clock, Plane} from "lucide-react";
import type {Itinerary} from "../types/flights/itinerary.type";
import {formatDate, formatDuration} from "@/lib/utils";
import {Separator} from "@/components/ui/separator";

interface Props {
  itinerary: Itinerary;
  showSeparator?: boolean;
}

export function FlightItinerary({showSeparator = false, itinerary}: Props) {
  const {segments} = itinerary;
  const numberOfStops = segments.reduce(
    (acc, current) => acc + current.numberOfStops,
    0,
  );
  const stops = segments.flatMap((segment) => segment.stops);
  const carrierCode = itinerary.segments[0].carrierCode;
  const operatingCarrierCode =
    itinerary.segments[0].operatingCarrierCode ?? carrierCode;

  return (
    <div>
      <div className="space-y-2">
        {/* Itinerary Title */}
        <div className="text-primary flex items-center gap-2 text-xl font-semibold">
          <p>{itinerary.segments[0].departure.iataCode}</p>
          <MoveRight className="size-6" />
          <p>{itinerary.segments.at(-1)?.arrival.iataCode}</p>
        </div>

        {/* Itinerary dates */}
        <div className="text-secondary flex items-center gap-2 text-sm">
          <CalendarDays className="size-4" />{" "}
          <span>{formatDate(itinerary.initialDepartureDate)}</span> -
          <span>{formatDate(itinerary.finalArrivalDate)}</span>
        </div>

        {/* Itineraty Duration */}
        <div className="grid grid-cols-12 grid-rows-2 gap-2">
          <div className="col-span-8">
            <Badge variant="outline">
              <Clock />
              <span>{formatDuration(itinerary.duration)}</span>
              <span>·</span>
              <span>
                {numberOfStops === 0 ? "Nonstop" : `${numberOfStops} stops`}
              </span>
            </Badge>
          </div>

          {stops.map(
            (stop) =>
              stop && (
                <div className="col-span-4 row-span-2 text-sm">
                  <p className="text-muted-foreground">
                    {formatDuration(stop.duration)} in {stop.iataCode}
                  </p>
                </div>
              ),
          )}
        </div>

        {/* Itinerary Airline */}
        <div className="text-muted-foreground flex items-center gap-1 text-sm">
          <Plane className="size-4" /> <span>{carrierCode}</span>
          {carrierCode !== operatingCarrierCode && (
            <span>· Operated by {operatingCarrierCode}</span>
          )}
        </div>
      </div>
      {showSeparator && <Separator className="my-6" />}
    </div>
  );
}
