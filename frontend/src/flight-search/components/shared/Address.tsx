import {Suspense, use} from "react";

import {getAddressInformation} from "@/flight-search/services/flight-search.service";
import type {Address} from "@/flight-search/types/common/address.type";
import {ErrorBoundary} from "../../../components/shared/ErrorBoundary";

interface CityNameProps {
  iataCode: string;
}

export function Address({iataCode}: CityNameProps) {
  const addressPromise = getAddressInformation(iataCode);

  return (
    <ErrorBoundary
      fallback={<span className="text-muted-foreground">City unavailable</span>}
    >
      <Suspense
        fallback={
          <span className="text-muted-foreground">Loading city...</span>
        }
      >
        <CityNameContent addressPromise={addressPromise} />
      </Suspense>
    </ErrorBoundary>
  );
}

interface AddressContent {
  addressPromise: Promise<Address>;
}

function CityNameContent({addressPromise}: AddressContent) {
  const address = use(addressPromise);
  return <span className="capitalize">{address.cityName.toLowerCase()}</span>;
}
