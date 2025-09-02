package inc.encora.FlightSearch.amadeus.dto.common;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class AmadeusFlightOfferDTO {
    private String type;
    private String id;
    private String source;
    private boolean instantTicketingRequired;
    private boolean nonHomogeneous;
    private boolean oneWay;
    private boolean isUpsellOffer;
    private LocalDate lastTicketingDate;
    private LocalDate lastTicketingDateTime;
    private long numberOfBookableSeats;
    private List<AmadeusItineraryDTO> itineraries;
    private AmadeusPriceDTO price;
    private AmadeusPricingOptionsDTO pricingOptions;
    private List<String> validatingAirlineCodes;
    private List<AmadeusTravelerPricingsDTO> travelerPricings;
}
