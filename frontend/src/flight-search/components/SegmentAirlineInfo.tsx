import {Plane} from "lucide-react";
import {Airline} from "./Airline";

interface Props {
  carrierCode: string;
  operatingCarrierCode?: string;
  flightNumber?: string;
  aircraftCode?: string;
  showFlightDetails?: boolean;
}

export function SegmentAirlineInfo({
  carrierCode,
  operatingCarrierCode,
  flightNumber,
  aircraftCode,
  showFlightDetails = false,
}: Props) {
  return (
    <div className="space-y-1">
      <div className="flex items-center gap-2 text-sm">
        <Plane className="text-muted-foreground size-4" />
        <div className="flex items-center gap-1">
          <Airline iataCode={carrierCode} />
          <span className="text-muted-foreground">({carrierCode})</span>
          {showFlightDetails && flightNumber && (
            <>
              <span className="text-muted-foreground">·</span>
              <span>Flight {flightNumber}</span>
            </>
          )}
          {showFlightDetails && aircraftCode && (
            <>
              <span className="text-muted-foreground">·</span>
              <span>{aircraftCode}</span>
            </>
          )}
        </div>
      </div>

      {operatingCarrierCode && operatingCarrierCode !== carrierCode && (
        <div className="text-muted-foreground ml-6 flex items-center gap-1 text-xs">
          <span>Operated by</span>
          <Airline iataCode={operatingCarrierCode} />
          <span>({operatingCarrierCode})</span>
        </div>
      )}
    </div>
  );
}
