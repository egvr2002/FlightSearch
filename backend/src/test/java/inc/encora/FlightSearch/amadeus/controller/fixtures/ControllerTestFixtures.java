package inc.encora.FlightSearch.amadeus.controller.fixtures;

import inc.encora.FlightSearch.amadeus.dto.request.SearchFlightsDTO;
import inc.encora.FlightSearch.amadeus.dto.request.SearchLocationsDTO;
import inc.encora.FlightSearch.amadeus.model.Address;
import inc.encora.FlightSearch.amadeus.model.AirlineInformation;
import inc.encora.FlightSearch.amadeus.model.FlightOffer;
import inc.encora.FlightSearch.amadeus.model.Location;

import java.time.LocalDate;

public class ControllerTestFixtures {

    public static SearchLocationsDTO validSearchLocationsRequest(String keyword) {
        SearchLocationsDTO dto = new SearchLocationsDTO();
        dto.setKeyword(keyword);
        dto.setPageLimit(10);
        dto.setPageOffset(0);
        dto.setSubType("AIRPORT");
        return dto;
    }

    public static SearchFlightsDTO validSearchFlightsRequest() {
        SearchFlightsDTO dto = new SearchFlightsDTO();
        dto.setOriginLocationCode("MAD");
        dto.setDestinationLocationCode("BCN");
        dto.setDepartureDate(LocalDate.of(2025, 12, 15));
        dto.setAdults(2);
        dto.setCurrencyCode("EUR");
        dto.setNonStop(false);
        dto.setMax(10);
        return dto;
    }

    public static SearchFlightsDTO invalidSearchFlightsRequestWithPastDate() {
        SearchFlightsDTO dto = new SearchFlightsDTO();
        dto.setOriginLocationCode("MAD");
        dto.setDestinationLocationCode("BCN");
        dto.setDepartureDate(LocalDate.of(2020, 1, 1)); // Past date
        dto.setAdults(1);
        return dto;
    }

    public static SearchFlightsDTO invalidSearchFlightsRequestWithZeroAdults() {
        SearchFlightsDTO dto = new SearchFlightsDTO();
        dto.setOriginLocationCode("MAD");
        dto.setDestinationLocationCode("BCN");
        dto.setDepartureDate(LocalDate.of(2025, 12, 15));
        dto.setAdults(0); // Invalid
        return dto;
    }

    public static SearchFlightsDTO invalidSearchFlightsRequestWithInvalidCurrency() {
        SearchFlightsDTO dto = new SearchFlightsDTO();
        dto.setOriginLocationCode("MAD");
        dto.setDestinationLocationCode("BCN");
        dto.setDepartureDate(LocalDate.of(2025, 12, 15));
        dto.setAdults(1);
        dto.setCurrencyCode("XYZ"); // Invalid currency
        return dto;
    }

    public static Location locationModel(String airportName, String iataCode, String cityName) {
        Location location = new Location();
        location.setAirportName(airportName);
        location.setIataCode(iataCode);
        location.setCityName(cityName);
        return location;
    }

    public static Address addressModel(String cityName) {
        Address address = new Address();
        address.setCityName(cityName);
        return address;
    }

    public static AirlineInformation airlineModel(String name, String iataCode) {
        AirlineInformation airline = new AirlineInformation();
        airline.setName(name);
        airline.setIataCode(iataCode);
        return airline;
    }

    public static FlightOffer flightOfferModel(String flightId, String currency, String totalPrice) {
        FlightOffer offer = new FlightOffer();
        offer.setFlightOfferId(flightId);
        offer.setCurrency(currency);
        offer.setTotalPrice(totalPrice);
        return offer;
    }
}
