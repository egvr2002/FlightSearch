import {ItineraryOverview} from "./ItineraryOverview";
import {SegmentDetails} from "./SegmentDetails";
import type {Itinerary} from "../types/flights/itinerary.type";
import type {FlightOffer} from "../types/flights/flight-offer.type";

interface Props {
  itinerary: Itinerary;
  flightOffer: FlightOffer;
}

export function FlightItinerarySection({itinerary, flightOffer}: Props) {
  return (
    <div className="space-y-6">
      <ItineraryOverview itinerary={itinerary} />

      {/* Segments */}
      {itinerary.segments.map((segment, segmentIndex) => (
        <SegmentDetails
          key={segment.segmentId}
          segment={segment}
          segmentIndex={segmentIndex}
          flightOffer={flightOffer}
          isLastSegment={segmentIndex === itinerary.segments.length - 1}
        />
      ))}
    </div>
  );
}
