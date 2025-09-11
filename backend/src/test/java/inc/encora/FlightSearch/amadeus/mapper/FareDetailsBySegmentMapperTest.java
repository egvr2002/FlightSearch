package inc.encora.FlightSearch.amadeus.mapper;

import inc.encora.FlightSearch.amadeus.dto.common.AmadeusAmenityDTO;
import inc.encora.FlightSearch.amadeus.dto.common.AmadeusFareDetailsBySegmentDTO;
import inc.encora.FlightSearch.amadeus.model.FareDetailsBySegment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FareDetailsBySegmentMapperTest {

    @Autowired
    private FareDetailsBySegmentMapper mapper;

    @Test
    void shouldMapAmadeusFareDetailsBySegmentDTOToFareDetailsBySegment() {
        // Given
        AmadeusFareDetailsBySegmentDTO dto = new AmadeusFareDetailsBySegmentDTO();
        dto.setSegmentId("SEG001");
        dto.setCabin("ECONOMY");
        dto.setFareBasis("K1X0C3");
        dto.setBrandedFare("BASIC");
        dto.setBookingClass("K");

        AmadeusAmenityDTO amenity = new AmadeusAmenityDTO();
        amenity.setDescription("In-flight WiFi");
        amenity.setChargeable(true);
        dto.setAmenities(List.of(amenity));

        // When
        FareDetailsBySegment result = mapper.toFareDetailsBySegment(dto);

        // Then
        assertNotNull(result);
        assertEquals("SEG001", result.getSegmentId());
        assertEquals("ECONOMY", result.getCabin());
        assertEquals("K", result.getBookingClass());
        assertNotNull(result.getAmenities());
        assertEquals(1, result.getAmenities().size());
        assertEquals("In-flight WiFi", result.getAmenities().get(0).getName());
        assertTrue(result.getAmenities().get(0).isChargeable());
    }

    @Test
    void shouldReturnNullWhenFareDetailsBySegmentDTOIsNull() {
        // When
        FareDetailsBySegment result = mapper.toFareDetailsBySegment(null);

        // Then
        assertNull(result);
    }

    @Test
    void shouldMapFareDetailsBySegmentDTOWithNullAmenitiesToFareDetailsBySegmentWithNullAmenities() {
        // Given - Test critical nested object mapping
        AmadeusFareDetailsBySegmentDTO dto = new AmadeusFareDetailsBySegmentDTO();
        dto.setSegmentId("SEG002");
        dto.setCabin("BUSINESS");
        dto.setAmenities(null);

        // When
        FareDetailsBySegment result = mapper.toFareDetailsBySegment(dto);

        // Then
        assertNotNull(result);
        assertEquals("SEG002", result.getSegmentId());
        assertEquals("BUSINESS", result.getCabin());
        assertNull(result.getAmenities());
    }
}
