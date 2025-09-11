package inc.encora.FlightSearch.amadeus.mapper;

import inc.encora.FlightSearch.amadeus.dto.common.AmadeusFareDetailsBySegmentDTO;
import inc.encora.FlightSearch.amadeus.dto.common.AmadeusTravelerPricingPriceDTO;
import inc.encora.FlightSearch.amadeus.dto.common.AmadeusTravelerPricingsDTO;
import inc.encora.FlightSearch.amadeus.model.TravelerPricing;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TravelerPricingMapperTest {

    @Autowired
    private TravelerPricingMapper mapper;

    @Test
    void shouldMapAmadeusTravelerPricingsDTOToTravelerPricing() {
        // Given
        AmadeusTravelerPricingsDTO dto = new AmadeusTravelerPricingsDTO();
        dto.setTravelerId("T001");
        dto.setFareOption("STANDARD");
        dto.setTravelerType("ADULT");

        AmadeusTravelerPricingPriceDTO price = new AmadeusTravelerPricingPriceDTO();
        price.setCurrency("USD");
        price.setBase("250.00");
        price.setTotal("275.50");
        dto.setPrice(price);

        AmadeusFareDetailsBySegmentDTO fareDetails = new AmadeusFareDetailsBySegmentDTO();
        fareDetails.setSegmentId("SEG001");
        fareDetails.setCabin("ECONOMY");
        dto.setFareDetailsBySegment(List.of(fareDetails));

        // When
        TravelerPricing result = mapper.toTravelerPricing(dto);

        // Then
        assertNotNull(result);
        assertEquals("T001", result.getTravelerId());
        assertEquals("250.00", result.getBasePrice());
        assertEquals("275.50", result.getTotalPrice());
        assertNotNull(result.getFareDetailsBySegment());
        assertEquals(1, result.getFareDetailsBySegment().size());
        assertEquals("SEG001", result.getFareDetailsBySegment().get(0).getSegmentId());
        assertEquals("ECONOMY", result.getFareDetailsBySegment().get(0).getCabin());
    }

    @Test
    void shouldReturnNullWhenTravelerPricingsDTOIsNull() {
        // When
        TravelerPricing result = mapper.toTravelerPricing(null);

        // Then
        assertNull(result);
    }

    @Test
    void shouldMapTravelerPricingsDTOWithNullPriceToTravelerPricingWithNullPrices() {
        // Given
        AmadeusTravelerPricingsDTO dto = new AmadeusTravelerPricingsDTO();
        dto.setTravelerId("T004");
        dto.setPrice(null);

        // When
        TravelerPricing result = mapper.toTravelerPricing(dto);

        // Then
        assertNotNull(result);
        assertEquals("T004", result.getTravelerId());
        assertNull(result.getBasePrice());
        assertNull(result.getTotalPrice());
    }
}
