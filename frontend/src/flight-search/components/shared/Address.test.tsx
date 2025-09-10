import {afterEach, beforeEach, describe, expect, it} from "vitest";
import AxiosMockAdapter from "axios-mock-adapter";
import {flightSearch} from "@/flight-search/api/flight-search.api";
import type {Address} from "@/flight-search/types/common/address.type";
import {act, render, screen} from "@testing-library/react";
import {Address as AddressComponent} from "./Address";

describe("Address", () => {
  let axiosMock: AxiosMockAdapter;

  const iataCode = "MEX";
  const expectedAddress: Address = {cityName: "MEXICO CITY"};

  beforeEach(() => {
    axiosMock = new AxiosMockAdapter(flightSearch);
    sessionStorage.clear();
  });

  afterEach(() => {
    axiosMock.restore();
  });

  it("should render city name given a IATA code", async () => {
    axiosMock
      .onGet(`/search/locations/city/${iataCode}`)
      .reply(200, expectedAddress);

    await act(async () => {
      render(<AddressComponent iataCode={iataCode} />);
    });

    expect(
      screen.getByText(expectedAddress.cityName.toLowerCase()),
    ).toBeDefined();
  });

  it("should render error element when API fails", async () => {
    axiosMock.onGet(`/search/locations/city/${iataCode}`).reply(400);

    await act(async () => {
      render(<AddressComponent iataCode={iataCode} />);
    });

    expect(screen.getByText("City unavailable")).toBeDefined();
  });
});
