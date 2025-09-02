package inc.encora.FlightSearch.amadeus.mapper;

import inc.encora.FlightSearch.amadeus.dto.common.AmadeusAmenityDTO;
import inc.encora.FlightSearch.amadeus.model.Amenity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AmenityMapper {
  @Mapping(target = "name", source = "description")
  Amenity toAmenity(AmadeusAmenityDTO amenityDTO);
}
