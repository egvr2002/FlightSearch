import * as z from "zod";
import type {ActionFunctionArgs} from "react-router";
import {searchFlightsFormSchema} from "../schemas/search-flights-form.schema";

import {getFlightOffers} from "../services/flight-search.service";

export async function getFlightOffersLoader({request}: ActionFunctionArgs) {
  const url = new URL(request.url);
  const searchParams = Object.fromEntries(url.searchParams);
  const {success, data, error} =
    searchFlightsFormSchema.safeParse(searchParams);

  if (!success) {
    return {
      ok: false,
      error: z.treeifyError(error).properties,
    };
  }

  const flightOffers = await getFlightOffers(data);

  return {
    ok: true,
    data: flightOffers,
  };
}
