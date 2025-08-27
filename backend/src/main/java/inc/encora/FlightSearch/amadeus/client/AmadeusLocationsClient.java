package inc.encora.FlightSearch.amadeus.client;

import inc.encora.FlightSearch.amadeus.constants.common.AmadeusWebClientType;
import inc.encora.FlightSearch.amadeus.dto.common.AmadeusLocationDTO;
import inc.encora.FlightSearch.amadeus.dto.request.AmadeusSearchLocationsRequestDTO;
import inc.encora.FlightSearch.amadeus.dto.response.AmadeusListResponseDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class AmadeusLocationsClient {
    private final WebClient webClient;

    public AmadeusLocationsClient(@Qualifier(AmadeusWebClientType.WITH_CREDENTIALS) WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<AmadeusListResponseDTO<AmadeusLocationDTO>> searchLocations(AmadeusSearchLocationsRequestDTO requestDTO) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/v1/reference-data/locations")
                        .queryParam("subType", requestDTO.getSubType())
                        .queryParam("keyword", requestDTO.getKeyword())
                        .queryParam("page[limit]", requestDTO.getPageLimit())
                        .queryParam("page[offset]", requestDTO.getPageOffset())
                        .queryParam("sort", requestDTO.getSort())
                        .queryParam("view", requestDTO.getView())
                        .build()
                )
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>() {});
    }
}
