package inc.encora.FlightSearch.amadeus.mapper;

import inc.encora.FlightSearch.amadeus.dto.common.AmadeusFlightEndPointDTO;
import inc.encora.FlightSearch.amadeus.dto.common.AmadeusItineraryDTO;
import inc.encora.FlightSearch.amadeus.dto.common.AmadeusSegmentDTO;
import inc.encora.FlightSearch.amadeus.model.Itinerary;
import inc.encora.FlightSearch.amadeus.model.Segment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItineraryMapperTest {

    @Autowired
    private ItineraryMapper mapper;

    @Test
    void shouldMapAmadeusItineraryDTOToItineraryAndExecuteCustomLogic() {
        // Given
        AmadeusItineraryDTO dto = new AmadeusItineraryDTO();
        dto.setDuration("PT5H30M");

        // Create segments with realistic times
        AmadeusSegmentDTO segment1 = createSegment("seg1", "JFK", "2025-09-07T08:00", "ORD", "2025-09-07T10:00");
        AmadeusSegmentDTO segment2 = createSegment("seg2", "ORD", "2025-09-07T11:30", "LAX", "2025-09-07T13:30");

        dto.setSegments(List.of(segment1, segment2));

        // When
        Itinerary result = mapper.toItinerary(dto);

        // Then - Basic mapping
        assertNotNull(result);
        assertEquals("PT5H30M", result.getDuration());
        assertEquals(2, result.getSegments().size());

        // Then - Custom @AfterMapping logic verification

        assertNotNull(result.getItineraryId());
        assertTrue(result.getItineraryId().length() > 0);

        assertEquals(LocalDateTime.of(2025, 9, 7, 8, 0), result.getInitialDepartureDate());
        assertEquals(LocalDateTime.of(2025, 9, 7, 13, 30), result.getFinalArrivalDate());

        Segment firstSegment = result.getSegments().get(0);
        Segment secondSegment = result.getSegments().get(1);

        assertEquals("PT1H30M", firstSegment.getLayoverDuration());
        assertNull(secondSegment.getLayoverDuration());
    }

    @Test
    void shouldCalculateNoLayoverForSingleSegmentItinerary() {
        // Given
        AmadeusItineraryDTO dto = new AmadeusItineraryDTO();
        dto.setDuration("PT3H");

        AmadeusSegmentDTO segment = createSegment("seg1", "JFK", "2025-09-07T08:00", "LAX", "2025-09-07T11:00");
        dto.setSegments(List.of(segment));

        // When
        Itinerary result = mapper.toItinerary(dto);

        // Then
        assertNotNull(result);
        assertEquals(1, result.getSegments().size());

        // Verify custom logic for single segment
        assertEquals(LocalDateTime.of(2025, 9, 7, 8, 0), result.getInitialDepartureDate());
        assertEquals(LocalDateTime.of(2025, 9, 7, 11, 0), result.getFinalArrivalDate());

        // Single segment should have no layover
        assertNull(result.getSegments().get(0).getLayoverDuration());
    }

    @Test
    void shouldCalculateMultipleLayoversForThreeSegmentItinerary() {
        // Given
        AmadeusItineraryDTO dto = new AmadeusItineraryDTO();

        AmadeusSegmentDTO segment1 = createSegment("seg1", "JFK", "2025-09-07T08:00", "ORD", "2025-09-07T10:00");
        AmadeusSegmentDTO segment2 = createSegment("seg2", "ORD", "2025-09-07T11:00", "DEN", "2025-09-07T12:00");
        AmadeusSegmentDTO segment3 = createSegment("seg3", "DEN", "2025-09-07T14:00", "LAX", "2025-09-07T15:00");

        dto.setSegments(List.of(segment1, segment2, segment3));

        // When
        Itinerary result = mapper.toItinerary(dto);

        // Then
        assertEquals(3, result.getSegments().size());

        // Verify layover calculations
        assertEquals("PT1H", result.getSegments().get(0).getLayoverDuration()); // 10:00 to 11:00
        assertEquals("PT2H", result.getSegments().get(1).getLayoverDuration()); // 12:00 to 14:00
        assertNull(result.getSegments().get(2).getLayoverDuration()); // Last segment
    }

    @Test
    void shouldReturnNullWhenItineraryDTOIsNull() {
        // When
        Itinerary result = mapper.toItinerary(null);

        // Then
        assertNull(result);
    }

    private AmadeusSegmentDTO createSegment(String id, String depIata, String depTime, String arrIata, String arrTime) {
        AmadeusSegmentDTO segment = new AmadeusSegmentDTO();
        segment.setId(id);

        AmadeusFlightEndPointDTO departure = new AmadeusFlightEndPointDTO();
        departure.setIataCode(depIata);
        departure.setAt(LocalDateTime.parse(depTime));
        segment.setDeparture(departure);

        AmadeusFlightEndPointDTO arrival = new AmadeusFlightEndPointDTO();
        arrival.setIataCode(arrIata);
        arrival.setAt(LocalDateTime.parse(arrTime));
        segment.setArrival(arrival);

        return segment;
    }
}