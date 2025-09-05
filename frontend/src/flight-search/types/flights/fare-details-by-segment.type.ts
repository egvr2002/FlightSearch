import type {Amenity} from "./amenity.type";

export type FareDetailsBySegment = {
  segmentId: string;
  cabin: string;
  amenities: Amenity[];
  class: string;
};
