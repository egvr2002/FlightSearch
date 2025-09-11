import type {Segment} from "@/flight-search/types/flights/segment.type";

export const mockSegment: Segment = {
  segmentId: "segment-123",
  departure: {iataCode: "JFK", at: "2025-09-08T08:00:00"},
  arrival: {iataCode: "LAX", at: "2025-09-08T15:00:00"},
  carrierCode: "AA",
  operatingCarrierCode: "AA",
  number: "100",
  aircraftCode: "B737",
  numberOfStops: 0,
  layoverDuration: "PT2H30M",
};

export const mockSegmentLong: Segment = {
  segmentId: "segment-456",
  departure: {iataCode: "NYC", at: "2025-09-08T10:00:00"},
  arrival: {iataCode: "MIA", at: "2025-09-08T20:00:00"},
  carrierCode: "DL",
  operatingCarrierCode: "DL",
  number: "200",
  aircraftCode: "A320",
  numberOfStops: 1,
  layoverDuration: "PT4H15M",
};

export const mockSegmentShort: Segment = {
  segmentId: "segment-789",
  departure: {iataCode: "BOS", at: "2025-09-08T14:00:00"},
  arrival: {iataCode: "DCA", at: "2025-09-08T16:30:00"},
  carrierCode: "UA",
  operatingCarrierCode: "UA",
  number: "300",
  aircraftCode: "B757",
  numberOfStops: 0,
  layoverDuration: "PT1H45M",
};

export const mockSegmentWithoutLayover: Segment = {
  segmentId: "segment-456",
  departure: {iataCode: "ORD", at: "2025-09-08T13:00:00"},
  arrival: {iataCode: "LAX", at: "2025-09-08T15:00:00"},
  carrierCode: "AA",
  number: "200",
  aircraftCode: "B737",
  numberOfStops: 0,
  layoverDuration: "",
};

export const mockSegments: Segment[] = [
  {
    segmentId: "segment-1",
    departure: {iataCode: "JFK", at: "2025-09-08T08:00:00"},
    arrival: {iataCode: "ORD", at: "2025-09-08T10:00:00"},
    carrierCode: "AA",
    number: "123",
    aircraftCode: "B737",
    numberOfStops: 0,
    layoverDuration: "PT2H",
  },
  {
    segmentId: "segment-2",
    departure: {iataCode: "ORD", at: "2025-09-08T12:00:00"},
    arrival: {iataCode: "LAX", at: "2025-09-08T13:30:00"},
    carrierCode: "AA",
    number: "456",
    aircraftCode: "B737",
    numberOfStops: 0,
    layoverDuration: "",
  },
];
