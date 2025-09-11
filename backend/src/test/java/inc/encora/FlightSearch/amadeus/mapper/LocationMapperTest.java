package inc.encora.FlightSearch.amadeus.mapper;

import inc.encora.FlightSearch.amadeus.dto.common.AmadeusAddressDTO;
import inc.encora.FlightSearch.amadeus.dto.common.AmadeusLocationDTO;
import inc.encora.FlightSearch.amadeus.dto.request.AmadeusSearchLocationsRequestDTO;
import inc.encora.FlightSearch.amadeus.dto.request.SearchLocationsDTO;
import inc.encora.FlightSearch.amadeus.model.Address;
import inc.encora.FlightSearch.amadeus.model.Location;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LocationMapperTest {

    @Autowired
    private LocationMapper mapper;

    @Test
    void shouldMapAmadeusLocationDTOToLocation() {
        // Given
        AmadeusLocationDTO dto = new AmadeusLocationDTO();
        dto.setName("John F. Kennedy International Airport");
        dto.setIataCode("JFK");

        AmadeusAddressDTO address = new AmadeusAddressDTO();
        address.setCityName("New York");
        dto.setAddress(address);

        // When
        Location result = mapper.toLocation(dto);

        // Then
        assertNotNull(result);
        assertEquals("John F. Kennedy International Airport", result.getAirportName());
        assertEquals("JFK", result.getIataCode());
        assertEquals("New York", result.getCityName());
    }

    @Test
    void shouldReturnNullWhenLocationDTOIsNull() {
        // When
        Location result = mapper.toLocation(null);

        // Then
        assertNull(result);
    }

    @Test
    void shouldMapLocationDTOWithNullAddressToLocationWithNullCityName() {
        // Given - Test critical nested object mapping
        AmadeusLocationDTO dto = new AmadeusLocationDTO();
        dto.setName("LaGuardia Airport");
        dto.setIataCode("LGA");
        dto.setAddress(null);

        // When
        Location result = mapper.toLocation(dto);

        // Then
        assertNotNull(result);
        assertEquals("LaGuardia Airport", result.getAirportName());
        assertEquals("LGA", result.getIataCode());
        assertNull(result.getCityName());
    }

    @Test
    void shouldMapSearchLocationsDTOToAmadeusSearchLocationsRequest() {
        // Given
        SearchLocationsDTO dto = new SearchLocationsDTO();
        dto.setKeyword("NewYork");
        dto.setSubType("AIRPORT");
        dto.setPageLimit(5);
        dto.setPageOffset(10);

        // When
        AmadeusSearchLocationsRequestDTO result = mapper.toAmadeusSearchLocationsRequest(dto);

        // Then
        assertNotNull(result);
        assertEquals("NewYork", result.getKeyword());
        assertEquals(5, result.getPageLimit());
        assertEquals(10, result.getPageOffset());
    }

    @Test
    void shouldMapAmadeusAddressDTOToAddress() {
        // Given
        AmadeusAddressDTO dto = new AmadeusAddressDTO();
        dto.setCityName("Los Angeles");

        // When
        Address result = mapper.toAddress(dto);

        // Then
        assertNotNull(result);
        assertEquals("Los Angeles", result.getCityName());
    }

    @Test
    void shouldReturnNullWhenAddressDTOIsNull() {
        // When
        Address result = mapper.toAddress(null);

        // Then
        assertNull(result);
    }
}