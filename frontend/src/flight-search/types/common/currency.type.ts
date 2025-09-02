import type {currencySchema} from "@/flight-search/schemas/currency.schema";
import * as z from "zod";

export type Currency = z.infer<typeof currencySchema>;
