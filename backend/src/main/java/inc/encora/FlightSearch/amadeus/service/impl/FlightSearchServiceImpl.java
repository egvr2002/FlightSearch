package inc.encora.FlightSearch.amadeus.service.impl;

import inc.encora.FlightSearch.amadeus.client.AmadeusFlightOffersClient;
import inc.encora.FlightSearch.amadeus.client.AmadeusLocationsClient;
import inc.encora.FlightSearch.amadeus.dto.common.AmadeusLocationDTO;
import inc.encora.FlightSearch.amadeus.dto.request.*;
import inc.encora.FlightSearch.amadeus.dto.response.AmadeusListResponseDTO;
import inc.encora.FlightSearch.amadeus.mapper.AirlineMapper;
import inc.encora.FlightSearch.amadeus.mapper.FlightOfferMapper;
import inc.encora.FlightSearch.amadeus.mapper.LocationMapper;
import inc.encora.FlightSearch.amadeus.model.Address;
import inc.encora.FlightSearch.amadeus.model.AirlineInformation;
import inc.encora.FlightSearch.amadeus.model.FlightOffer;
import inc.encora.FlightSearch.amadeus.model.Location;
import inc.encora.FlightSearch.amadeus.service.FlightSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightSearchServiceImpl implements FlightSearchService {
    private final AmadeusLocationsClient amadeusLocationsClient;

    private final AmadeusFlightOffersClient amadeusFlightOffersClient;

    private final LocationMapper locationMapper;

    private final AirlineMapper airlineMapper;

    private final FlightOfferMapper flightOfferMapper;

    @Override
    public List<Location> searchLocations(SearchLocationsDTO searchLocationsDTO) {
        AmadeusSearchLocationsRequestDTO requestDTO = locationMapper
                .toAmadeusSearchLocationsRequest(searchLocationsDTO);
        return amadeusLocationsClient.searchLocations(requestDTO)
                .map(AmadeusListResponseDTO::getData)
                .flatMapMany(Flux::fromIterable)
                .map(locationMapper::toLocation)
                .collectList()
                .block();
    }

    @Override
    public Address searchCityByIATACode(String iataCode) {
        AmadeusSearchLocationsRequestDTO requestDTO = AmadeusSearchLocationsRequestDTO.builder()
                .keyword(iataCode)
                .pageLimit(1)
                .build();
        return amadeusLocationsClient.searchLocations(requestDTO)
                .map(AmadeusListResponseDTO::getData)
                .map(List::getFirst)
                .map(AmadeusLocationDTO::getAddress)
                .map(locationMapper::toAddress)
                .block();
    }

    @Override
    public AirlineInformation searchAirlineInformation(String airlineCode) {
        AmadeusAirlinesLookupRequestDTO requestDTO = new AmadeusAirlinesLookupRequestDTO(airlineCode);
        return amadeusLocationsClient.searchAirlineInformation(requestDTO)
                .map(AmadeusListResponseDTO::getData)
                .map(List::getFirst)
                .map(airlineMapper::toAirlineInformation)
                .block();
    }

    @Override
    public List<FlightOffer> searchFlightOffers(SearchFlightsDTO searchFlightsDTO) {
        AmadeusSearchFlightsRequestDTO requestDTO = flightOfferMapper
                .toAmadeusSearchFlightsRequestDTO(searchFlightsDTO);
        return amadeusFlightOffersClient.searchFlightOffers(requestDTO)
                .map(AmadeusListResponseDTO::getData)
                .flatMapMany(Flux::fromIterable)
                .map(flightOfferMapper::toFlightOffer)
                .collectList()
                .block();
    }
}
