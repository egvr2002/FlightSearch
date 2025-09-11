import {use, useEffect} from "react";
import type {FlightOffer} from "../../types/flights/flight-offer.type";
import {FlightOfferCard} from "./FlightOfferCard";
import {useFlightOffersContext} from "../../hooks/useFlightOffersContext";
import {EmpyFlightsResult} from "@/components/shared/EmpyFlightsResult";
import {TicketsPlane} from "lucide-react";

interface Props {
  flightOffersPromise: Promise<FlightOffer[]>;
}

export function FlightOffers({flightOffersPromise}: Props) {
  const flightOffers = use(flightOffersPromise);
  const {setFlightOffers, sortedFlightOffers} = useFlightOffersContext();

  useEffect(() => {
    setFlightOffers(flightOffers);
  }, [flightOffers, setFlightOffers]);

  if (flightOffers.length === 0) {
    return (
      <EmpyFlightsResult
        title="No Results"
        description="Try adjusting your search criteria to find what you're looking for."
        Icon={TicketsPlane}
      />
    );
  }

  return sortedFlightOffers.map((flightOffer) => (
    <FlightOfferCard
      key={flightOffer.flightOfferId}
      flightOffer={flightOffer}
    />
  ));
}
