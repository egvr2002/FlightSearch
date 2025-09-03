import type {FareDetailsBySegment} from "./fare-details-by-segment.type";

export type TravelerPricing = {
  travelerId: string;
  basePrice: string;
  totalPrice: string;
  fareDetailsBySegment: FareDetailsBySegment[];
};
