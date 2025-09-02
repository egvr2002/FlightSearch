import {flightSearch} from "@/flight-search/api/flight-search.api";
import type {Location} from "@/flight-search/types/locations/location.type";
import type {SearchLocations} from "@/flight-search/types/locations/search-location.type";

export async function searchLocations({
  keyword,
  pageLimit = 10,
  pageOffset = 0,
  subType = "AIRPORT",
  view = "LIGHT",
}: SearchLocations) {
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
