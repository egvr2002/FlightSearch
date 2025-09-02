import {redirect, type ActionFunctionArgs} from "react-router";
import * as z from "zod";

import {searchFlightsFormSchema} from "../schemas/search-flights-form.schema";
import {generateSearchParamsFromObject} from "@/lib/utils";

export async function searchFlightsAction({request}: ActionFunctionArgs) {
  const formData = await request.formData();
  const {data, success, error} = searchFlightsFormSchema.safeParse(
    Object.fromEntries(formData),
  );

  if (!success) {
    return {
      ok: false,
      error: z.treeifyError(error).properties,
    };
  }

  const searchParams = generateSearchParamsFromObject(data);
  return redirect(`/flight-offers?${searchParams}`);
}
