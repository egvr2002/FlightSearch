import {flightSearch} from "@/flight-search/api/flight-search.api";

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
