import type {FlightOffer} from "../types/flights/flight-offer.type";
import type {
  SortConfig,
  SortField,
  SortDirection,
} from "../types/sorting/sort-config.type";

function parseDurationToMinutes(duration: string): number {
  // Duration format: "PT1H30M" or "PT2H" or "PT45M"
  const match = duration.match(/PT(?:(\d+)H)?(?:(\d+)M)?/);
  if (!match) return 0;

  const hours = parseInt(match[1] || "0", 10);
  const minutes = parseInt(match[2] || "0", 10);

  return hours * 60 + minutes;
}

function getTotalDuration(flightOffer: FlightOffer): number {
  // Sum all itinerary durations
  return flightOffer.itineraries.reduce((total, itinerary) => {
    return total + parseDurationToMinutes(itinerary.duration);
  }, 0);
}

function compareValues(
  a: number | string,
  b: number | string,
  direction: SortDirection,
): number {
  const aValue = typeof a === "string" ? parseFloat(a) : a;
  const bValue = typeof b === "string" ? parseFloat(b) : b;

  if (aValue < bValue) return direction === "asc" ? -1 : 1;
  if (aValue > bValue) return direction === "asc" ? 1 : -1;
  return 0;
}

function getSortValue(flightOffer: FlightOffer, field: SortField): number {
  switch (field) {
    case "price":
      return parseFloat(flightOffer.grandTotalPrice);
    case "duration":
      return getTotalDuration(flightOffer);
    default:
      return 0;
  }
}

export function sortFlightOffers(
  flightOffers: FlightOffer[],
  sortConfig: SortConfig,
): FlightOffer[] {
  if (sortConfig.length === 0) return flightOffers;

  return [...flightOffers].sort((a, b) => {
    for (const {field, direction} of sortConfig) {
      const aValue = getSortValue(a, field);
      const bValue = getSortValue(b, field);
      const comparison = compareValues(aValue, bValue, direction);

      if (comparison !== 0) return comparison;
    }
    return 0;
  });
}
