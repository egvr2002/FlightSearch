import {Button} from "@/components/ui/button";
import {Card, CardContent, CardFooter} from "@/components/ui/card";
import {FlightItinerary} from "./FlightItinerary";
import {PricingDisplay} from "./PricingDisplay";

import {ChevronRight} from "lucide-react";
import type {FlightOffer} from "../types/flights/flight-offer.type";
import {Link} from "react-router";

interface Props {
  flightOffer: FlightOffer;
}

export function FlightOfferCard({flightOffer}: Props) {
  const {itineraries} = flightOffer;

  return (
    <Card>
      <CardContent>
        <div className="grid grid-cols-12 gap-y-6">
          {/* Itineraries */}
          <div className="col-span-full space-y-6 md:col-span-8">
            {itineraries.map((itinerary, index) => (
              <FlightItinerary
                key={itinerary.itineraryId}
                itinerary={itinerary}
                showSeparator={index !== itineraries.length - 1}
              />
            ))}
          </div>

          {/* Pricing */}
          <div className="col-span-full space-y-4 self-center md:col-span-4">
            <PricingDisplay
              grandTotalPrice={flightOffer.grandTotalPrice}
              perTravelerPrice={flightOffer.travelerPricings[0].totalPrice}
              currency={flightOffer.currency}
              alignment="right"
            />
          </div>
        </div>
      </CardContent>

      <CardFooter>
        <Button
          className="w-full md:ml-auto md:w-fit"
          asChild
        >
          <Link to={`/flight-offers/${flightOffer.flightOfferId}`}>
            View details
            <ChevronRight />
          </Link>
        </Button>
      </CardFooter>
    </Card>
  );
}
