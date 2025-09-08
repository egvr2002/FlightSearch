package inc.encora.FlightSearch.amadeus.mapper;

import inc.encora.FlightSearch.amadeus.dto.common.AmadeusAirlineInformationDTO;
import inc.encora.FlightSearch.amadeus.model.AirlineInformation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AirlineMapperTest {

    @Autowired
    private AirlineMapper mapper;

    @Test
    void shouldMapAmadeusAirlineInformationDTOToAirlineInformation() {
        AmadeusAirlineInformationDTO dto = new AmadeusAirlineInformationDTO();
        dto.setBusinessName("Acme Air");
        dto.setIataCode("AC");

        AirlineInformation result = mapper.toAirlineInformation(dto);

        assertNotNull(result);
        assertEquals("Acme Air", result.getName());
        assertEquals("AC", result.getIataCode());
    }

    @Test
    void shouldReturnNullWhenAirlineInformationDTOIsNull() {
        AirlineInformation result = mapper.toAirlineInformation(null);
        assertNull(result);
    }
}
