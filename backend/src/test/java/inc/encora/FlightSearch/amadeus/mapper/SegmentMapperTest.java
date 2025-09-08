package inc.encora.FlightSearch.amadeus.mapper;

import inc.encora.FlightSearch.amadeus.dto.common.*;
import inc.encora.FlightSearch.amadeus.model.Segment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SegmentMapperTest {

    @Autowired
    private SegmentMapper mapper;

    @Test
    void shouldMapAmadeusSegmentDTOToSegment() {
        AmadeusSegmentDTO dto = new AmadeusSegmentDTO();
        AmadeusFlightEndPointDTO dep = new AmadeusFlightEndPointDTO();
        dep.setIataCode("JFK");
        dep.setAt(LocalDateTime.of(2025, 9, 7, 12, 30));
        AmadeusFlightEndPointDTO arr = new AmadeusFlightEndPointDTO();
        arr.setIataCode("LAX");
        arr.setAt(LocalDateTime.of(2025, 9, 7, 15, 45));

        AmadeusSegmentAircraftDTO aircraft = new AmadeusSegmentAircraftDTO();
        aircraft.setCode("A320");

        AmadeusOperatingAirlineDTO operating = new AmadeusOperatingAirlineDTO();
        operating.setCarrierCode("OP");

        AmadeusFlightStopDTO stop = new AmadeusFlightStopDTO();
        stop.setIataCode("ORD");
        stop.setDuration("PT1H");

        dto.setDeparture(dep);
        dto.setArrival(arr);
        dto.setAircraft(aircraft);
        dto.setOperating(operating);
        dto.setId("seg-123");
        dto.setCarrierCode("CC");
        dto.setNumber("42");
        dto.setNumberOfStops(1);
        dto.setStops(List.of(stop));

        Segment result = mapper.toSegment(dto);

        assertNotNull(result);
        assertEquals("seg-123", result.getSegmentId());
        assertEquals("OP", result.getOperatingCarrierCode());
        assertEquals("A320", result.getAircraftCode());
        assertEquals("JFK", result.getDeparture().getIataCode());
        assertEquals("LAX", result.getArrival().getIataCode());
        assertEquals("CC", result.getCarrierCode());
        assertEquals("42", result.getNumber());
        assertEquals(1, result.getNumberOfStops());
        assertNotNull(result.getStops());
        assertEquals(1, result.getStops().size());
        assertEquals("ORD", result.getStops().get(0).getIataCode());
    }

    @Test
    void shouldReturnNullWhenSegmentDTOIsNull() {
        Segment result = mapper.toSegment(null);
        assertNull(result);
    }
}
