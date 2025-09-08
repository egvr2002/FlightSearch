package inc.encora.FlightSearch.amadeus.mapper;

import inc.encora.FlightSearch.amadeus.dto.common.*;
import inc.encora.FlightSearch.amadeus.dto.request.AmadeusSearchFlightsRequestDTO;
import inc.encora.FlightSearch.amadeus.dto.request.SearchFlightsDTO;
import inc.encora.FlightSearch.amadeus.model.FlightOffer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FlightOfferMapperTest {

    @Autowired
    private FlightOfferMapper mapper;

    @Test
    void shouldMapAmadeusFlightOfferDTOToFlightOffer() {
        // Given
        AmadeusFlightOfferDTO dto = new AmadeusFlightOfferDTO();
        dto.setId("FLIGHT001");
        dto.setSource("AMADEUS");
        dto.setInstantTicketingRequired(true);

        AmadeusPriceDTO price = new AmadeusPriceDTO();
        price.setCurrency("USD");
        price.setBase("500.00");
        price.setTotal("650.00");
        price.setGrandTotal("670.00");
        dto.setPrice(price);

        // Create itinerary with proper segments (required by ItineraryMapper)
        AmadeusItineraryDTO itinerary = new AmadeusItineraryDTO();
        itinerary.setDuration("PT5H30M");

        AmadeusSegmentDTO segment = new AmadeusSegmentDTO();
        segment.setId("SEG001");
        AmadeusFlightEndPointDTO departure = new AmadeusFlightEndPointDTO();
        departure.setIataCode("JFK");
        departure.setAt(LocalDateTime.of(2025, 9, 15, 8, 0));
        segment.setDeparture(departure);

        AmadeusFlightEndPointDTO arrival = new AmadeusFlightEndPointDTO();
        arrival.setIataCode("LAX");
        arrival.setAt(LocalDateTime.of(2025, 9, 15, 13, 30));
        segment.setArrival(arrival);

        itinerary.setSegments(List.of(segment));
        dto.setItineraries(List.of(itinerary));

        // Create traveler pricing with price
        AmadeusTravelerPricingsDTO travelerPricing = new AmadeusTravelerPricingsDTO();
        travelerPricing.setTravelerId("T001");
        AmadeusTravelerPricingPriceDTO travelerPrice = new AmadeusTravelerPricingPriceDTO();
        travelerPrice.setBase("250.00");
        travelerPrice.setTotal("325.00");
        travelerPricing.setPrice(travelerPrice);
        dto.setTravelerPricings(List.of(travelerPricing));

        // When
        FlightOffer result = mapper.toFlightOffer(dto);

        // Then
        assertNotNull(result);
        assertEquals("FLIGHT001", result.getFlightOfferId()); // Custom mapping: id -> flightOfferId
        assertEquals("USD", result.getCurrency()); // Custom mapping: price.currency -> currency
        assertEquals("500.00", result.getBasePrice()); // Custom mapping: price.base -> basePrice
        assertEquals("650.00", result.getTotalPrice()); // Custom mapping: price.total -> totalPrice
        assertEquals("670.00", result.getGrandTotalPrice()); // Custom mapping: price.grandTotal -> grandTotalPrice
        assertNotNull(result.getItineraries());
        assertEquals(1, result.getItineraries().size());
        assertNotNull(result.getTravelerPricings());
        assertEquals(1, result.getTravelerPricings().size());
    }

    @Test
    void shouldReturnNullWhenFlightOfferDTOIsNull() {
        // When
        FlightOffer result = mapper.toFlightOffer(null);

        // Then
        assertNull(result);
    }

    @Test
    void shouldMapFlightOfferDTOWithNullPriceToFlightOfferWithNullPriceFields() {
        // Given - Test critical nested object mapping
        AmadeusFlightOfferDTO dto = new AmadeusFlightOfferDTO();
        dto.setId("FLIGHT002");
        dto.setPrice(null);

        // When
        FlightOffer result = mapper.toFlightOffer(dto);

        // Then
        assertNotNull(result);
        assertEquals("FLIGHT002", result.getFlightOfferId());
        assertNull(result.getCurrency());
        assertNull(result.getBasePrice());
        assertNull(result.getTotalPrice());
        assertNull(result.getGrandTotalPrice());
        assertNull(result.getFees());
    }

    @Test
    void shouldMapSearchFlightsDTOToAmadeusSearchFlightsRequestDTO() {
        // Given
        SearchFlightsDTO dto = new SearchFlightsDTO();
        dto.setOriginLocationCode("JFK");
        dto.setDestinationLocationCode("LAX");
        dto.setDepartureDate(LocalDate.of(2025, 9, 15));
        dto.setReturnDate(LocalDate.of(2025, 9, 22));
        dto.setAdults(2);
        dto.setCurrencyCode("USD");
        dto.setNonStop(true);
        dto.setMax(10);

        // When
        AmadeusSearchFlightsRequestDTO result = mapper.toAmadeusSearchFlightsRequestDTO(dto);

        // Then
        assertNotNull(result);
        assertEquals("JFK", result.getOriginLocationCode());
        assertEquals("LAX", result.getDestinationLocationCode());
        assertEquals(LocalDate.of(2025, 9, 15), result.getDepartureDate());
        assertEquals(LocalDate.of(2025, 9, 22), result.getReturnDate());
        assertEquals(2, result.getAdults());
        assertEquals("USD", result.getCurrencyCode());
        assertTrue(result.isNonStop());
        assertEquals(10, result.getMax());
    }

    @Test
    void shouldReturnNullWhenSearchFlightsDTOIsNull() {
        // When
        AmadeusSearchFlightsRequestDTO result = mapper.toAmadeusSearchFlightsRequestDTO(null);

        // Then
        assertNull(result);
    }
}
