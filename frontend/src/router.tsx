import {createBrowserRouter, Navigate} from "react-router";

import {MainLayout} from "./flight-search/layouts/MainLayout";

import {FlightDetailsPage} from "./flight-search/pages/FlightDetailsPage";
import {FlightResultsPage} from "./flight-search/pages/FlightsResultsPage";
import {FlightSearchPage} from "./flight-search/pages/FlightSearchPage";

import {searchFlightsAction} from "./flight-search/actions/search-flights.action";
import {getFlightOffersLoader} from "./flight-search/loaders/get-flight-offers.loader";

export const router = createBrowserRouter([
  {
    path: "/",
    Component: MainLayout,
    children: [
      {
        index: true,
        Component: FlightSearchPage,
        action: searchFlightsAction,
      },
      {
        path: "flight-offers",
        children: [
          {
            index: true,
            Component: FlightResultsPage,
            loader: getFlightOffersLoader,
          },
          {
            path: ":flightOfferId",
            Component: FlightDetailsPage,
          },
        ],
      },
      {
        path: "*",
        element: <Navigate to="/" />,
      },
    ],
  },
]);
