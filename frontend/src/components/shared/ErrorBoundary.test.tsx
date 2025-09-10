import {render, screen} from "@testing-library/react";
import {describe, it, expect, vi, beforeEach, afterEach} from "vitest";
import {ErrorBoundary} from "./ErrorBoundary";

describe("ErrorBoundary", () => {
  const ThrowError = ({shouldThrow}: {shouldThrow: boolean}) => {
    if (shouldThrow) {
      throw new Error("Test error");
    }
    return <div>No error</div>;
  };

  const originalConsoleError = console.error;
  beforeEach(() => {
    console.error = vi.fn();
  });

  afterEach(() => {
    console.error = originalConsoleError;
  });

  it("renders children when there is no error", () => {
    render(
      <ErrorBoundary>
        <div>Child component</div>
      </ErrorBoundary>,
    );

    expect(screen.getByText("Child component")).toBeDefined();
  });

  it("renders fallback when there is an error", () => {
    const fallback = <div>Something went wrong</div>;

    render(
      <ErrorBoundary fallback={fallback}>
        <ThrowError shouldThrow={true} />
      </ErrorBoundary>,
    );

    expect(screen.getByText("Something went wrong")).toBeDefined();
  });

  it("calls onError callback when error occurs", () => {
    const onError = vi.fn();

    render(
      <ErrorBoundary
        onError={onError}
        fallback={<div>Error occurred</div>}
      >
        <ThrowError shouldThrow={true} />
      </ErrorBoundary>,
    );

    expect(onError).toHaveBeenCalledWith(
      expect.any(Error),
      expect.objectContaining({
        componentStack: expect.any(String),
      }),
    );
  });

  it("logs error to console when error occurs", () => {
    const consoleErrorSpy = vi.spyOn(console, "error");

    render(
      <ErrorBoundary fallback={<div>Error occurred</div>}>
        <ThrowError shouldThrow={true} />
      </ErrorBoundary>,
    );

    expect(consoleErrorSpy).toHaveBeenCalledWith(
      "Error boundary caught an error:",
      expect.any(Error),
      expect.any(Object),
    );
  });

  it("renders nothing when no fallback is provided and error occurs", () => {
    const {container} = render(
      <ErrorBoundary>
        <ThrowError shouldThrow={true} />
      </ErrorBoundary>,
    );

    expect(container.firstChild).toBeNull();
  });
});
