import {Button} from "@/components/ui/button";
import {Card, CardContent, CardFooter} from "@/components/ui/card";
import {FlightItinerary} from "./FlightItinerary";

import {ChevronRight} from "lucide-react";
import type {FlightOffer} from "../types/flights/flight-offer.type";
import {formatCurrency} from "@/lib/utils";

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
            <div className="text-primary text-right font-semibold">
              <p>{formatCurrency(flightOffer.grandTotalPrice)}</p>
              <p className="text-sm">total</p>
            </div>

            <div className="text-secondary text-right">
              <p>
                {formatCurrency(flightOffer.travelerPricings[0].totalPrice)}
              </p>
              <p className="text-sm">per traveler</p>
            </div>
          </div>
        </div>
      </CardContent>

      <CardFooter>
        {/* Defailts Button */}
        <Button className="w-full md:ml-auto md:w-fit">
          View details
          <ChevronRight />
        </Button>
      </CardFooter>
    </Card>
  );
}
