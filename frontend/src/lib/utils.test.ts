import {describe, expect, it} from "vitest";
import {
  cn,
  generateSearchParamsFromObject,
  formatCurrency,
  formatDate,
  formatDuration,
} from "./utils";

describe("Utils", () => {
  describe("cn", () => {
    it("merges class names correctly", () => {
      const result = cn("text-red-500", "bg-blue-500");
      expect(result).toBe("text-red-500 bg-blue-500");
    });

    it("handles conditional classes", () => {
      const condition = false;
      const result = cn("text-red-500", condition && "bg-blue-500", "p-4");
      expect(result).toBe("text-red-500 p-4");
    });

    it("handles conflicting Tailwind classes", () => {
      const result = cn("text-red-500", "text-blue-500");
      expect(result).toBe("text-blue-500");
    });
  });

  describe("generateSearchParamsFromObject", () => {
    it("converts object to URL search params", () => {
      const obj = {origin: "NYC", destination: "LAX", adults: "2"};
      const result = generateSearchParamsFromObject(obj);
      expect(result).toBe("origin=NYC&destination=LAX&adults=2");
    });

    it("handles null and undefined values", () => {
      const obj = {origin: "NYC", destination: null, adults: undefined};
      const result = generateSearchParamsFromObject(obj);
      expect(result).toBe("origin=NYC");
    });

    it("handles empty object", () => {
      const obj = {};
      const result = generateSearchParamsFromObject(obj);
      expect(result).toBe("");
    });

    it("handles special characters", () => {
      const obj = {query: "New York City"};
      const result = generateSearchParamsFromObject(obj);
      expect(result).toBe("query=New+York+City");
    });
  });

  describe("formatCurrency", () => {
    it("formats USD currency correctly", () => {
      const result = formatCurrency("123.45", "USD");
      expect(result).toBe("$123.45");
    });

    it("formats EUR currency correctly", () => {
      const result = formatCurrency("123.45", "EUR");
      expect(result).toBe("€123.45");
    });

    it("formats MXN currency correctly", () => {
      const result = formatCurrency("123.45", "MXN");
      expect(result).toBe("MX$123.45");
    });

    it("handles zero amount", () => {
      const result = formatCurrency("0", "USD");
      expect(result).toBe("$0.00");
    });

    it("handles large amounts", () => {
      const result = formatCurrency("1234567.89", "USD");
      expect(result).toBe("$1,234,567.89");
    });
  });

  describe("formatDate", () => {
    it("formats Date object correctly", () => {
      const date = new Date("2025-01-15T14:30:00Z");
      const result = formatDate(date);
      // The actual format is "Jan 15, 2025, 8:30 AM" (with comma, not "at")
      expect(result).toMatch(/Jan \d{1,2}, 2025, \d{1,2}:\d{2} (AM|PM)/);
    });

    it("formats ISO string correctly", () => {
      const dateString = "2025-01-15T14:30:00Z";
      const result = formatDate(dateString);
      expect(result).toMatch(/Jan \d{1,2}, 2025, \d{1,2}:\d{2} (AM|PM)/);
    });

    it("handles invalid date string", () => {
      const invalidDate = "invalid-date";
      const result = formatDate(invalidDate);
      expect(result).toBe("Invalid Date");
    });

    it("handles invalid Date object", () => {
      const invalidDate = new Date("invalid");
      const result = formatDate(invalidDate);
      expect(result).toBe("Invalid Date");
    });
  });

  describe("formatDuration", () => {
    it("formats hours and minutes", () => {
      const result = formatDuration("PT2H30M");
      expect(result).toBe("2h 30m");
    });

    it("formats only hours", () => {
      const result = formatDuration("PT2H");
      expect(result).toBe("2h");
    });

    it("formats only minutes", () => {
      const result = formatDuration("PT45M");
      expect(result).toBe("45m");
    });

    it("handles zero duration", () => {
      const result = formatDuration("PT0M");
      expect(result).toBe("0m");
    });

    it("handles invalid duration format", () => {
      const result = formatDuration("invalid");
      expect(result).toBe("0m");
    });

    it("handles alternative format with lowercase", () => {
      const result = formatDuration("2h30m");
      expect(result).toBe("2h 30m");
    });

    it("handles single digit durations", () => {
      const result = formatDuration("PT1H5M");
      expect(result).toBe("1h 5m");
    });
  });
});
