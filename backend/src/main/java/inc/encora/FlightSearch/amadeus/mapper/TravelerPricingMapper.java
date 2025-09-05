package inc.encora.FlightSearch.amadeus.mapper;

import inc.encora.FlightSearch.amadeus.dto.common.AmadeusTravelerPricingsDTO;
import inc.encora.FlightSearch.amadeus.model.TravelerPricing;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {FareDetailsBySegmentMapper.class})
public interface TravelerPricingMapper {
    @Mapping(target = "basePrice", source = "price.base")
    @Mapping(target = "totalPrice", source = "price.total")
    TravelerPricing toTravelerPricing(AmadeusTravelerPricingsDTO travelerPricingsDTO);
}
