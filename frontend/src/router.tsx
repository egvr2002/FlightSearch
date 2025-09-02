import {createBrowserRouter, Navigate} from "react-router";

import {MainLayout} from "./flight-search/layouts/MainLayout";

import {FlightDetailsPage} from "./flight-search/pages/FlightDetailsPage";
import {FlightResultsPage} from "./flight-search/pages/FlightsResultsPage";
import {FlightSearchPage} from "./flight-search/pages/FlightSearchPage";

import {searchFlightsAction} from "./flight-search/actions/search-flights.action";

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
        path: "/flight-offers",
        children: [
          {
            index: true,
            Component: FlightResultsPage,
          },
          {
            path: "details",
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
