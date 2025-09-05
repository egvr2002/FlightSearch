import {Card, CardContent} from "@/components/ui/card";
import {ItineraryHeader} from "../common/ItineraryHeader";
import {ItineraryDuration} from "../common/ItineraryDuration";
import {useItineraryData} from "../../hooks/useItineraryData";
import type {Itinerary} from "../../types/flights/itinerary.type";

interface Props {
  itinerary: Itinerary;
}

export function ItineraryOverview({itinerary}: Props) {
  const {
    departureIataCode,
    arrivalIataCode,
    initialDepartureDate,
    finalArrivalDate,
    totalDuration,
    numberOfStops,
    stops,
  } = useItineraryData(itinerary);

  return (
    <Card>
      <CardContent>
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
        </div>
      </CardContent>
    </Card>
  );
}
