import type {FlightOffer} from "../../flight-search/types/flights/flight-offer.type";
import {mockItinerary} from "./itineraries";
import {
  mockTravelerPricings,
  mockSingleTravelerPricing,
} from "./traveler-pricings";

export const mockFlightOffer: FlightOffer = {
  flightOfferId: "offer-123",
  currency: "USD",
  totalPrice: "500.00",
  basePrice: "400.00",
  grandTotalPrice: "550.00",
  fees: [
    {type: "SUPPLIER", amount: "25.00"},
    {type: "TAXES", amount: "75.00"},
  ],
  travelerPricings: mockTravelerPricings,
  itineraries: [mockItinerary],
};

export const mockFlightOfferWithSingleTraveler: FlightOffer = {
  ...mockFlightOffer,
  travelerPricings: mockSingleTravelerPricing,
};

export const mockFlightOfferWithoutFees: FlightOffer = {
  ...mockFlightOffer,
  fees: [],
};

export const mockSimpleFlightOffer: FlightOffer = {
  flightOfferId: "offer-123",
  currency: "USD",
  totalPrice: "500.00",
  basePrice: "400.00",
  grandTotalPrice: "500.00",
  fees: [],
  travelerPricings: [mockSingleTravelerPricing[0]],
  itineraries: [],
};
