import type {searchFlightsFormSchema} from "@/flight-search/schemas/flight-search/search-flights-form.schema";
import * as z from "zod";

export type FlightSearchPayload = z.infer<typeof searchFlightsFormSchema>;
