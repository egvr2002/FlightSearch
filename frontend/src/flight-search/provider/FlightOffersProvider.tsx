import {useState} from "react";

import {FlightOffersContext} from "../context/FlightOffersContext";
import type {FlightOffer} from "../types/flights/flight-offer.type";

interface Props {
  children: React.ReactNode;
}

export function FlightOffersProvider({children}: Props) {
  const [offers, setOffers] = useState<FlightOffer[]>([]);

  const setFlightOffers = (flightOffers: FlightOffer[]) => {
    setOffers(flightOffers);
  };

  return (
    <FlightOffersContext value={{flightOffers: offers, setFlightOffers}}>
      {children}
    </FlightOffersContext>
  );
}
