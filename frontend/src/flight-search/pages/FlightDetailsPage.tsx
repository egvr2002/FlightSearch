import {GoBackToResultsButton} from "../components/GoBackToResultsButton";
import {FlightItinerarySection} from "../components/FlightItinerarySection";
import {PricingBreakdown} from "../components/PricingBreakdown";
import {useFlightOfferDetails} from "../hooks/useFlightOfferDetails";
import {Navigate} from "react-router";

export function FlightDetailsPage() {
  const {flightOffer, isFound} = useFlightOfferDetails();

  if (!isFound || !flightOffer) {
    return (
      <Navigate
        to="/"
        replace
      />
    );
  }

  return (
    <div className="space-y-4">
      <div className="flex items-center justify-between gap-2">
        <GoBackToResultsButton />
      </div>

      <div className="grid grid-cols-12 gap-x-4 gap-y-6">
        <div className="col-span-8 space-y-6">
          {flightOffer.itineraries.map((itinerary) => (
            <FlightItinerarySection
              key={itinerary.itineraryId}
              itinerary={itinerary}
              flightOffer={flightOffer}
            />
          ))}
        </div>

        <div className="col-span-4">
          <PricingBreakdown flightOffer={flightOffer} />
        </div>
      </div>
    </div>
  );
}
