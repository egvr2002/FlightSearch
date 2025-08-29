import {createBrowserRouter, Navigate} from "react-router";

import {MainLayout} from "./flights/layouts/MainLayout";

import {FlightDetailsPage} from "./flights/pages/FlightDetailsPage";
import {FlightsResultsPage} from "./flights/pages/FlightsResultsPage";
import {FlightsSearchPage} from "./flights/pages/FlightsSearchPage";

export const router = createBrowserRouter([
  {
    path: "/",
    Component: MainLayout,
    children: [
      {
        index: true,
        Component: FlightsSearchPage,
      },
      {
        path: "/flights",
        children: [
          {
            index: true,
            Component: FlightsResultsPage,
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
