import type {TravelerPricing} from "../../flight-search/types/flights/traveler-pricing.type";

export const mockTravelerPricings: TravelerPricing[] = [
  {
    travelerId: "1",
    basePrice: "400.00",
    totalPrice: "500.00",
    fareDetailsBySegment: [
      {
        segmentId: "segment-123",
        cabin: "ECONOMY",
        class: "M",
        amenities: [
          {name: "WIFI", isChargeable: false},
          {name: "MEAL", isChargeable: true},
          {name: "SEAT_SELECTION", isChargeable: false},
        ],
      },
    ],
  },
  {
    travelerId: "2",
    basePrice: "400.00",
    totalPrice: "500.00",
    fareDetailsBySegment: [
      {
        segmentId: "segment-123",
        cabin: "BUSINESS",
        class: "J",
        amenities: [
          {name: "WIFI", isChargeable: false},
          {name: "LOUNGE_ACCESS", isChargeable: false},
        ],
      },
    ],
  },
];

export const mockSingleTravelerPricing: TravelerPricing[] = [
  {
    travelerId: "1",
    basePrice: "400.00",
    totalPrice: "500.00",
    fareDetailsBySegment: [
      {
        segmentId: "segment-123",
        cabin: "ECONOMY",
        class: "M",
        amenities: [],
      },
    ],
  },
];

export const mockTravelerWithDifferentSegment: TravelerPricing[] = [
  {
    travelerId: "1",
    basePrice: "400.00",
    totalPrice: "500.00",
    fareDetailsBySegment: [
      {
        segmentId: "different-segment", // Different segment ID
        cabin: "ECONOMY",
        class: "M",
        amenities: [],
      },
    ],
  },
];
