import * as z from "zod";

import {currencySchema} from "./currency.schema";

export const searchFlightsFormSchema = z
  .object({
    originLocationCode: z
      .string()
      .min(1, {error: "Departure code is required"}),
    destinationLocationCode: z
      .string()
      .min(1, {error: "Arrival code is required"}),
    departureDate: z.iso
      .date({error: "Departure date is required"})
      .refine((date) => {
        const selectedDate = new Date(date + "T00:00-0800");
        const today = new Date();

        selectedDate.setHours(0, 0, 0, 0);
        today.setHours(0, 0, 0, 0);

        return selectedDate >= today;
      }, "Departure date cannot be in the past"),
    returnDate: z
      .union([
        z.string().min(1).pipe(z.iso.date()),
        z.literal("").transform(() => undefined),
      ])
      .optional(),
    adults: z.codec(
      z.string().min(1, {error: "Arrival code is required"}),
      z.number().min(1, {error: "Number of adults must be greater than 0"}),
      {
        encode: (numberValue) => `${numberValue}`,
        decode: (stringValue) => parseInt(stringValue),
      },
    ),
    currencyCode: currencySchema,
    nonStop: z
      .literal("on")
      .transform((v) => v === "on")
      .default(false),
  })
  .refine(
    (data) => {
      if (!data.returnDate) return true;

      const departureDate = new Date(data.departureDate);
      const returnDate = new Date(data.returnDate);

      departureDate.setHours(0, 0, 0, 0);
      returnDate.setHours(0, 0, 0, 0);

      return returnDate > departureDate;
    },
    {
      message: "Return date cannot be less than departure date",
      path: ["returnDate"],
    },
  );
