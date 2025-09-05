import {Suspense, use} from "react";
import {getAddressInformation} from "../services/flight-search.service";
import type {Address} from "../types/common/address.type";

interface CityNameProps {
  iataCode: string;
}

export function Address({iataCode}: CityNameProps) {
  const addressPromise = getAddressInformation(iataCode);

  return (
    <Suspense
      fallback={<span className="text-muted-foreground">Loading city...</span>}
    >
      <CityNameContent addressPromise={addressPromise} />
    </Suspense>
  );
}

interface AddressContent {
  addressPromise: Promise<Address>;
}

function CityNameContent({addressPromise}: AddressContent) {
  const address = use(addressPromise);
  return <span className="capitalize">{address.cityName.toLowerCase()}</span>;
}
