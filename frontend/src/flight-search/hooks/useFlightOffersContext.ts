import {use} from "react";
import {FlightOffersContext} from "../context/FlightOffersContext";

export function useFlightOffersContext() {
  const flightOffersContext = use(FlightOffersContext);

  if (!flightOffersContext) {
    throw new Error(
      "useFlightOffersContext must be called with a FlightOffersContext",
    );
  }

  return flightOffersContext;
}
