import {describe, expect, it, beforeEach, vi, afterEach} from "vitest";
import AxiosMockAdapter from "axios-mock-adapter";
import {flightSearch} from "../api/flight-search.api";
import {mockLocations} from "@/test/fixtures/locations";
import {mockFlightOffer} from "@/test/fixtures/flight-offers";
import {
  searchLocations,
  getFlightOffers,
  getAddressInformation,
  getAirlineInformation,
} from "./flight-search.service";

import * as cacheService from "./cache.service";

describe("FlightSearchService", () => {
  let axiosMock: AxiosMockAdapter;

  vi.mock("./cache.service", () => ({
    getCacheKey: vi.fn(),
    getCachedData: vi.fn(),
    setCachedData: vi.fn(),
    clearCache: vi.fn(),
  }));

  const mockCacheService = vi.mocked(cacheService);

  beforeEach(() => {
    axiosMock = new AxiosMockAdapter(flightSearch);
    vi.clearAllMocks();
  });

  afterEach(() => {
    axiosMock.reset();
  });

  describe("searchLocations", () => {
    it("should search locations correctly", async () => {
      const keyword = "MEX";
      axiosMock.onGet("/search/locations/airport").reply(200, mockLocations);

      const locations = await searchLocations({keyword});

      expect(locations).toHaveLength(mockLocations.length);
      expect(locations).toEqual(mockLocations);
    });

    it("should handle empty results", async () => {
      const keyword = "UNKNOWN";
      axiosMock.onGet("/search/locations/airport").reply(200, []);

      const locations = await searchLocations({keyword});
      expect(locations).toHaveLength(0);
      expect(locations).toEqual([]);
    });

    it("should throw an error if keyword doesn't have a proper value", async () => {
      const keyword = ",";
      axiosMock.onGet("/search/locations/airport").reply(400);
      await expect(searchLocations({keyword})).rejects.toThrow();
    });

    it("should pass all parameters to the API", async () => {
      const params = {
        keyword: "NYC",
        pageLimit: 20,
        pageOffset: 10,
        subType: "CITY" as const,
        view: "LIGHT" as const,
      };

      axiosMock.onGet("/search/locations/airport").reply(200, mockLocations);

      await searchLocations(params);

      expect(axiosMock.history.get[0].params).toEqual({
        keyword: "NYC",
        pageLimit: 20,
        pageOffset: 10,
        subType: "CITY",
        view: "LIGHT",
      });
    });

    it("should use default parameters when not provided", async () => {
      const keyword = "LAX";
      axiosMock.onGet("/search/locations/airport").reply(200, mockLocations);

      await searchLocations({keyword});

      expect(axiosMock.history.get[0].params).toEqual({
        keyword: "LAX",
        pageLimit: 10,
        pageOffset: 0,
        subType: "AIRPORT",
        view: "LIGHT",
      });
    });
  });

  describe("getFlightOffers", () => {
    const mockFlightSearchPayload = {
      originLocationCode: "NYC",
      destinationLocationCode: "LAX",
      departureDate: "2025-12-25",
      adults: 2,
      currencyCode: "USD" as const,
      nonStop: false,
      max: 10,
    };

    const mockFlightOffers = [mockFlightOffer];

    beforeEach(() => {
      mockCacheService.getCacheKey.mockReturnValue("cache-key-123");
    });

    it("should return cached data when available", async () => {
      mockCacheService.getCachedData.mockReturnValue(mockFlightOffers);

      const result = await getFlightOffers(mockFlightSearchPayload);

      expect(result).toEqual(mockFlightOffers);
      expect(mockCacheService.getCachedData).toHaveBeenCalledWith(
        "cache-key-123",
      );
      expect(axiosMock.history.post).toHaveLength(0); // No API call should be made
    });

    it("should make API call and cache data when not cached", async () => {
      mockCacheService.getCachedData.mockReturnValue(null);
      axiosMock.onPost("/search/flights").reply(200, mockFlightOffers);

      const result = await getFlightOffers(mockFlightSearchPayload);

      expect(result).toEqual(mockFlightOffers);
      expect(mockCacheService.setCachedData).toHaveBeenCalledWith(
        "cache-key-123",
        mockFlightOffers,
      );
      expect(axiosMock.history.post[0].data).toBe(
        JSON.stringify(mockFlightSearchPayload),
      );
    });

    it("should generate correct cache key", async () => {
      mockCacheService.getCachedData.mockReturnValue(null);
      axiosMock.onPost("/search/flights").reply(200, mockFlightOffers);

      await getFlightOffers(mockFlightSearchPayload);

      expect(mockCacheService.getCacheKey).toHaveBeenCalledWith(
        "flight-offers-",
        mockFlightSearchPayload,
      );
    });

    it("should handle API errors", async () => {
      mockCacheService.getCachedData.mockReturnValue(null);
      axiosMock.onPost("/search/flights").reply(500);

      await expect(getFlightOffers(mockFlightSearchPayload)).rejects.toThrow();
      expect(mockCacheService.setCachedData).not.toHaveBeenCalled();
    });
  });

  describe("getAddressInformation", () => {
    const mockAddress = {cityName: "New York"};
    const iataCode = "NYC";

    beforeEach(() => {
      mockCacheService.getCacheKey.mockReturnValue("address-cache-key");
    });

    it("should return cached address when available", async () => {
      mockCacheService.getCachedData.mockReturnValue(mockAddress);

      const result = await getAddressInformation(iataCode);

      expect(result).toEqual(mockAddress);
      expect(mockCacheService.getCachedData).toHaveBeenCalledWith(
        "address-cache-key",
      );
      expect(axiosMock.history.get).toHaveLength(0);
    });

    it("should make API call and cache data when not cached", async () => {
      mockCacheService.getCachedData.mockReturnValue(null);
      axiosMock
        .onGet(`/search/locations/city/${iataCode}`)
        .reply(200, mockAddress);

      const result = await getAddressInformation(iataCode);

      expect(result).toEqual(mockAddress);
      expect(mockCacheService.setCachedData).toHaveBeenCalledWith(
        "address-cache-key",
        mockAddress,
      );
      expect(axiosMock.history.get[0].url).toBe(
        `/search/locations/city/${iataCode}`,
      );
    });

    it("should generate correct cache key", async () => {
      mockCacheService.getCachedData.mockReturnValue(null);
      axiosMock
        .onGet(`/search/locations/city/${iataCode}`)
        .reply(200, mockAddress);

      await getAddressInformation(iataCode);

      expect(mockCacheService.getCacheKey).toHaveBeenCalledWith(
        "address-",
        iataCode,
      );
    });

    it("should handle API errors", async () => {
      mockCacheService.getCachedData.mockReturnValue(null);
      axiosMock.onGet(`/search/locations/city/${iataCode}`).reply(404);

      await expect(getAddressInformation(iataCode)).rejects.toThrow();
      expect(mockCacheService.setCachedData).not.toHaveBeenCalled();
    });
  });

  describe("getAirlineInformation", () => {
    const mockAirline = {name: "American Airlines", iataCode: "AA"};
    const iataCode = "AA";

    beforeEach(() => {
      mockCacheService.getCacheKey.mockReturnValue("airline-cache-key");
    });

    it("should return cached airline when available", async () => {
      mockCacheService.getCachedData.mockReturnValue(mockAirline);

      const result = await getAirlineInformation(iataCode);

      expect(result).toEqual(mockAirline);
      expect(mockCacheService.getCachedData).toHaveBeenCalledWith(
        "airline-cache-key",
      );
      expect(axiosMock.history.get).toHaveLength(0);
    });

    it("should make API call and cache data when not cached", async () => {
      mockCacheService.getCachedData.mockReturnValue(null);
      axiosMock.onGet(`/search/airlines/${iataCode}`).reply(200, mockAirline);

      const result = await getAirlineInformation(iataCode);

      expect(result).toEqual(mockAirline);
      expect(mockCacheService.setCachedData).toHaveBeenCalledWith(
        "airline-cache-key",
        mockAirline,
      );
      expect(axiosMock.history.get[0].url).toBe(`/search/airlines/${iataCode}`);
    });

    it("should generate correct cache key", async () => {
      mockCacheService.getCachedData.mockReturnValue(null);
      axiosMock.onGet(`/search/airlines/${iataCode}`).reply(200, mockAirline);

      await getAirlineInformation(iataCode);

      expect(mockCacheService.getCacheKey).toHaveBeenCalledWith(
        "airline-",
        iataCode,
      );
    });

    it("should handle API errors", async () => {
      mockCacheService.getCachedData.mockReturnValue(null);
      axiosMock.onGet(`/search/airlines/${iataCode}`).reply(404);

      await expect(getAirlineInformation(iataCode)).rejects.toThrow();
      expect(mockCacheService.setCachedData).not.toHaveBeenCalled();
    });
  });
});
