import {Button} from "@/components/ui/button";
import {Check, ChevronsUpDown, Loader2} from "lucide-react";
import {cn} from "@/lib/utils";
import {
  Command,
  CommandGroup,
  CommandInput,
  CommandItem,
  CommandList,
} from "@/components/ui/command";
import {Popover, PopoverContent, PopoverTrigger} from "@/components/ui/popover";
import {searchLocations} from "@/flight-search/services/flight-search.service";
import {useLocationCombobox} from "@/flight-search/hooks/useLocationCombobox";

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
        </PopoverTrigger>
        <PopoverContent className="w-96 p-0">
          <Command shouldFilter={false}>
            <CommandInput
              placeholder="Type to search airports..."
              className="h-9"
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
                      <span className="capitalize">{location.name}</span> (
                      {location.iataCode})
                      <Check
                        className={cn(
                          "ml-auto",
                          value === location.iataCode
                            ? "opacity-100"
                            : "opacity-0",
                        )}
                      />
                    </CommandItem>
                  ))
                )}
              </CommandGroup>
            </CommandList>
          </Command>
        </PopoverContent>
      </Popover>
    </>
  );
}
