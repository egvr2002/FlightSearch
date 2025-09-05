import {RouterProvider} from "react-router";

import {FlightOffersProvider} from "./flight-search/provider/FlightOffersProvider";
import {router} from "./router";

export default function App() {
  return (
    <FlightOffersProvider>
      <RouterProvider router={router} />
    </FlightOffersProvider>
  );
}
