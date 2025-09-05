package inc.encora.FlightSearch.amadeus.mapper;

import inc.encora.FlightSearch.amadeus.dto.common.AmadeusAddressDTO;
import inc.encora.FlightSearch.amadeus.dto.common.AmadeusLocationDTO;
import inc.encora.FlightSearch.amadeus.dto.request.AmadeusSearchLocationsRequestDTO;
import inc.encora.FlightSearch.amadeus.dto.request.SearchLocationsDTO;
import inc.encora.FlightSearch.amadeus.model.Address;
import inc.encora.FlightSearch.amadeus.model.Location;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LocationMapper {
    @Mapping(target = "cityName", source = "address.cityName")
    @Mapping(target = "airportName", source = "name")
    Location toLocation(AmadeusLocationDTO locationDTO);

    AmadeusSearchLocationsRequestDTO toAmadeusSearchLocationsRequest(SearchLocationsDTO amadeusSearchLocationsRequestDTO);

    Address toAddress(AmadeusAddressDTO amadeusAddressDTO);
}
