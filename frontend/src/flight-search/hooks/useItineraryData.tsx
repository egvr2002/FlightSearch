import type {Itinerary} from "../types/flights/itinerary.type";

export function useItineraryData(itinerary: Itinerary) {
  const {segments} = itinerary;

  const firstSegment = segments[0];
  const lastSegment = segments.at(-1)!;

  const numberOfStops = segments.reduce(
    (prev, current) => prev + current.numberOfStops,
    0,
  );

  const stops = segments
    .flatMap((segment) => segment.stops ?? [])
    .filter(Boolean);

  const carrierCode = firstSegment.carrierCode;
  const operatingCarrierCode = firstSegment.operatingCarrierCode ?? carrierCode;

  return {
    departureIataCode: firstSegment.departure.iataCode,
    arrivalIataCode: lastSegment.arrival.iataCode,
    initialDepartureDate: itinerary.initialDepartureDate,
    finalArrivalDate: itinerary.finalArrivalDate,
    totalDuration: itinerary.duration,
    numberOfStops,
    stops,
    carrierCode,
    operatingCarrierCode,
  };
}
