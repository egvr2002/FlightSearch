import {render, screen} from "@testing-library/react";
import {describe, it, expect} from "vitest";
import {FieldError} from "./FieldError";

describe("FieldError", () => {
  it("renders nothing when no error is provided", () => {
    const {container} = render(<FieldError />);
    expect(container.firstChild).toBeNull();
  });

  it("renders nothing when error is empty string", () => {
    const {container} = render(<FieldError error="" />);
    expect(container.firstChild).toBeNull();
  });

  it("renders error message when error is provided", () => {
    render(<FieldError error="This field is required" />);
    expect(screen.getByText("* This field is required")).toBeDefined();
  });

  it("applies correct accessibility attributes", () => {
    render(<FieldError error="Validation error" />);
    const errorElement = screen.getByText("* Validation error");
    expect(errorElement.getAttribute("aria-live")).toBe("polite");
  });

  it("applies default CSS classes", () => {
    render(<FieldError error="Error message" />);
    const errorElement = screen.getByText("* Error message");
    expect(errorElement.className).toContain("text-destructive");
    expect(errorElement.className).toContain("text-sm");
  });

  it("accepts additional props", () => {
    render(
      <FieldError
        error="Error message"
        data-testid="custom-error"
        className="custom-class"
      />,
    );
    const errorElement = screen.getByTestId("custom-error");
    expect(errorElement).toBeDefined();
    expect(errorElement.className).toContain("custom-class");
  });

  it("renders as a paragraph element", () => {
    render(<FieldError error="Error message" />);
    const errorElement = screen.getByText("* Error message");
    expect(errorElement.tagName).toBe("P");
  });
});
