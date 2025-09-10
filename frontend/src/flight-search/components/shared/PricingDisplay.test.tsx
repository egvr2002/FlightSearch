import {screen} from "@testing-library/dom";
import {render} from "@testing-library/react";
import {describe, expect, it} from "vitest";
import {PricingDisplay} from "./PricingDisplay";

describe("Princing Display", () => {
  const grandTotalPrice = "1000";
  const perTravelerPrice = "500";
  const currency = "USD";

  it("should render price data correctly with aligment with default", () => {
    render(
      <PricingDisplay
        grandTotalPrice={grandTotalPrice}
        perTravelerPrice={perTravelerPrice}
        currency={currency}
      />,
    );

    const element = screen.getByTestId("pricing-display");

    expect(screen.getByText("$1,000.00")).toBeDefined();
    expect(screen.getByText("total")).toBeDefined();
    expect(screen.getByText("$500.00")).toBeDefined();
    expect(screen.getByText("per traveler")).toBeDefined();
    expect(element.className).toContain("text-right");
  });

  it("should apply right alignment when specified", () => {
    render(
      <PricingDisplay
        grandTotalPrice={grandTotalPrice}
        perTravelerPrice={perTravelerPrice}
        currency={currency}
        alignment="right"
      />,
    );
    const element = screen.getByTestId("pricing-display");
    expect(element.className).toContain("text-right");
  });

  it("should apply left alignment when specified", () => {
    render(
      <PricingDisplay
        grandTotalPrice={grandTotalPrice}
        perTravelerPrice={perTravelerPrice}
        currency={currency}
        alignment="left"
      />,
    );
    const element = screen.getByTestId("pricing-display");
    expect(element.className).toContain("text-left");
  });
});
