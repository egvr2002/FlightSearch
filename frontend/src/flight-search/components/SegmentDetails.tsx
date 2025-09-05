import {Card, CardContent} from "@/components/ui/card";
import {Separator} from "@/components/ui/separator";
import {FlightEndpoint} from "./FlightEndpoint";
import {SegmentAirlineInfo} from "./SegmentAirlineInfo";
import {TravelerFareDetails} from "./TravelerFareDetails";
import type {Segment} from "../types/flights/segment.type";
import type {FlightOffer} from "../types/flights/flight-offer.type";

interface Props {
  segment: Segment;
  segmentIndex: number;
  flightOffer: FlightOffer;
  isLastSegment: boolean;
}

export function SegmentDetails({
  segment,
  segmentIndex,
  flightOffer,
  isLastSegment,
}: Props) {
  return (
    <Card>
      <CardContent className="space-y-4">
        {/* Segment Number */}
        <div className="text-lg font-semibold">Segment {segmentIndex + 1}</div>

        <SegmentAirlineInfo
          carrierCode={segment.carrierCode}
          operatingCarrierCode={segment.operatingCarrierCode}
          flightNumber={segment.number}
          aircraftCode={segment.aircraftCode}
          showFlightDetails={true}
        />

        <FlightEndpoint
          departureIataCode={segment.departure.iataCode}
          arrivalIataCode={segment.arrival.iataCode}
          departureDateTime={segment.departure.at}
          arrivalDateTime={segment.arrival.at}
        />

        <TravelerFareDetails
          segmentId={segment.segmentId}
          travelersPricings={flightOffer.travelerPricings}
        />

        {/* Layover */}
        {!isLastSegment && (
          <>
            <Separator className="my-4" />
            <div className="text-muted-foreground text-center text-sm">
              {/* TODO: Calculate layover time between segments */}
              Layover calculation needed
            </div>
          </>
        )}
      </CardContent>
    </Card>
  );
}
