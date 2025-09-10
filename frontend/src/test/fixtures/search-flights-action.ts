// Mock action data for SearchFlightsForm tests
export const mockSearchFlightsActionErrorData = {
  ok: false,
  error: {
    originLocationCode: {errors: ["Departure code is required"]},
    destinationLocationCode: {errors: ["Arrival code is required"]},
    departureDate: {errors: ["Departure date is required"]},
    returnDate: {errors: ["Return date cannot be less than departure date"]},
    adults: {errors: ["Number of adults must be greater than 0"]},
    currencyCode: {errors: ["Currency code is required"]},
  },
};

export const mockSearchFlightsActionSuccessData = {
  ok: true,
  error: null,
};
