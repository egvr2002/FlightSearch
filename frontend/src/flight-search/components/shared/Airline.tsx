import {Suspense, use} from "react";

import {getAirlineInformation} from "@/flight-search/services/flight-search.service";
import type {Airline} from "@/flight-search/types/common/airline-information.type";
import {ErrorBoundary} from "../../../components/shared/ErrorBoundary";

interface CityNameProps {
  iataCode: string;
}

export function Airline({iataCode}: CityNameProps) {
  const airlinePromise = getAirlineInformation(iataCode);

  return (
    <ErrorBoundary
      fallback={
        <span className="text-muted-foreground">Airline unavailable</span>
      }
    >
      <Suspense
        fallback={
          <span className="text-muted-foreground">Loading airline...</span>
        }
      >
        <AirlineContent airlinePromise={airlinePromise} />
      </Suspense>
    </ErrorBoundary>
  );
}

interface AddressContent {
  airlinePromise: Promise<Airline>;
}

function AirlineContent({airlinePromise}: AddressContent) {
  const address = use(airlinePromise);
  return <span className="capitalize">{address.name.toLowerCase()}</span>;
}
