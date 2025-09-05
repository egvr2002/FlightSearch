import {Separator} from "@/components/ui/separator";

import {ItineraryHeader} from "./ItineraryHeader";
import {ItineraryDuration} from "./ItineraryDuration";
import {ItineraryAirline} from "./ItineraryAirline";
import {useItineraryData} from "../hooks/useItineraryData";

import type {Itinerary} from "../types/flights/itinerary.type";

interface Props {
  itinerary: Itinerary;
  showSeparator?: boolean;
}

export function FlightItinerary({showSeparator = false, itinerary}: Props) {
  const {
    departureIataCode,
    arrivalIataCode,
    initialDepartureDate,
    finalArrivalDate,
    totalDuration,
    numberOfStops,
    stops,
    carrierCode,
    operatingCarrierCode,
  } = useItineraryData(itinerary);

  return (
    <div>
      <div className="space-y-4">
        <ItineraryHeader
          departureIataCode={departureIataCode}
          arrivalIataCode={arrivalIataCode}
          initialDepartureDate={initialDepartureDate}
          finalArrivalDate={finalArrivalDate}
        />

        <ItineraryDuration
          totalDuration={totalDuration}
          numberOfStops={numberOfStops}
          stops={stops}
        />

        <ItineraryAirline
          primaryCarrierCode={carrierCode}
          operatingCarrierCode={operatingCarrierCode}
        />
      </div>
      {showSeparator && <Separator className="my-6" />}
    </div>
  );
}
