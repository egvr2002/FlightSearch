import {Card, CardContent} from "@/components/ui/card";
import {Separator} from "@/components/ui/separator";
import {FlightEndpoint} from "../shared/FlightEndpoint";
import {SegmentAirlineInfo} from "../shared/SegmentAirlineInfo";
import {TravelerFareDetails} from "./TravelerFareDetails";
import type {Segment} from "../../types/flights/segment.type";
import type {FlightOffer} from "../../types/flights/flight-offer.type";
import {Badge} from "@/components/ui/badge";
import {formatDuration} from "@/lib/utils";
import {Clock} from "lucide-react";

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
            <div className="flex items-center justify-center gap-2 py-2">
              <div className="flex items-center gap-2">
                <Badge
                  variant="outline"
                  className="border-orange-200 bg-orange-50 text-orange-700"
                >
                  <Clock />
                  <span>
                    Layover: {formatDuration(segment.layoverDuration)}
                  </span>
                </Badge>
              </div>
            </div>
          </>
        )}
      </CardContent>
    </Card>
  );
}
