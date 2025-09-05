import {useLoaderData} from "react-router";

import {ResultsSortOptions} from "../components/ResultsSortOptions";
import {GoBackToSearchButton} from "../components/GoBackToSearchButton";
import {FlightOfferCard} from "../components/FlightOfferCard";

import type {getFlightOffersLoader} from "../loaders/get-flight-offers.loader";
import {useFlightOffersContext} from "../hooks/useFlightOffersContext";
import {useEffect} from "react";

export function FlightResultsPage() {
  const {data} = useLoaderData<typeof getFlightOffersLoader>();
  const {setFlightOffers} = useFlightOffersContext();

  useEffect(() => {
    if (data) {
      setFlightOffers(data);
    }
  }, [data, setFlightOffers]);

  return (
    <div className="space-y-4">
      <div className="flex items-center justify-between gap-2">
        <GoBackToSearchButton />
        <ResultsSortOptions />
      </div>

      {data?.map((flightOffer) => (
        <FlightOfferCard
          key={flightOffer.flightOfferId}
          flightOffer={flightOffer}
        />
      ))}
    </div>
  );
}
