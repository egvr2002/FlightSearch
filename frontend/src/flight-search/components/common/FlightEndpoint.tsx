import {Address} from "./Address";
import {formatDate} from "@/lib/utils";

interface Props {
  departureIataCode: string;
  arrivalIataCode: string;
  departureDateTime: string;
  arrivalDateTime: string;
}

export function FlightEndpoint({
  departureIataCode,
  arrivalIataCode,
  departureDateTime,
  arrivalDateTime,
}: Props) {
  return (
    <div className="grid grid-cols-2 gap-4 text-sm">
      <div>
        <div className="text-muted-foreground">Departure</div>
        <div className="font-medium">{formatDate(departureDateTime)}</div>
        <div className="flex items-center gap-1">
          <Address iataCode={departureIataCode} />
          <span className="text-muted-foreground">({departureIataCode})</span>
        </div>
      </div>

      <div>
        <div className="text-muted-foreground">Arrival</div>
        <div className="font-medium">{formatDate(arrivalDateTime)}</div>
        <div className="flex items-center gap-1">
          <Address iataCode={arrivalIataCode} />
          <span className="text-muted-foreground">({arrivalIataCode})</span>
        </div>
      </div>
    </div>
  );
}
