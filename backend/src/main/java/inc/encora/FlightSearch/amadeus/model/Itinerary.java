package inc.encora.FlightSearch.amadeus.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Itinerary {
    private String duration;
    private List<Segment> segments;
    private LocalDateTime initialDepartureDate;
    private LocalDateTime finalArrivalDate;
}
