package inc.encora.FlightSearch.amadeus.client.fixtures;

import inc.encora.FlightSearch.amadeus.constants.locations.LocationType;
import inc.encora.FlightSearch.amadeus.constants.locations.SortType;
import inc.encora.FlightSearch.amadeus.constants.locations.ViewType;
import inc.encora.FlightSearch.amadeus.dto.common.*;
import inc.encora.FlightSearch.amadeus.dto.request.AmadeusAirlinesLookupRequestDTO;
import inc.encora.FlightSearch.amadeus.dto.request.AmadeusSearchFlightsRequestDTO;
import inc.encora.FlightSearch.amadeus.dto.request.AmadeusSearchLocationsRequestDTO;
import inc.encora.FlightSearch.amadeus.dto.request.AmadeusTokenRequestDTO;
import inc.encora.FlightSearch.amadeus.dto.response.AmadeusListResponseDTO;
import inc.encora.FlightSearch.amadeus.dto.response.AmadeusTokenResponseDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class AmadeusClientTestFixtures {

    public static AmadeusTokenRequestDTO createTokenRequest() {
        return new AmadeusTokenRequestDTO("client_credentials", "test_client_id", "test_client_secret");
    }

    public static AmadeusTokenResponseDTO createTokenResponse() {
        AmadeusTokenResponseDTO response = new AmadeusTokenResponseDTO();
        response.setType("amadeusOAuth2Token");
        response.setUsername("test_user");
        response.setApplicationName("test_app");
        response.setClientId("test_client_id");
        response.setTokenType("Bearer");
        response.setAccessToken("test_access_token_12345");
        response.setExpiresIn(1799);
        response.setState("approved");
        response.setScope("read");
        return response;
    }

    public static AmadeusSearchFlightsRequestDTO createFlightSearchRequest() {
        AmadeusSearchFlightsRequestDTO request = new AmadeusSearchFlightsRequestDTO();
        request.setOriginLocationCode("MAD");
        request.setDestinationLocationCode("NYC");
        request.setDepartureDate(LocalDate.of(2024, 12, 25));
        request.setReturnDate(LocalDate.of(2024, 12, 30));
        request.setAdults(2);
        request.setNonStop(false);
        request.setMax(5);
        request.setCurrencyCode("USD");
        return request;
    }

    public static AmadeusListResponseDTO<AmadeusFlightOfferDTO> createFlightOffersResponse() {
        AmadeusListResponseDTO<AmadeusFlightOfferDTO> response = new AmadeusListResponseDTO<>();

        // Meta
        AmadeusMetaDTO meta = new AmadeusMetaDTO();
        meta.setCount(2);
        response.setMeta(meta);

        // Flight offers
        AmadeusFlightOfferDTO offer1 = createFlightOffer("1", "512.85", "USD");
        AmadeusFlightOfferDTO offer2 = createFlightOffer("2", "789.23", "USD");
        response.setData(List.of(offer1, offer2));

        return response;
    }

    private static AmadeusFlightOfferDTO createFlightOffer(String id, String price, String currency) {
        AmadeusFlightOfferDTO offer = new AmadeusFlightOfferDTO();
        offer.setType("flight-offer");
        offer.setId(id);
        offer.setSource("GDS");
        offer.setInstantTicketingRequired(false);
        offer.setNonHomogeneous(false);
        offer.setOneWay(false);
        offer.setLastTicketingDate(LocalDate.of(2024, 12, 24));
        offer.setLastTicketingDateTime(LocalDate.of(2024, 12, 24));
        offer.setNumberOfBookableSeats(7);

        // Itineraries
        AmadeusItineraryDTO itinerary = new AmadeusItineraryDTO();
        itinerary.setDuration("PT8H30M");

        AmadeusSegmentDTO segment = new AmadeusSegmentDTO();
        segment.setId("1");
        segment.setNumberOfStops(0);
        segment.setBlacklistedInEU(false);

        AmadeusFlightEndPointDTO departure = new AmadeusFlightEndPointDTO();
        departure.setIataCode("MAD");
        departure.setAt(LocalDateTime.of(2024, 12, 25, 10, 30));
        segment.setDeparture(departure);

        AmadeusFlightEndPointDTO arrival = new AmadeusFlightEndPointDTO();
        arrival.setIataCode("NYC");
        arrival.setAt(LocalDateTime.of(2024, 12, 25, 19, 0));
        segment.setArrival(arrival);

        itinerary.setSegments(List.of(segment));
        offer.setItineraries(List.of(itinerary));

        // Price
        AmadeusPriceDTO priceDTO = new AmadeusPriceDTO();
        priceDTO.setCurrency(currency);
        priceDTO.setTotal(price);
        priceDTO.setBase("400.00");
        priceDTO.setGrandTotal(price);
        offer.setPrice(priceDTO);

        return offer;
    }

    public static AmadeusSearchLocationsRequestDTO createLocationSearchRequest() {
        return AmadeusSearchLocationsRequestDTO.builder()
                .keyword("madrid")
                .pageLimit(10)
                .pageOffset(0)
                .subType(LocationType.AIRPORT)
                .sort(SortType.TRAVELERS_SCORE)
                .view(ViewType.LIGHT)
                .build();
    }

    public static AmadeusListResponseDTO<AmadeusLocationDTO> createLocationsResponse() {
        AmadeusListResponseDTO<AmadeusLocationDTO> response = new AmadeusListResponseDTO<>();

        // Meta
        AmadeusMetaDTO meta = new AmadeusMetaDTO();
        meta.setCount(2);
        response.setMeta(meta);

        // Locations
        AmadeusLocationDTO location1 = createLocation("MAD", "Madrid", "ES", "AIRPORT");
        AmadeusLocationDTO location2 = createLocation("MEX", "Mexico City", "MX", "AIRPORT");
        response.setData(List.of(location1, location2));

        return response;
    }

    private static AmadeusLocationDTO createLocation(String id, String name, String countryCode, String subType) {
        AmadeusLocationDTO location = new AmadeusLocationDTO();
        location.setType("location");
        location.setSubType(subType.equals("AIRPORT") ? LocationType.AIRPORT : LocationType.CITY);
        location.setName(name);
        location.setDetailedName(name + " Detailed");
        location.setId(id);
        location.setSelf(createSelfLinks(id));
        location.setIataCode(id.replace("C", ""));

        AmadeusAddressDTO address = new AmadeusAddressDTO();
        address.setCityName(name);
        address.setCountryName("Spain");
        location.setAddress(address);

        return location;
    }

    private static AmadeusSelfDTO createSelfLinks(String id) {
        AmadeusSelfDTO self = new AmadeusSelfDTO();
        self.setSelf("https://test.api.amadeus.com/v1/reference-data/locations/" + id);
        return self;
    }

    public static AmadeusAirlinesLookupRequestDTO createAirlineLookupRequest() {
        return new AmadeusAirlinesLookupRequestDTO("BA,AA");
    }

    public static AmadeusListResponseDTO<AmadeusAirlineInformationDTO> createAirlineInfoResponse() {
        AmadeusListResponseDTO<AmadeusAirlineInformationDTO> response = new AmadeusListResponseDTO<>();

        // Meta
        AmadeusMetaDTO meta = new AmadeusMetaDTO();
        meta.setCount(1);
        response.setMeta(meta);

        // Airlines
        AmadeusAirlineInformationDTO airline1 = createAirlineInfo("BA", "British Airways");
        response.setData(List.of(airline1));

        return response;
    }

    private static AmadeusAirlineInformationDTO createAirlineInfo(String iataCode, String businessName) {
        AmadeusAirlineInformationDTO airline = new AmadeusAirlineInformationDTO();
        airline.setType("airline");
        airline.setIataCode(iataCode);
        airline.setIcaoCode(iataCode + "IC");
        airline.setBusinessName(businessName);
        airline.setCommonName(businessName);
        return airline;
    }
}
