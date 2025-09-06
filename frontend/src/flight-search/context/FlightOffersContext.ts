import {createContext} from "react";
import type {FlightOffer} from "../types/flights/flight-offer.type";

type FlightOffersContextType = {
  flightOffers: FlightOffer[];
  setFlightOffers: (flightOffers: FlightOffer[]) => void;
};

export const FlightOffersContext =
  createContext<FlightOffersContextType | null>(null);
