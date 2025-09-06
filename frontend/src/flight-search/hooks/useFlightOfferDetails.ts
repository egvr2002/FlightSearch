import {useParams} from "react-router";
import {useFlightOffersContext} from "./useFlightOffersContext";

export function useFlightOfferDetails() {
  const {flightOffers} = useFlightOffersContext();
  const {flightOfferId} = useParams();

  const flightOffer = flightOffers.find(
    (offer) => offer.flightOfferId === flightOfferId,
  );

  return {
    flightOffer,
    isFound: Boolean(flightOffer),
  };
}
