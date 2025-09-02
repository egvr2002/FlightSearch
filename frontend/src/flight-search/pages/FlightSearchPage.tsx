import {SearchFlightsForm} from "@/flight-search/components/SearchFlightsForm";
import {AppTitle} from "@/shared/components/AppTitle";

export function FlightSearchPage() {
  return (
    <div className="mx-auto max-w-sm space-y-8">
      <AppTitle />
      <SearchFlightsForm />
    </div>
  );
}
