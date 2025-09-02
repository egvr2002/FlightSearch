import {clsx, type ClassValue} from "clsx";
import {twMerge} from "tailwind-merge";

export function cn(...inputs: ClassValue[]) {
  return twMerge(clsx(inputs));
}

export function generateSearchParamsFromObject(object: object): string {
  const urlSearchParams = new URLSearchParams();
  for (const [key, value] of Object.entries(object)) {
    urlSearchParams.append(key, value.toString());
  }
  return urlSearchParams.toString();
}
