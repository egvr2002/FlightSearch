package inc.encora.FlightSearch.amadeus.mapper;

import inc.encora.FlightSearch.amadeus.dto.common.AmadeusSegmentDTO;
import inc.encora.FlightSearch.amadeus.model.Segment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SegmentMapper {
  @Mapping(target = "segmentId", source = "id")
  @Mapping(target = "operatingCarrierCode", source = "operating.carrierCode")
  @Mapping(target = "aircraftCode", source = "aircraft.code")
  Segment toSegment(AmadeusSegmentDTO amadeusSegmentDTO);
}
