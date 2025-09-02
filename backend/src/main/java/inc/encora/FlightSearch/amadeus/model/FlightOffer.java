package inc.encora.FlightSearch.amadeus.model;

import lombok.Data;

import java.util.List;

@Data
public class FlightOffer {
    private String flightOfferId;
    private List<Itinerary> itineraries;
    private String currency;
    private String totalPrice;
    private String basePrice;
    private String grandTotalPrice;
    private List<Fee> fees;
    private List<TravelerPricing> travelerPricings;
}
