import type {Currency} from "../common/currency.type";
import type {Fee} from "./fee.type";
import type {Itinerary} from "./itinerary.type";
import type {TravelerPricing} from "./traveler-pricing.type";

export type FlightOffer = {
  flightOfferId: string;
  itineraries: Itinerary[];
  currency: Currency;
  totalPrice: string;
  basePrice: string;
  grandTotalPrice: string;
  fees: Fee[];
  travelerPricings: TravelerPricing[];
};
