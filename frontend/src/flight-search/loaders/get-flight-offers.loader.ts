import * as z from "zod";
import type {ActionFunctionArgs} from "react-router";

import {getFlightOffers} from "../services/flight-search.service";
import {searchFlightsFormSchema} from "../schemas/search-flights-form.schema";

export async function getFlightOffersLoader({request}: ActionFunctionArgs) {
  const url = new URL(request.url);
  const searchParams = Object.fromEntries(url.searchParams);
  const {success, data, error} =
    searchFlightsFormSchema.safeParse(searchParams);

  if (!success) {
    console.log(z.treeifyError(error).properties);
    return {
      ok: false,
      error: z.treeifyError(error).properties,
    };
  }

  const flightOffersPromise = getFlightOffers(data);

  return {
    ok: true,
    data: flightOffersPromise,
  };
}
