import type {LocationSubType} from "./location-sub-type.type";
import type {LocationView} from "./location-view.type";

export type SearchLocationsPayload = {
  keyword: string;
  subType?: LocationSubType;
  view?: LocationView;
  pageLimit?: number;
  pageOffset?: number;
};
