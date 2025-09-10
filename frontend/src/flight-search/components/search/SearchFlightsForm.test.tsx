import {render, screen, waitFor} from "@testing-library/react";
import userEvent from "@testing-library/user-event";
import {describe, it, expect, vi, beforeEach} from "vitest";
import {createRoutesStub} from "react-router";
import {SearchFlightsForm} from "./SearchFlightsForm";
import {mockSearchFlightsActionErrorData} from "@/test/fixtures";

global.ResizeObserver = vi.fn().mockImplementation(() => ({
  observe: vi.fn(),
  unobserve: vi.fn(),
  disconnect: vi.fn(),
}));

vi.mock("./LocationCombobox", () => ({
  LocationCombobox: ({name, id}: {name: string; id: string}) => (
    <input
      data-testid={`${name}-combobox`}
      name={name}
      id={id}
    />
  ),
}));

const mockSearchFlightsAction = vi.fn();

describe("SearchFlightsForm", () => {
  beforeEach(() => {
    mockSearchFlightsAction.mockClear();
  });

  const createTestRoutes = (actionReturnValue?: unknown) => {
    mockSearchFlightsAction.mockResolvedValue(actionReturnValue);
    return createRoutesStub([
      {
        path: "/",
        Component: SearchFlightsForm,
        action: mockSearchFlightsAction,
      },
    ]);
  };

  it("should render all form fields", () => {
    const TestRoutes = createTestRoutes();
    render(<TestRoutes initialEntries={["/"]} />);

    expect(screen.getByLabelText("Departure Code")).toBeDefined();
    expect(screen.getByLabelText("Arrival Code")).toBeDefined();
    expect(screen.getByLabelText("Departure date")).toBeDefined();
    expect(screen.getByLabelText("Return date")).toBeDefined();
    expect(screen.getByLabelText("Adults")).toBeDefined();
    expect(screen.getByText("Currency")).toBeDefined(); // Label exists
    expect(screen.getByLabelText("Non-stop")).toBeDefined();
  });

  it("should render submit button", () => {
    const TestRoutes = createTestRoutes();
    render(<TestRoutes initialEntries={["/"]} />);

    const submitButton = screen.getByRole("button", {name: "Search Flights"});
    expect(submitButton).toBeDefined();
    expect(submitButton.getAttribute("type")).toBe("submit");
  });

  it("should render LocationCombobox for departure and arrival", () => {
    const TestRoutes = createTestRoutes();
    render(<TestRoutes initialEntries={["/"]} />);

    expect(screen.getByTestId("originLocationCode-combobox")).toBeDefined();
    expect(
      screen.getByTestId("destinationLocationCode-combobox"),
    ).toBeDefined();
  });

  it("should set correct input types and attributes", () => {
    const TestRoutes = createTestRoutes();
    render(<TestRoutes initialEntries={["/"]} />);

    const departureDate = screen.getByLabelText("Departure date");
    const returnDate = screen.getByLabelText("Return date");
    const adults = screen.getByLabelText("Adults");
    const nonStop = screen.getByLabelText("Non-stop");

    expect(departureDate.getAttribute("type")).toBe("date");
    expect(returnDate.getAttribute("type")).toBe("date");
    expect(adults.getAttribute("type")).toBe("number");
    expect(adults.getAttribute("min")).toBe("0");
    expect(nonStop.getAttribute("role")).toBe("checkbox");
  });

  it("should render currency select with correct options", () => {
    const TestRoutes = createTestRoutes();
    render(<TestRoutes initialEntries={["/"]} />);

    expect(screen.getByText("MXN")).toBeDefined();
    expect(screen.getByText("USD")).toBeDefined();
    expect(screen.getByText("EUR")).toBeDefined();
  });

  it("should render currency select component", () => {
    const TestRoutes = createTestRoutes();
    render(<TestRoutes initialEntries={["/"]} />);

    const selectTrigger = screen.getByRole("combobox");
    expect(selectTrigger).toBeDefined();
    expect(screen.getByText("Select a value...")).toBeDefined();
  });

  it("should display validation errors after form submission with invalid data", async () => {
    const user = userEvent.setup();
    const TestRoutes = createTestRoutes(mockSearchFlightsActionErrorData);
    render(<TestRoutes initialEntries={["/"]} />);

    await user.click(screen.getByRole("button", {name: "Search Flights"}));

    await waitFor(() => {
      expect(screen.getByText("* Departure code is required")).toBeDefined();
    });

    await waitFor(() => {
      expect(screen.getByText("* Arrival code is required")).toBeDefined();
    });

    await waitFor(() => {
      expect(screen.getByText("* Departure date is required")).toBeDefined();
    });

    await waitFor(() => {
      expect(
        screen.getByText("* Return date cannot be less than departure date"),
      ).toBeDefined();
    });

    await waitFor(() => {
      expect(
        screen.getByText("* Number of adults must be greater than 0"),
      ).toBeDefined();
    });

    await waitFor(() => {
      expect(screen.getByText("* Currency code is required")).toBeDefined();
    });
  });

  it("should not display errors initially", () => {
    const TestRoutes = createTestRoutes();
    render(<TestRoutes initialEntries={["/"]} />);

    expect(screen.queryByText("* Departure code is required")).toBeNull();
    expect(screen.queryByText("* Arrival code is required")).toBeNull();
    expect(screen.queryByText("* Departure date is required")).toBeNull();
    expect(
      screen.queryByText("* Return date cannot be less than departure date"),
    ).toBeNull();
    expect(
      screen.queryByText("* Number of adults must be greater than 0"),
    ).toBeNull();
    expect(screen.queryByText("* Currency code is required")).toBeNull();
  });

  it("should submit form successfully with valid data", async () => {
    const user = userEvent.setup();
    const TestRoutes = createTestRoutes({ok: true, error: null});
    render(<TestRoutes initialEntries={["/"]} />);

    await user.type(screen.getByTestId("originLocationCode-combobox"), "NYC");
    await user.type(
      screen.getByTestId("destinationLocationCode-combobox"),
      "LAX",
    );
    await user.type(screen.getByLabelText("Departure date"), "2025-12-01");
    await user.type(screen.getByLabelText("Return date"), "2025-12-05");
    await user.type(screen.getByLabelText("Adults"), "2");

    await user.click(screen.getByRole("button", {name: "Search Flights"}));

    await waitFor(() => {
      expect(mockSearchFlightsAction).toHaveBeenCalled();
    });
  });
});
