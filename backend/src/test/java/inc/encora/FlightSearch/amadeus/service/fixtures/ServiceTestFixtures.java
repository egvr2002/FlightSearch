package inc.encora.FlightSearch.amadeus.service.fixtures;

import inc.encora.FlightSearch.amadeus.dto.common.AmadeusAddressDTO;
import inc.encora.FlightSearch.amadeus.dto.common.AmadeusAirlineInformationDTO;
import inc.encora.FlightSearch.amadeus.dto.common.AmadeusFlightOfferDTO;
import inc.encora.FlightSearch.amadeus.dto.common.AmadeusLocationDTO;
import inc.encora.FlightSearch.amadeus.dto.request.AmadeusSearchFlightsRequestDTO;
import inc.encora.FlightSearch.amadeus.dto.request.AmadeusSearchLocationsRequestDTO;
import inc.encora.FlightSearch.amadeus.model.Address;
import inc.encora.FlightSearch.amadeus.model.AirlineInformation;
import inc.encora.FlightSearch.amadeus.model.FlightOffer;
import inc.encora.FlightSearch.amadeus.model.Location;

public class ServiceTestFixtures {
    public static AmadeusLocationDTO locationDTO(String name) {
        AmadeusLocationDTO dto = new AmadeusLocationDTO();
        dto.setName(name);
        AmadeusAddressDTO address = new AmadeusAddressDTO();
        address.setCityName(name);
        dto.setAddress(address);
        return dto;
    }

    public static Location locationModel(String airportName) {
        Location l = new Location();
        l.setAirportName(airportName);
        return l;
    }

    public static AmadeusAirlineInformationDTO airlineDTO(String name) {
        AmadeusAirlineInformationDTO dto = new AmadeusAirlineInformationDTO();
        dto.setBusinessName(name);
        return dto;
    }

    public static AirlineInformation airlineModel(String name) {
        AirlineInformation a = new AirlineInformation();
        a.setName(name);
        return a;
    }

    public static AmadeusFlightOfferDTO flightOfferDTO() {
        return new AmadeusFlightOfferDTO();
    }

    public static FlightOffer flightOfferModel(String totalPrice) {
        FlightOffer f = new FlightOffer();
        f.setTotalPrice(totalPrice);
        return f;
    }

    public static Address addressModel(String cityName) {
        Address a = new Address();
        a.setCityName(cityName);
        return a;
    }

    public static AmadeusSearchLocationsRequestDTO amadeusSearchLocationsRequest(
            String keyword) {
        return AmadeusSearchLocationsRequestDTO.builder()
                .keyword(keyword)
                .build();
    }

    public static AmadeusSearchFlightsRequestDTO amadeusSearchFlightsRequest() {
        AmadeusSearchFlightsRequestDTO r = new AmadeusSearchFlightsRequestDTO();
        r.setOriginLocationCode("MAD");
        r.setDestinationLocationCode("LON");
        r.setAdults(1);
        r.setNonStop(false);
        r.setMax(5);
        r.setCurrencyCode("EUR");
        return r;
    }
}
