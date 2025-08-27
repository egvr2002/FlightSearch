package inc.encora.FlightSearch.amadeus.service.impl;

import inc.encora.FlightSearch.amadeus.client.AmadeusLocationsClient;
import inc.encora.FlightSearch.amadeus.dto.common.AmadeusLocationDTO;
import inc.encora.FlightSearch.amadeus.dto.request.AmadeusSearchLocationsRequestDTO;
import inc.encora.FlightSearch.amadeus.dto.request.SearchLocationsDTO;
import inc.encora.FlightSearch.amadeus.dto.response.AmadeusListResponseDTO;
import inc.encora.FlightSearch.amadeus.mapper.LocationMapper;
import inc.encora.FlightSearch.amadeus.model.Address;
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

    private final LocationMapper locationMapper;

    @Override
    public List<Location> searchLocations(SearchLocationsDTO searchLocationsDTO) {
        AmadeusSearchLocationsRequestDTO requestDTO = locationMapper.toAmadeusSearchLocationsRequest(searchLocationsDTO);
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
                .doOnNext(System.out::println)
                .map(List::getFirst)
                .map(AmadeusLocationDTO::getAddress)
                .map(locationMapper::toAddress)
                .block();
    }
}
