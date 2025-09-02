package inc.encora.FlightSearch.amadeus.mapper;

import inc.encora.FlightSearch.amadeus.dto.common.AmadeusFareDetailsBySegmentDTO;
import inc.encora.FlightSearch.amadeus.model.FareDetailsBySegment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { AmenityMapper.class })
public interface FareDetailsBySegmentMapper {
  FareDetailsBySegment toFareDetailsBySegment(AmadeusFareDetailsBySegmentDTO travelerPricingsDTO);
}
