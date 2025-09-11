import {render, screen} from "@testing-library/react";
import {describe, expect, it, vi} from "vitest";
import {FlightEndpoint} from "./FlightEndpoint";
import {formatDate} from "@/lib/utils";

vi.mock("./Address", () => ({
  Address: ({iataCode}: {iataCode: string}) => <span>{iataCode}</span>,
}));

describe("FlightEndpoint", () => {
  const departureIataCode = "MEX";
  const arrivalIataCode = "JFK";
  const departureDateTime = "2023-10-01T10:00:00Z";
  const arrivalDateTime = "2023-10-01T18:00:00Z";

  it("it should render correctly", () => {
    render(
      <FlightEndpoint
        departureIataCode={departureIataCode}
        arrivalIataCode={arrivalIataCode}
        departureDateTime={departureDateTime}
        arrivalDateTime={arrivalDateTime}
      />,
    );

    expect(screen.getByText("Departure")).toBeDefined();
    expect(screen.getByText("Arrival")).toBeDefined();
    expect(screen.getByText(`(${departureIataCode})`)).toBeDefined();
    expect(screen.getByText(departureIataCode)).toBeDefined();
    expect(screen.getByText(formatDate(departureDateTime))).toBeDefined();
    expect(screen.getByText(`(${arrivalIataCode})`)).toBeDefined();
    expect(screen.getByText(arrivalIataCode)).toBeDefined();
    expect(screen.getByText(formatDate(arrivalDateTime))).toBeDefined();
  });
});
