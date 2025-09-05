import type {FlightEndPoint} from "./flight-end-point.type";
import type {FlightStop} from "./flight-stop.type";

export type Segment = {
  segmentId: string;
  departure: FlightEndPoint;
  arrival: FlightEndPoint;
  carrierCode: string;
  operatingCarrierCode?: string;
  stops?: FlightStop[];
  number: string;
  aircraftCode: string;
  numberOfStops: number;
};
