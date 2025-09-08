import {flightSearch} from "@/flight-search/api/flight-search.api";
import {getCacheKey, getCachedData, setCachedData} from "./cache.service";
import {
  ADDRESS_PREFIX,
  AIRLINE_PREFIX,
  FLIGHT_OFFERS_PREFIX,
} from "../constants/cache.constants";

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
  const key = getCacheKey(FLIGHT_OFFERS_PREFIX, flightSearchPayload);
  const cached = getCachedData<FlightOffer[]>(key);
  if (cached) {
    return cached;
  }

  const flightOffers = await flightSearch.post<FlightOffer[]>(
    "/search/flights",
    flightSearchPayload,
  );
  setCachedData(key, flightOffers.data);

  return flightOffers.data;
}

export async function getAddressInformation(iataCode: string) {
  const key = getCacheKey(ADDRESS_PREFIX, iataCode);
  const cached = getCachedData<Address>(key);
  if (cached) {
    return cached;
  }

  const addressInformation = await flightSearch.get<Address>(
    `/search/locations/city/${iataCode}`,
  );
  setCachedData(key, addressInformation.data);

  return addressInformation.data;
}

export async function getAirlineInformation(iataCode: string) {
  const key = getCacheKey(AIRLINE_PREFIX, iataCode);
  const cached = getCachedData<Airline>(key);
  if (cached) {
    return cached;
  }

  const airlineInformation = await flightSearch.get<Airline>(
    `/search/airlines/${iataCode}`,
  );
  setCachedData(key, airlineInformation.data);

  return airlineInformation.data;
}
