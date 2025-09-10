import {describe, expect, it} from "vitest";
import {sortFlightOffers} from "./sort.service";
import type {FlightOffer} from "../types/flights/flight-offer.type";
import type {SortConfig} from "../types/sorting/sort-config.type";

const createMockFlightOffer = (
  id: string,
  grandTotalPrice: string,
  duration: string,
): FlightOffer => ({
  flightOfferId: id,
  grandTotalPrice,
  totalPrice: grandTotalPrice,
  basePrice: "100.00",
  currency: "USD",
  fees: [],
  travelerPricings: [],
  itineraries: [
    {
      itineraryId: `itinerary-${id}`,
      duration,
      segments: [],
      initialDepartureDate: "2025-01-01T10:00:00",
      finalArrivalDate: "2025-01-01T12:00:00",
    },
  ],
});

describe("sortFlightOffers", () => {
  const mockFlightOffers: FlightOffer[] = [
    createMockFlightOffer("1", "500.00", "PT2H30M"),
    createMockFlightOffer("2", "300.00", "PT1H45M"),
    createMockFlightOffer("3", "400.00", "PT3H00M"),
  ];

  it("returns original array when no sort config is provided", () => {
    const sortConfig: SortConfig = [];
    const result = sortFlightOffers(mockFlightOffers, sortConfig);

    expect(result).toEqual(mockFlightOffers);
    expect(result).toBe(mockFlightOffers);
  });

  it("sorts by price in ascending order", () => {
    const sortConfig: SortConfig = [{field: "price", direction: "asc"}];
    const result = sortFlightOffers(mockFlightOffers, sortConfig);

    expect(result[0].grandTotalPrice).toBe("300.00");
    expect(result[1].grandTotalPrice).toBe("400.00");
    expect(result[2].grandTotalPrice).toBe("500.00");
  });

  it("sorts by price in descending order", () => {
    const sortConfig: SortConfig = [{field: "price", direction: "desc"}];
    const result = sortFlightOffers(mockFlightOffers, sortConfig);

    expect(result[0].grandTotalPrice).toBe("500.00");
    expect(result[1].grandTotalPrice).toBe("400.00");
    expect(result[2].grandTotalPrice).toBe("300.00");
  });

  it("sorts by duration in ascending order", () => {
    const sortConfig: SortConfig = [{field: "duration", direction: "asc"}];
    const result = sortFlightOffers(mockFlightOffers, sortConfig);

    expect(result[0].itineraries[0].duration).toBe("PT1H45M");
    expect(result[1].itineraries[0].duration).toBe("PT2H30M");
    expect(result[2].itineraries[0].duration).toBe("PT3H00M");
  });

  it("sorts by duration in descending order", () => {
    const sortConfig: SortConfig = [{field: "duration", direction: "desc"}];
    const result = sortFlightOffers(mockFlightOffers, sortConfig);

    expect(result[0].itineraries[0].duration).toBe("PT3H00M");
    expect(result[1].itineraries[0].duration).toBe("PT2H30M");
    expect(result[2].itineraries[0].duration).toBe("PT1H45M");
  });

  it("sorts by multiple criteria with price first, then duration", () => {
    // Add flights with same price but different durations
    const flightOffers: FlightOffer[] = [
      createMockFlightOffer("1", "400.00", "PT3H00M"),
      createMockFlightOffer("2", "400.00", "PT1H30M"),
      createMockFlightOffer("3", "300.00", "PT2H00M"),
    ];

    const sortConfig: SortConfig = [
      {field: "price", direction: "asc"},
      {field: "duration", direction: "asc"},
    ];

    const result = sortFlightOffers(flightOffers, sortConfig);

    // First should be lowest price
    expect(result[0].grandTotalPrice).toBe("300.00");
    // Among same prices, shortest duration first
    expect(result[1].grandTotalPrice).toBe("400.00");
    expect(result[1].itineraries[0].duration).toBe("PT1H30M");
    expect(result[2].grandTotalPrice).toBe("400.00");
    expect(result[2].itineraries[0].duration).toBe("PT3H00M");
  });

  it("handles flights with multiple itineraries", () => {
    const multiItineraryFlight: FlightOffer = {
      ...createMockFlightOffer("multi", "600.00", "PT1H00M"),
      itineraries: [
        {
          itineraryId: "itinerary-1",
          duration: "PT1H00M", // 60 minutes
          segments: [],
          initialDepartureDate: "2025-01-01T10:00:00",
          finalArrivalDate: "2025-01-01T11:00:00",
        },
        {
          itineraryId: "itinerary-2",
          duration: "PT2H00M", // 120 minutes
          segments: [],
          initialDepartureDate: "2025-01-02T10:00:00",
          finalArrivalDate: "2025-01-02T12:00:00",
        },
      ],
    };

    const flightOffers = [mockFlightOffers[1], multiItineraryFlight];
    const sortConfig: SortConfig = [{field: "duration", direction: "asc"}];
    const result = sortFlightOffers(flightOffers, sortConfig);

    expect(result[0].flightOfferId).toBe("2");
    expect(result[1].flightOfferId).toBe("multi");
  });

  it("handles edge cases with zero duration", () => {
    const flightOffers: FlightOffer[] = [
      createMockFlightOffer("1", "400.00", "PT0M"),
      createMockFlightOffer("2", "300.00", "PT1H00M"),
    ];

    const sortConfig: SortConfig = [{field: "duration", direction: "asc"}];
    const result = sortFlightOffers(flightOffers, sortConfig);

    expect(result[0].flightOfferId).toBe("1");
    expect(result[1].flightOfferId).toBe("2");
  });

  it("handles invalid duration formats gracefully", () => {
    const flightOffers: FlightOffer[] = [
      createMockFlightOffer("1", "400.00", "INVALID"),
      createMockFlightOffer("2", "300.00", "PT1H00M"),
    ];

    const sortConfig: SortConfig = [{field: "duration", direction: "asc"}];
    const result = sortFlightOffers(flightOffers, sortConfig);

    expect(result[0].flightOfferId).toBe("1");
    expect(result[1].flightOfferId).toBe("2");
  });

  it("preserves original array immutability", () => {
    const originalOrder = mockFlightOffers.map((offer) => offer.flightOfferId);
    const sortConfig: SortConfig = [{field: "price", direction: "desc"}];

    sortFlightOffers(mockFlightOffers, sortConfig);

    const currentOrder = mockFlightOffers.map((offer) => offer.flightOfferId);
    expect(currentOrder).toEqual(originalOrder);
  });
});
