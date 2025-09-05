import {useFlightOffersContext} from "../hooks/useFlightOffersContext";

export function FlightDetailsPage() {
  const {flightOffers} = useFlightOffersContext();

  return (
    <div>
      <p>Hello details page!</p>
      <pre>{JSON.stringify(flightOffers, null, 2)}</pre>
    </div>
  );
}
