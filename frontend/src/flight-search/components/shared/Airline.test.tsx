import {afterEach, beforeEach, describe, expect, it} from "vitest";
import AxiosMockAdapter from "axios-mock-adapter";
import type {Airline} from "@/flight-search/types/common/airline-information.type";
import {Airline as AirlineComponent} from "./Airline";
import {flightSearch} from "@/flight-search/api/flight-search.api";
import {act} from "react";
import {render, screen} from "@testing-library/react";

describe("Airline", () => {
  let axiosMock: AxiosMockAdapter;

  const iataCode = "MEX";
  const expectedAirline: Airline = {
    iataCode: "MEX",
    name: "Mexico City International Airport",
  };

  beforeEach(() => {
    axiosMock = new AxiosMockAdapter(flightSearch);
    sessionStorage.clear();
  });

  afterEach(() => {
    axiosMock.restore();
  });

  it("should render airline name given a IATA code", async () => {
    axiosMock.onGet(`/search/airlines/${iataCode}`).reply(200, expectedAirline);

    await act(async () => {
      render(<AirlineComponent iataCode={iataCode} />);
    });

    expect(screen.getByText(expectedAirline.name.toLowerCase())).toBeDefined();
  });

  it("should render error element when API fails", async () => {
    axiosMock.onGet(`/search/airlines/${iataCode}`).reply(500);

    await act(async () => {
      render(<AirlineComponent iataCode={iataCode} />);
    });
    expect(screen.getByText("Airline unavailable")).toBeDefined();
  });
});
