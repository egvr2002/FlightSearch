import {use, useEffect} from "react";
import type {FlightOffer} from "../types/flights/flight-offer.type";
import {FlightOfferCard} from "./FlightOfferCard";
import {useFlightOffersContext} from "../hooks/useFlightOffersContext";

interface Props {
  flightOffersPromise: Promise<FlightOffer[]>;
}

export function FlightOffers({flightOffersPromise}: Props) {
  const flightOffers = use(flightOffersPromise);
  const {setFlightOffers} = useFlightOffersContext();

  useEffect(() => {
    setFlightOffers(flightOffers);
  }, [flightOffers, setFlightOffers]);

  return flightOffers.map((flightOffer) => (
    <FlightOfferCard
      key={flightOffer.flightOfferId}
      flightOffer={flightOffer}
    />
  ));
}
