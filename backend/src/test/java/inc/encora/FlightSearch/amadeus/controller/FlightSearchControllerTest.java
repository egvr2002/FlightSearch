package inc.encora.FlightSearch.amadeus.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import inc.encora.FlightSearch.amadeus.controller.fixtures.ControllerTestFixtures;
import inc.encora.FlightSearch.amadeus.dto.request.SearchFlightsDTO;
import inc.encora.FlightSearch.amadeus.dto.request.SearchLocationsDTO;
import inc.encora.FlightSearch.amadeus.model.Address;
import inc.encora.FlightSearch.amadeus.model.AirlineInformation;
import inc.encora.FlightSearch.amadeus.model.FlightOffer;
import inc.encora.FlightSearch.amadeus.model.Location;
import inc.encora.FlightSearch.amadeus.service.FlightSearchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.NoSuchElementException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FlightSearchController.class)
class FlightSearchControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @MockitoBean
        private FlightSearchService flightSearchService;

        @Autowired
        private ObjectMapper objectMapper;

        private Location testLocation;
        private Address testAddress;
        private AirlineInformation testAirlineInfo;
        private FlightOffer testFlightOffer;

        @BeforeEach
        void setUp() {
                testLocation = ControllerTestFixtures.locationModel("Madrid-Barajas Airport", "MAD", "Madrid");
                testAddress = ControllerTestFixtures.addressModel("Madrid");
                testAirlineInfo = ControllerTestFixtures.airlineModel("Iberia", "IB");
                testFlightOffer = ControllerTestFixtures.flightOfferModel("FLIGHT001", "EUR", "500.00");
        }

        @Test
        void shouldSearchLocationsSuccessfully() throws Exception {
                // Given
                when(flightSearchService.searchLocations(any(SearchLocationsDTO.class)))
                                .thenReturn(List.of(testLocation));

                // When & Then
                mockMvc.perform(get("/api/search/locations/airport")
                                .param("keyword", "Madrid")
                                .param("pageLimit", "10")
                                .param("pageOffset", "0"))
                                .andExpect(status().isOk())
                                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$[0].airportName").value("Madrid-Barajas Airport"))
                                .andExpect(jsonPath("$[0].iataCode").value("MAD"))
                                .andExpect(jsonPath("$[0].cityName").value("Madrid"));
        }

        @Test
        void shouldReturnBadRequestWhenSearchLocationsKeywordIsBlank() throws Exception {
                // When & Then
                mockMvc.perform(get("/api/search/locations/airport")
                                .param("keyword", ""))
                                .andExpect(status().isBadRequest());
        }

        @Test
        void shouldSearchCityByIATACodeSuccessfully() throws Exception {
                // Given
                when(flightSearchService.searchCityByIATACode("MAD"))
                                .thenReturn(testAddress);

                // When & Then
                mockMvc.perform(get("/api/search/locations/city/MAD"))
                                .andExpect(status().isOk())
                                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$.cityName").value("Madrid"));
        }

        @Test
        void shouldReturnBadRequestWhenIATACodeIsInvalid() throws Exception {
                // When & Then - Too short
                mockMvc.perform(get("/api/search/locations/city/MA"))
                                .andExpect(status().isBadRequest());

                // When & Then - Too long
                mockMvc.perform(get("/api/search/locations/city/MADR"))
                                .andExpect(status().isBadRequest());

                // When & Then - Contains numbers
                mockMvc.perform(get("/api/search/locations/city/M1D"))
                                .andExpect(status().isBadRequest());
        }

        @Test
        void shouldReturnNotFoundWhenCityByIATACodeNotFound() throws Exception {
                // Given
                when(flightSearchService.searchCityByIATACode("XXX"))
                                .thenThrow(new NoSuchElementException("City not found"));

                // When & Then
                mockMvc.perform(get("/api/search/locations/city/XXX"))
                                .andExpect(status().isNotFound()); // Spring automatically converts
                                                                   // NoSuchElementException to 404
        }

        @Test
        void shouldSearchAirlineInformationSuccessfully() throws Exception {
                // Given
                when(flightSearchService.searchAirlineInformation("IB"))
                                .thenReturn(testAirlineInfo);

                // When & Then
                mockMvc.perform(get("/api/search/airlines/IB"))
                                .andExpect(status().isOk())
                                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$.name").value("Iberia"))
                                .andExpect(jsonPath("$.iataCode").value("IB"));
        }

        @Test
        void shouldReturnBadRequestWhenAirlineCodeIsInvalid() throws Exception {
                // When & Then - Too short
                mockMvc.perform(get("/api/search/airlines/I"))
                                .andExpect(status().isBadRequest());

                // When & Then - Too long
                mockMvc.perform(get("/api/search/airlines/IBER"))
                                .andExpect(status().isBadRequest());
        }

        @Test
        void shouldSearchFlightOffersSuccessfully() throws Exception {
                // Given
                SearchFlightsDTO searchRequest = ControllerTestFixtures.validSearchFlightsRequest();

                when(flightSearchService.searchFlightOffers(any(SearchFlightsDTO.class)))
                                .thenReturn(List.of(testFlightOffer));

                // When & Then
                mockMvc.perform(post("/api/search/flights")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(searchRequest)))
                                .andExpect(status().isOk())
                                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$[0].flightOfferId").value("FLIGHT001"))
                                .andExpect(jsonPath("$[0].currency").value("EUR"))
                                .andExpect(jsonPath("$[0].totalPrice").value("500.00"));
        }

        @Test
        void shouldReturnBadRequestWhenFlightSearchRequestIsInvalid() throws Exception {
                // Given - Missing required fields
                SearchFlightsDTO invalidRequest = new SearchFlightsDTO();

                // When & Then
                mockMvc.perform(post("/api/search/flights")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(invalidRequest)))
                                .andExpect(status().isBadRequest());
        }

        @Test
        void shouldReturnBadRequestWhenDepartureDateIsInPast() throws Exception {
                // Given
                SearchFlightsDTO invalidRequest = ControllerTestFixtures.invalidSearchFlightsRequestWithPastDate();

                // When & Then
                mockMvc.perform(post("/api/search/flights")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(invalidRequest)))
                                .andExpect(status().isBadRequest());
        }

        @Test
        void shouldReturnBadRequestWhenAdultsIsZero() throws Exception {
                // Given
                SearchFlightsDTO invalidRequest = ControllerTestFixtures.invalidSearchFlightsRequestWithZeroAdults();

                // When & Then
                mockMvc.perform(post("/api/search/flights")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(invalidRequest)))
                                .andExpect(status().isBadRequest());
        }

        @Test
        void shouldReturnBadRequestWhenCurrencyCodeIsInvalid() throws Exception {
                // Given
                SearchFlightsDTO invalidRequest = ControllerTestFixtures
                                .invalidSearchFlightsRequestWithInvalidCurrency();

                // When & Then
                mockMvc.perform(post("/api/search/flights")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(invalidRequest)))
                                .andExpect(status().isBadRequest());
        }

        @Test
        void shouldHandleEmptyResultsGracefully() throws Exception {
                // Given
                when(flightSearchService.searchLocations(any(SearchLocationsDTO.class)))
                                .thenReturn(List.of());

                // When & Then
                mockMvc.perform(get("/api/search/locations/airport")
                                .param("keyword", "NonExistentCity"))
                                .andExpect(status().isOk())
                                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$").isArray())
                                .andExpect(jsonPath("$").isEmpty());
        }

        @Test
        void shouldHandleServiceExceptionGracefully() throws Exception {
                // Given
                when(flightSearchService.searchLocations(any(SearchLocationsDTO.class)))
                                .thenThrow(new RuntimeException("Service unavailable"));

                // When & Then
                mockMvc.perform(get("/api/search/locations/airport")
                                .param("keyword", "Madrid"))
                                .andExpect(status().isInternalServerError());
        }
}