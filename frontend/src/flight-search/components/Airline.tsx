import {Suspense, use} from "react";
import {getAirlineInformation} from "../services/flight-search.service";
import type {Airline} from "../types/common/airline-information.type";

interface CityNameProps {
  iataCode: string;
}

export function Airline({iataCode}: CityNameProps) {
  const airlinePromise = getAirlineInformation(iataCode);

  return (
    <Suspense
      fallback={
        <span className="text-muted-foreground">Loading airline...</span>
      }
    >
      <AirlineContent airlinePromise={airlinePromise} />
    </Suspense>
  );
}

interface AddressContent {
  airlinePromise: Promise<Airline>;
}

function AirlineContent({airlinePromise}: AddressContent) {
  const address = use(airlinePromise);
  return <span className="capitalize">{address.name.toLowerCase()}</span>;
}
