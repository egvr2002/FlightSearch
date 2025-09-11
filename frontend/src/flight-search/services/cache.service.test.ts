import {describe, it, expect, beforeEach, vi} from "vitest";
import {
  getCacheKey,
  getCachedData,
  setCachedData,
  clearCache,
} from "./cache.service";
import {
  FLIGHT_OFFERS_PREFIX,
  ADDRESS_PREFIX,
  AIRLINE_PREFIX,
} from "../constants/cache.constants";
import {mockFlightOffer} from "@/test/fixtures";

describe("CacheService", () => {
  const mockSessionStorage = {
    getItem: vi.fn(),
    setItem: vi.fn(),
    removeItem: vi.fn(),
  };

  Object.defineProperty(global, "sessionStorage", {
    value: mockSessionStorage,
    writable: true,
  });

  beforeEach(() => {
    vi.clearAllMocks();
  });

  describe("getCacheKey", () => {
    it("should generate cache key with flight offers prefix", () => {
      const searchPayload = {origin: "NYC", destination: "LAX"};
      const result = getCacheKey(FLIGHT_OFFERS_PREFIX, searchPayload);
      expect(result).toBe('flight-offers-{"origin":"NYC","destination":"LAX"}');
    });

    it("should generate cache key with address prefix", () => {
      const iataCode = "NYC";
      const result = getCacheKey(ADDRESS_PREFIX, iataCode);
      expect(result).toBe('address-"NYC"');
    });

    it("should generate cache key with airline prefix", () => {
      const airlineCode = "AA";
      const result = getCacheKey(AIRLINE_PREFIX, airlineCode);
      expect(result).toBe('airline-"AA"');
    });
  });

  describe("getCachedData", () => {
    it("should return flight offer data when cached", () => {
      const key = "flight-offers-test";
      mockSessionStorage.getItem.mockReturnValue(
        JSON.stringify(mockFlightOffer),
      );

      const result = getCachedData(key);

      expect(mockSessionStorage.getItem).toHaveBeenCalledWith(key);
      expect(result).toEqual(mockFlightOffer);
    });

    it("should return null when no cached data exists", () => {
      const key = "non-existent-key";
      mockSessionStorage.getItem.mockReturnValue(null);

      const result = getCachedData(key);

      expect(result).toBeNull();
    });

    it("should return null when JSON parsing fails", () => {
      const key = "invalid-json-key";
      mockSessionStorage.getItem.mockReturnValue("invalid json");

      const result = getCachedData(key);

      expect(result).toBeNull();
    });
  });

  describe("setCachedData", () => {
    it("should store flight offer data in sessionStorage", () => {
      const key = "flight-offers-test";

      setCachedData(key, mockFlightOffer);

      expect(mockSessionStorage.setItem).toHaveBeenCalledWith(
        key,
        JSON.stringify(mockFlightOffer),
      );
    });
  });

  describe("clearCache", () => {
    beforeEach(() => {
      const mockKeys = [
        "flight-offers-search1",
        "flight-offers-search2",
        "address-NYC",
        "airline-AA",
        "other-data",
      ];
      vi.spyOn(Object, "keys").mockReturnValue(mockKeys);
    });

    it("should clear flight offers cache", () => {
      clearCache(FLIGHT_OFFERS_PREFIX);

      expect(mockSessionStorage.removeItem).toHaveBeenCalledWith(
        "flight-offers-search1",
      );
      expect(mockSessionStorage.removeItem).toHaveBeenCalledWith(
        "flight-offers-search2",
      );
      expect(mockSessionStorage.removeItem).toHaveBeenCalledTimes(2);
    });

    it("should clear address cache", () => {
      clearCache(ADDRESS_PREFIX);

      expect(mockSessionStorage.removeItem).toHaveBeenCalledWith("address-NYC");
      expect(mockSessionStorage.removeItem).toHaveBeenCalledTimes(1);
    });

    it("should clear airline cache", () => {
      clearCache(AIRLINE_PREFIX);

      expect(mockSessionStorage.removeItem).toHaveBeenCalledWith("airline-AA");
      expect(mockSessionStorage.removeItem).toHaveBeenCalledTimes(1);
    });
  });
});
