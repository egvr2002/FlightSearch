import type {Currency} from "@/flight-search/types/common/currency.type";
import {clsx, type ClassValue} from "clsx";
import {twMerge} from "tailwind-merge";

export function cn(...inputs: ClassValue[]) {
  return twMerge(clsx(inputs));
}

export function generateSearchParamsFromObject(object: object): string {
  const urlSearchParams = new URLSearchParams();
  for (const [key, value] of Object.entries(object)) {
    if (value !== null && value !== undefined) {
      urlSearchParams.append(key, value);
    }
  }
  return urlSearchParams.toString();
}

export function formatCurrency(
  amount: string,
  currency: Currency,
  locale = "en-US",
): string {
  return new Intl.NumberFormat(locale, {
    style: "currency",
    currency,
  }).format(parseFloat(amount));
}

export function formatDate(date: Date | string, locale = "en-US"): string {
  let dateObj: Date;

  if (typeof date === "string") {
    dateObj = new Date(date);
  } else {
    dateObj = date;
  }

  if (isNaN(dateObj.getTime())) {
    return "Invalid Date";
  }

  return new Intl.DateTimeFormat(locale, {
    hour: "2-digit",
    minute: "2-digit",
    year: "numeric",
    month: "short",
    day: "numeric",
  }).format(dateObj);
}

export function formatDuration(duration: string) {
  const hoursMatch = duration.match(/(\d+)h/i);
  const minutesMatch = duration.match(/(\d+)m/i);

  let formattedString = "";

  // If hours are found, add them to the string.
  if (hoursMatch) {
    formattedString += `${hoursMatch[1]}h`;
  }

  // If both hours and minutes exist, add a space.
  if (hoursMatch && minutesMatch) {
    formattedString += " ";
  }

  // If minutes are found, add them to the string.
  if (minutesMatch) {
    formattedString += `${minutesMatch[1]}m`;
  }

  // If nothing was found, it's a zero duration.
  if (!hoursMatch && !minutesMatch) {
    return "0m";
  }

  return formattedString;
}
