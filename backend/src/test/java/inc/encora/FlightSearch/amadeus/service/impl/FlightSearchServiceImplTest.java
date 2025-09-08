package inc.encora.FlightSearch.amadeus.service.impl;

import inc.encora.FlightSearch.amadeus.client.AmadeusFlightOffersClient;
import inc.encora.FlightSearch.amadeus.client.AmadeusLocationsClient;
import inc.encora.FlightSearch.amadeus.dto.common.AmadeusAirlineInformationDTO;
import inc.encora.FlightSearch.amadeus.dto.common.AmadeusFlightOfferDTO;
import inc.encora.FlightSearch.amadeus.dto.common.AmadeusLocationDTO;
import inc.encora.FlightSearch.amadeus.dto.request.SearchFlightsDTO;
import inc.encora.FlightSearch.amadeus.dto.request.SearchLocationsDTO;
import inc.encora.FlightSearch.amadeus.dto.response.AmadeusListResponseDTO;
import inc.encora.FlightSearch.amadeus.mapper.AirlineMapper;
import inc.encora.FlightSearch.amadeus.mapper.FlightOfferMapper;
import inc.encora.FlightSearch.amadeus.mapper.LocationMapper;
import inc.encora.FlightSearch.amadeus.model.Address;
import inc.encora.FlightSearch.amadeus.model.AirlineInformation;
import inc.encora.FlightSearch.amadeus.model.FlightOffer;
import inc.encora.FlightSearch.amadeus.model.Location;
import inc.encora.FlightSearch.amadeus.service.fixtures.ServiceTestFixtures;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FlightSearchServiceImplTest {

    @Mock
    private AmadeusLocationsClient locationsClient;

    @Mock
    private AmadeusFlightOffersClient offersClient;

    @Mock
    private LocationMapper locationMapper;

    @Mock
    private AirlineMapper airlineMapper;

    @Mock
    private FlightOfferMapper flightOfferMapper;

    private FlightSearchServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new FlightSearchServiceImpl(locationsClient, offersClient, locationMapper, airlineMapper,
                flightOfferMapper);
    }

    @Test
    void shouldSearchLocations() {
        // Given
        SearchLocationsDTO request = new SearchLocationsDTO();
        var dto = ServiceTestFixtures.locationDTO("Madrid");
        AmadeusListResponseDTO<AmadeusLocationDTO> response = new AmadeusListResponseDTO<>();
        response.setData(List.of(dto));

        Location mapped = ServiceTestFixtures.locationModel("Madrid Airport");

        var expectedRequest = ServiceTestFixtures.amadeusSearchLocationsRequest("Madrid");
        when(locationMapper.toAmadeusSearchLocationsRequest(any())).thenReturn(expectedRequest);
        when(locationsClient.searchLocations(any())).thenReturn(Mono.just(response));
        when(locationMapper.toLocation(any())).thenReturn(mapped);

        // When
        List<Location> result = service.searchLocations(request);

        // Then
        assertThat(result).isNotNull().hasSize(1);
        assertThat(result.get(0).getAirportName()).isEqualTo(mapped.getAirportName());
    }

    @Test
    void shouldSearchCityByIATACode() {
        // Given
        String iata = "MAD";
        var dto = ServiceTestFixtures.locationDTO("Madrid");
        AmadeusListResponseDTO<AmadeusLocationDTO> response = new AmadeusListResponseDTO<>();
        response.setData(List.of(dto));

        Address mapped = ServiceTestFixtures.addressModel("Madrid");

        when(locationsClient.searchLocations(any())).thenReturn(Mono.just(response));
        when(locationMapper.toAddress(any())).thenReturn(mapped);

        // When
        Address result = service.searchCityByIATACode(iata);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getCityName()).isEqualTo(mapped.getCityName());
    }

    @Test
    void shouldThrowWhenCityNotFound() {
        AmadeusListResponseDTO<AmadeusLocationDTO> emptyResponse = new AmadeusListResponseDTO<>();
        emptyResponse.setData(List.of());
        when(locationsClient.searchLocations(any())).thenReturn(Mono.just(emptyResponse));

        assertThrows(NoSuchElementException.class, () -> service.searchCityByIATACode("XXX"));
    }

    @Test
    void shouldSearchAirlineInformation() {
        // Given
        var airlineDTO = ServiceTestFixtures.airlineDTO("British Airways");
        AmadeusListResponseDTO<AmadeusAirlineInformationDTO> response = new AmadeusListResponseDTO<>();
        response.setData(List.of(airlineDTO));

        AirlineInformation mapped = ServiceTestFixtures.airlineModel("British Airways");

        when(locationsClient.searchAirlineInformation(any())).thenReturn(Mono.just(response));
        when(airlineMapper.toAirlineInformation(any())).thenReturn(mapped);

        // When
        AirlineInformation result = service.searchAirlineInformation("BA");

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo(mapped.getName());
    }

    @Test
    void shouldSearchFlightOffers() {
        // Given
        SearchFlightsDTO searchFlightsDTO = new SearchFlightsDTO();
        var offerDTO = ServiceTestFixtures.flightOfferDTO();
        AmadeusListResponseDTO<AmadeusFlightOfferDTO> response = new AmadeusListResponseDTO<>();
        response.setData(List.of(offerDTO));

        FlightOffer mapped = ServiceTestFixtures.flightOfferModel("100.00");

        var expectedFlightsRequest = ServiceTestFixtures.amadeusSearchFlightsRequest();
        when(flightOfferMapper.toAmadeusSearchFlightsRequestDTO(any())).thenReturn(expectedFlightsRequest);
        when(offersClient.searchFlightOffers(any())).thenReturn(Mono.just(response));
        when(flightOfferMapper.toFlightOffer(any())).thenReturn(mapped);

        // When
        List<FlightOffer> result = service.searchFlightOffers(searchFlightsDTO);

        // Then
        assertThat(result).isNotNull().hasSize(1);
        assertThat(result.get(0).getTotalPrice()).isEqualTo(mapped.getTotalPrice());
    }

    @Test
    void shouldVerifyMapperCalledWithSearchLocationsDTO() {
        // Given
        SearchLocationsDTO request = new SearchLocationsDTO();
        var expectedRequest = ServiceTestFixtures.amadeusSearchLocationsRequest("");
        when(locationMapper.toAmadeusSearchLocationsRequest(any())).thenReturn(expectedRequest);
        AmadeusListResponseDTO<AmadeusLocationDTO> response = new AmadeusListResponseDTO<>();
        response.setData(List.of());
        when(locationsClient.searchLocations(any())).thenReturn(Mono.just(response));

        // When
        service.searchLocations(request);

        // Then
        ArgumentCaptor<SearchLocationsDTO> captor = ArgumentCaptor.forClass(SearchLocationsDTO.class);
        verify(locationMapper).toAmadeusSearchLocationsRequest(captor.capture());
    }

    @Test
    void shouldVerifyMapperCalledWithSearchFlightsDTO() {
        // Given
        SearchFlightsDTO request = new SearchFlightsDTO();
        var expectedFlightsRequest = ServiceTestFixtures.amadeusSearchFlightsRequest();
        when(flightOfferMapper.toAmadeusSearchFlightsRequestDTO(any())).thenReturn(expectedFlightsRequest);
        AmadeusListResponseDTO<AmadeusFlightOfferDTO> response = new AmadeusListResponseDTO<>();
        response.setData(List.of());
        when(offersClient.searchFlightOffers(any())).thenReturn(Mono.just(response));

        // When
        service.searchFlightOffers(request);

        // Then
        ArgumentCaptor<SearchFlightsDTO> captor = ArgumentCaptor.forClass(SearchFlightsDTO.class);
        verify(flightOfferMapper).toAmadeusSearchFlightsRequestDTO(captor.capture());
    }
}
