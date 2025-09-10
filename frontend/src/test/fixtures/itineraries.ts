import type {Itinerary} from "../../flight-search/types/flights/itinerary.type";
import {mockSegments} from "./segments";

export const mockItinerary: Itinerary = {
  itineraryId: "itinerary-123",
  duration: "PT5H30M",
  initialDepartureDate: "2025-09-08T08:00:00",
  finalArrivalDate: "2025-09-08T13:30:00",
  segments: mockSegments,
};

export const mockSingleSegmentItinerary: Itinerary = {
  itineraryId: "itinerary-456",
  duration: "PT7H",
  initialDepartureDate: "2025-09-08T08:00:00",
  finalArrivalDate: "2025-09-08T15:00:00",
  segments: [mockSegments[0]],
};

export const mockItineraryForOverview: Itinerary = {
  itineraryId: "test-itinerary-123",
  duration: "PT7H",
  initialDepartureDate: "2025-09-08T08:00:00",
  finalArrivalDate: "2025-09-08T15:00:00",
  segments: [
    {
      segmentId: "1",
      departure: {iataCode: "JFK", at: "2025-09-08T08:00:00"},
      arrival: {iataCode: "ORD", at: "2025-09-08T11:00:00"},
      carrierCode: "AA",
      number: "100",
      aircraftCode: "B737",
      numberOfStops: 0,
      layoverDuration: "PT2H",
    },
    {
      segmentId: "2",
      departure: {iataCode: "ORD", at: "2025-09-08T13:00:00"},
      arrival: {iataCode: "LAX", at: "2025-09-08T15:00:00"},
      carrierCode: "AA",
      number: "200",
      aircraftCode: "B737",
      numberOfStops: 0,
      layoverDuration: "",
    },
  ],
};
