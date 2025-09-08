package inc.encora.FlightSearch.amadeus.mapper;

import inc.encora.FlightSearch.amadeus.dto.common.AmadeusAmenityDTO;
import inc.encora.FlightSearch.amadeus.model.Amenity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AmenityMapperTest {

    @Autowired
    private AmenityMapper mapper;

    @Test
    void shouldMapAmadeusAmenityDTOToAmenity() {
        AmadeusAmenityDTO dto = new AmadeusAmenityDTO();
        dto.setDescription("In-flight WiFi");
        dto.setChargeable(true);

        Amenity result = mapper.toAmenity(dto);

        assertNotNull(result);
        assertEquals("In-flight WiFi", result.getName());
        assertTrue(result.isChargeable());
    }

    @Test
    void shouldReturnNullWhenAmenityDTOIsNull() {
        Amenity result = mapper.toAmenity(null);
        assertNull(result);
    }
}
