package inc.encora.FlightSearch.amadeus.mapper;

import inc.encora.FlightSearch.amadeus.dto.common.AmadeusAddressDTO;
import inc.encora.FlightSearch.amadeus.dto.common.AmadeusLocationDTO;
import inc.encora.FlightSearch.amadeus.dto.request.AmadeusSearchLocationsRequestDTO;
import inc.encora.FlightSearch.amadeus.dto.request.SearchLocationsDTO;
import inc.encora.FlightSearch.amadeus.model.Address;
import inc.encora.FlightSearch.amadeus.model.Location;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LocationMapper {
    Location toLocation(AmadeusLocationDTO locationDTO);

    AmadeusSearchLocationsRequestDTO toAmadeusSearchLocationsRequest(SearchLocationsDTO amadeusSearchLocationsRequestDTO);

    Address toAddress(AmadeusAddressDTO amadeusAddressDTO);
}
