import {useState} from "react";
import {useDebouncedCallback} from "use-debounce";

import type {Location} from "@/flight-search/types/locations/location.type";
import type {SearchLocationsPayload} from "../types/locations/search-location.type";

export function useLocationCombobox(
  onSearchLocations: (req: SearchLocationsPayload) => Promise<Location[]>,
  wait: number = 500,
) {
  const [open, setOpen] = useState(false);
  const [locations, setLocations] = useState<Location[]>([]);
  const [value, setValue] = useState("");
  const [isLoading, setIsLoading] = useState(false);

  const debouncedOnSearch = useDebouncedCallback(async (keyword: string) => {
    if (keyword.trim().length === 0) return;

    setIsLoading(true);
    const locationsResult = await onSearchLocations({keyword});
    setLocations(locationsResult);
    setIsLoading(false);
  }, wait);

  return {
    open,
    setOpen,
    value,
    setValue,
    locations,
    isLoading,
    debouncedOnSearch,
  };
}
