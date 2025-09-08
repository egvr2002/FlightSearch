import {createContext} from "react";
import type {FlightOffer} from "../types/flights/flight-offer.type";
import type {SortConfig} from "../types/sorting/sort-config.type";

type FlightOffersContextType = {
  flightOffers: FlightOffer[];
  setFlightOffers: (flightOffers: FlightOffer[]) => void;
  sortConfig: SortConfig;
  setSortConfig: (sortConfig: SortConfig) => void;
  sortedFlightOffers: FlightOffer[];
};

export const FlightOffersContext =
  createContext<FlightOffersContextType | null>(null);
