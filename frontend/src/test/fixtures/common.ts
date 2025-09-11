import {vi} from "vitest";

// Test data for hooks and other utilities
export const mockItineraryData = {
  departureIataCode: "JFK",
  arrivalIataCode: "LAX",
  initialDepartureDate: "2025-09-08T08:00:00",
  finalArrivalDate: "2025-09-08T15:00:00",
  totalDuration: "PT7H",
  numberOfStops: 1,
  stops: [],
};

// Common test constants
export const TEST_SEGMENT_ID = "segment-123";
export const TEST_TRAVELER_ID = "1";

// Mock navigation function for router tests
export const createMockNavigate = () => {
  const mockNavigate = vi.fn();
  return mockNavigate;
};
