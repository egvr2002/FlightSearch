import type {Segment} from "./segment.type";

export type Itinerary = {
  duration: string;
  segments: Segment[];
  initialDepartureDate: string;
  finalArrivalDate: string;
};
