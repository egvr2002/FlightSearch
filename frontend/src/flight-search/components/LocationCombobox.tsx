import type {DebouncedState} from "use-debounce";

import {Button} from "@/components/ui/button";
import {Check, ChevronsUpDown, Loader2} from "lucide-react";
import {
  Command,
  CommandGroup,
  CommandInput,
  CommandItem,
  CommandList,
} from "@/components/ui/command";
import {Drawer, DrawerContent, DrawerTrigger} from "@/components/ui/drawer";
import {Popover, PopoverContent, PopoverTrigger} from "@/components/ui/popover";
import {searchLocations} from "@/flight-search/services/flight-search.service";

import {cn} from "@/lib/utils";
import {useIsMobile} from "@/hooks/useMobile";
import {useLocationCombobox} from "@/flight-search/hooks/useLocationCombobox";

import type {Location} from "../types/locations/location.type";

export function LocationCombobox({...props}: React.ComponentProps<"input">) {
  const {
    debouncedOnSearch,
    isLoading,
    locations,
    open,
    setOpen,
    setValue,
    value,
  } = useLocationCombobox(searchLocations);
  const isMobile = useIsMobile();

  if (isMobile) {
    return (
      <>
        <input
          type="hidden"
          value={value}
          {...props}
        />

        <Drawer
          open={open}
          onOpenChange={setOpen}
        >
          <DrawerTrigger>
            <LocationTriggerButton
              open={open}
              value={value}
            />
          </DrawerTrigger>

          <DrawerContent className="p-0">
            <LocationResultsCommand
              debouncedOnSearch={debouncedOnSearch}
              isLoading={isLoading}
              locations={locations}
              setOpen={setOpen}
              setValue={setValue}
              value={value}
            />
          </DrawerContent>
        </Drawer>
      </>
    );
  }

  return (
    <>
      <input
        type="hidden"
        value={value}
        {...props}
      />
      <Popover
        open={open}
        onOpenChange={setOpen}
      >
        <PopoverTrigger>
          <LocationTriggerButton
            open={open}
            value={value}
          />
        </PopoverTrigger>
        <PopoverContent className="min-w-md p-0">
          <LocationResultsCommand
            debouncedOnSearch={debouncedOnSearch}
            isLoading={isLoading}
            locations={locations}
            setOpen={setOpen}
            setValue={setValue}
            value={value}
          />
        </PopoverContent>
      </Popover>
    </>
  );
}

interface LocationTriggerButtonProps {
  open: boolean;
  value: string;
}

function LocationTriggerButton({open, value}: LocationTriggerButtonProps) {
  return (
    <Button
      variant="outline"
      role="combobox"
      aria-expanded={open}
      className="w-full justify-between"
      type="button"
      asChild
    >
      <div>
        {value ? (
          value
        ) : (
          <p className="text-muted-foreground">Search Airports...</p>
        )}
        <ChevronsUpDown className="opacity-50" />
      </div>
    </Button>
  );
}

interface LocationResultsCommandProps {
  debouncedOnSearch: DebouncedState<(keyword: string) => Promise<void>>;
  isLoading: boolean;
  locations: Location[];
  setOpen: React.Dispatch<React.SetStateAction<boolean>>;
  setValue: React.Dispatch<React.SetStateAction<string>>;
  value: string;
}
function LocationResultsCommand({
  debouncedOnSearch,
  isLoading,
  locations,
  setOpen,
  setValue,
  value,
}: LocationResultsCommandProps) {
  return (
    <Command shouldFilter={false}>
      <CommandInput
        placeholder="Type to search airports..."
        onValueChange={debouncedOnSearch}
      />
      <CommandList>
        <CommandGroup>
          {isLoading ? (
            <div className="flex items-center justify-center py-6">
              <Loader2 className="h-4 w-4 animate-spin" />
              <span className="text-muted-foreground ml-2 text-sm">
                Loading...
              </span>
            </div>
          ) : (
            locations.map((location) => (
              <CommandItem
                key={location.iataCode}
                value={location.iataCode}
                onSelect={(currentValue) => {
                  setValue(currentValue === value ? "" : currentValue);
                  setOpen(false);
                }}
              >
                {location.airportName} ({location.iataCode}) -{" "}
                {location.cityName}
                <Check
                  className={cn(
                    "ml-auto",
                    value === location.iataCode ? "opacity-100" : "opacity-0",
                  )}
                />
              </CommandItem>
            ))
          )}
        </CommandGroup>
      </CommandList>
    </Command>
  );
}
