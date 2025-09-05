import {Plane} from "lucide-react";
import {Airline} from "./Airline";

interface Props {
  primaryCarrierCode: string;
  operatingCarrierCode: string;
}

export function ItineraryAirline({
  primaryCarrierCode,
  operatingCarrierCode,
}: Props) {
  return (
    <div className="space-y-1">
      <div className="flex items-center gap-2 text-sm">
        <Plane className="text-muted-foreground size-4" />
        <div className="flex items-center gap-1">
          <Airline iataCode={primaryCarrierCode} />
          <span className="text-muted-foreground">({primaryCarrierCode})</span>
        </div>
      </div>

      {primaryCarrierCode !== operatingCarrierCode && (
        <div className="text-muted-foreground ml-6 flex items-center gap-1 text-xs">
          <span>Operated by</span>
          <Airline iataCode={operatingCarrierCode} />
          <span>({operatingCarrierCode})</span>
        </div>
      )}
    </div>
  );
}
