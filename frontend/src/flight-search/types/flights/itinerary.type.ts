import type {Segment} from "./segment.type";

export type Itinerary = {
  itineraryId: string;
  duration: string;
  segments: Segment[];
  initialDepartureDate: string;
  finalArrivalDate: string;
};
