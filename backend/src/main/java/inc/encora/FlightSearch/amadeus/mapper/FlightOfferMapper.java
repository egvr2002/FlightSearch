package inc.encora.FlightSearch.amadeus.mapper;

import inc.encora.FlightSearch.amadeus.dto.common.AmadeusFlightOfferDTO;
import inc.encora.FlightSearch.amadeus.dto.request.AmadeusSearchFlightsRequestDTO;
import inc.encora.FlightSearch.amadeus.dto.request.SearchFlightsDTO;
import inc.encora.FlightSearch.amadeus.model.FlightOffer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ItineraryMapper.class, TravelerPricingMapper.class})
public interface FlightOfferMapper {
    @Mapping(target = "totalPrice", source = "price.total")
    @Mapping(target = "grandTotalPrice", source = "price.grandTotal")
    @Mapping(target = "flightOfferId", source = "id")
    @Mapping(target = "fees", source = "price.fees")
    @Mapping(target = "currency", source = "price.currency")
    @Mapping(target = "basePrice", source = "price.base")
    FlightOffer toFlightOffer(AmadeusFlightOfferDTO amadeusFlightOfferDTO);

    AmadeusSearchFlightsRequestDTO toAmadeusSearchFlightsRequestDTO(SearchFlightsDTO searchFlightsDTO);
}
