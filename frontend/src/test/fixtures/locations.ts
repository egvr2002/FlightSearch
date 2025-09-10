import type {Location} from "@/flight-search/types/locations/location.type";

export const mockLocations: Location[] = [
  {
    airportName: "BENITO JUAREZ INTL",
    iataCode: "MEX",
    cityName: "MEXICO CITY",
  },
  {
    airportName: "MIAMI INTL",
    iataCode: "MIA",
    cityName: "MIAMI",
  },
  {
    airportName: "ADOLFO SUAREZ BARAJAS",
    iataCode: "MAD",
    cityName: "MADRID",
  },
  {
    airportName: "ST PAUL INTL",
    iataCode: "MSP",
    cityName: "MINNEAPOLIS/ST PAUL",
  },
];

export const mockLocation = mockLocations[0];
