import * as z from "zod";

export const currencySchema = z.enum(["MXN", "USD", "EUR"], {
  error: "Currency code is required",
});
