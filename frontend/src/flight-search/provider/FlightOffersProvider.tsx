import {useState, useMemo} from "react";

import {FlightOffersContext} from "../context/FlightOffersContext";
import type {FlightOffer} from "../types/flights/flight-offer.type";
import type {SortConfig} from "../types/sorting/sort-config.type";
import {sortFlightOffers} from "../services/sort.service";

interface Props {
  children: React.ReactNode;
}

export function FlightOffersProvider({children}: Props) {
  const [offers, setOffers] = useState<FlightOffer[]>([]);
  const [sortConfig, setSortConfig] = useState<SortConfig>([]);

  const setFlightOffers = (flightOffers: FlightOffer[]) => {
    setOffers(flightOffers);
  };

  const sortedFlightOffers = useMemo(() => {
    return sortFlightOffers(offers, sortConfig);
  }, [offers, sortConfig]);

  return (
    <FlightOffersContext
      value={{
        flightOffers: offers,
        setFlightOffers,
        sortConfig,
        setSortConfig,
        sortedFlightOffers,
      }}
    >
      {children}
    </FlightOffersContext>
  );
}
