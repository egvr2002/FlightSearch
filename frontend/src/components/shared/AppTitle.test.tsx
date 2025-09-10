import {render, screen} from "@testing-library/react";
import {describe, it, expect} from "vitest";
import {AppTitle} from "./AppTitle";
import {createRoutesStub} from "react-router";

describe("AppTitle", () => {
  const Stub = createRoutesStub([
    {
      path: "/",
      Component: AppTitle,
    },
  ]);

  it("renders the app title with correct text", () => {
    render(<Stub initialEntries={["/"]} />);

    expect(screen.getByText("Flight")).toBeDefined();
    expect(screen.getByText("Search")).toBeDefined();
  });

  it("renders the plane takeoff icon", () => {
    render(<Stub initialEntries={["/"]} />);

    expect(screen.getByTestId("plane-takeoff-icon")).toBeDefined();
  });

  it("renders a link to home page", () => {
    render(<Stub initialEntries={["/"]} />);

    const link = screen.getByRole("link");
    expect(link.getAttribute("href")).toBe("/");
  });

  it("includes screen reader text for accessibility", () => {
    render(<Stub initialEntries={["/"]} />);

    const screenReaderText = screen.getByText("FlightSearch");
    expect(screenReaderText.className).toContain("sr-only");
  });

  it("applies correct styling classes", () => {
    render(<Stub initialEntries={["/"]} />);

    const title = screen.getByRole("heading", {level: 1});
    expect(title.className).toContain("text-2xl");
    expect(title.className).toContain("font-bold");
  });
});
