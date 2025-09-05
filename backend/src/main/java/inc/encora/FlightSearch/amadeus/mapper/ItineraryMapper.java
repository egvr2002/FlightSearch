package inc.encora.FlightSearch.amadeus.mapper;

import inc.encora.FlightSearch.amadeus.dto.common.AmadeusItineraryDTO;
import inc.encora.FlightSearch.amadeus.model.Itinerary;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.UUID;

@Mapper(componentModel = "spring", uses = {SegmentMapper.class})
public abstract class ItineraryMapper {
    @Mapping(target = "itineraryId", ignore = true)
    @Mapping(target = "initialDepartureDate", ignore = true)
    @Mapping(target = "finalArrivalDate", ignore = true)
    abstract Itinerary toItinerary(AmadeusItineraryDTO amadeusItineraryDTO);

    @AfterMapping
    protected void getInitialDepartureDate(AmadeusItineraryDTO amadeusItineraryDTO, @MappingTarget Itinerary itinerary) {
        itinerary.setInitialDepartureDate(amadeusItineraryDTO.getSegments().getFirst().getDeparture().getAt());
    }

    @AfterMapping
    protected void getFinalArrivalDate(AmadeusItineraryDTO amadeusItineraryDTO, @MappingTarget Itinerary itinerary) {
        itinerary.setFinalArrivalDate(amadeusItineraryDTO.getSegments().getLast().getArrival().getAt());
    }

    @AfterMapping
    protected void setItineraryId(@MappingTarget Itinerary itinerary) {
        itinerary.setItineraryId(UUID.randomUUID().toString());
    }
}
