import {CalendarDays, MoveRight} from "lucide-react";
import {Address} from "./Address";
import {formatDate} from "@/lib/utils";

interface Props {
  departureIataCode: string;
  arrivalIataCode: string;
  initialDepartureDate: string;
  finalArrivalDate: string;
}

export function ItineraryHeader({
  departureIataCode,
  arrivalIataCode,
  initialDepartureDate,
  finalArrivalDate,
}: Props) {
  return (
    <div className="space-y-2">
      {/* Itinerary dates */}
      <div className="text-secondary flex items-center gap-2 text-sm">
        <CalendarDays className="size-4" />{" "}
        <span>{formatDate(initialDepartureDate)}</span> -
        <span>{formatDate(finalArrivalDate)}</span>
      </div>

      {/* Itinerary Title */}
      <div className="text-primary flex items-center gap-2 text-xl font-semibold">
        <Address iataCode={departureIataCode} />
        <span>({departureIataCode})</span>
        <MoveRight className="size-6" />
        <Address iataCode={arrivalIataCode} />
        <span>({arrivalIataCode})</span>
      </div>
    </div>
  );
}
