import {Suspense} from "react";
import {useLoaderData} from "react-router";

import {FlightOffers} from "../components/results/FlightOffers";
import {GoBackToSearchButton} from "../components/results/GoBackToSearchButton";
import {ResultsSortOptions} from "../components/results/ResultsSortOptions";
import {FlightOffersSkeleton} from "../components/results/skeletons/FlightOffersSkeleton";

import type {getFlightOffersLoader} from "../loaders/get-flight-offers.loader";

export function FlightResultsPage() {
  const {data} = useLoaderData<typeof getFlightOffersLoader>();

  return (
    <div className="space-y-4">
      <div className="flex items-center justify-between gap-2">
        <GoBackToSearchButton />
        <ResultsSortOptions />
      </div>

      <Suspense fallback={<FlightOffersSkeleton />}>
        <FlightOffers flightOffersPromise={data!} />
      </Suspense>
    </div>
  );
}
