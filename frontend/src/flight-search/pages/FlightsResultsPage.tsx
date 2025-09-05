import {Suspense} from "react";
import {useLoaderData} from "react-router";

import {ResultsSortOptions} from "../components/ResultsSortOptions";
import {GoBackToSearchButton} from "../components/GoBackToSearchButton";

import type {getFlightOffersLoader} from "../loaders/get-flight-offers.loader";
import {FlightOffersSkeleton} from "../components/skeletons/FlightOffersSkeleton";
import {FlightOffers} from "../components/FlightOffers";

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
