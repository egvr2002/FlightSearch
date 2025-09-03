import {ResultsSortOptions} from "../components/ResultsSortOptions";
import {GoBackToSearchButton} from "../components/GoBackToSearchButton";
import {FlightOfferCard} from "../components/FlightOfferCard";

export function FlightResultsPage() {
  return (
    <div className="space-y-4">
      <div className="flex items-center justify-between gap-2">
        <GoBackToSearchButton />
        <ResultsSortOptions />
      </div>

      <FlightOfferCard />
      <FlightOfferCard />
      <FlightOfferCard />
      <FlightOfferCard />
    </div>
  );
}
