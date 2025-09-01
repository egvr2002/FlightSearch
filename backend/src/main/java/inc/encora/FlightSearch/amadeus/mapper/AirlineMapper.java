package inc.encora.FlightSearch.amadeus.mapper;

import inc.encora.FlightSearch.amadeus.dto.common.AmadeusAirlineInformationDTO;
import inc.encora.FlightSearch.amadeus.model.AirlineInformation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AirlineMapper {
    @Mapping(target = "name", source = "businessName")
    AirlineInformation toAirlineInformation(AmadeusAirlineInformationDTO airlineInformationDTO);
}
