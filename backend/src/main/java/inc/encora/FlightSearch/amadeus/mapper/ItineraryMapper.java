package inc.encora.FlightSearch.amadeus.mapper;

import inc.encora.FlightSearch.amadeus.dto.common.AmadeusItineraryDTO;
import inc.encora.FlightSearch.amadeus.model.Itinerary;
import inc.encora.FlightSearch.amadeus.model.Segment;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
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

    @AfterMapping
    protected void calculateLayoverDurations(@MappingTarget Itinerary itinerary) {
        List<Segment> segments = itinerary.getSegments();
        if (segments == null || segments.size() <= 1) {
            return; // No layovers with 0 or 1 segments
        }

        for (int i = 0; i < segments.size() - 1; i++) {
            Segment currentSegment = segments.get(i);
            Segment nextSegment = segments.get(i + 1);

            LocalDateTime currentArrival = currentSegment.getArrival().getAt();
            LocalDateTime nextDeparture = nextSegment.getDeparture().getAt();

            Duration layoverDuration = Duration.between(currentArrival, nextDeparture);

            currentSegment.setLayoverDuration(layoverDuration.toString());
        }

        // Last segment has no layover
        segments.getLast().setLayoverDuration(null);
    }
}
