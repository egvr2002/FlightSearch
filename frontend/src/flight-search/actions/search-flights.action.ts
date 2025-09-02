import {type ActionFunctionArgs} from "react-router";
import * as z from "zod";

import {searchFlightsFormSchema} from "../schemas/search-flights-form.schema";

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

  // TODO: Handle flights search making a HTTP call to the backend.
  return {
    ok: true,
    data,
  };
}
