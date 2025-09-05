import {flightSearch} from "@/flight-search/api/flight-search.api";

import type {Address} from "../types/common/address.type";
import type {Airline} from "../types/common/airline-information.type";
import type {FlightOffer} from "../types/flights/flight-offer.type";
import type {FlightSearchPayload} from "../types/flights/flight-search-payload.type";
import type {Location} from "@/flight-search/types/locations/location.type";
import type {SearchLocationsPayload} from "@/flight-search/types/locations/search-location.type";

export async function searchLocations({
  keyword,
  pageLimit = 10,
  pageOffset = 0,
  subType = "AIRPORT",
  view = "LIGHT",
}: SearchLocationsPayload) {
  const locations = await flightSearch.get<Location[]>(
    "/search/locations/airport",
    {
      params: {
        keyword,
        pageLimit,
        pageOffset,
        subType,
        view,
      },
    },
  );

  return locations.data;
}

export async function getFlightOffers(
  flightSearchPayload: FlightSearchPayload,
) {
  const flightOffers = await flightSearch.post<FlightOffer[]>(
    "/search/flights",
    flightSearchPayload,
  );

  return flightOffers.data;
}

export async function getAddressInformation(iataCode: string) {
  const addressInformation = await flightSearch.get<Address>(
    `/search/locations/city/${iataCode}`,
  );
  return addressInformation.data;
}

export async function getAirlineInformation(iataCode: string) {
  const airlineInformation = await flightSearch.get<Airline>(
    `/search/airlines/${iataCode}`,
  );
  return airlineInformation.data;
}
