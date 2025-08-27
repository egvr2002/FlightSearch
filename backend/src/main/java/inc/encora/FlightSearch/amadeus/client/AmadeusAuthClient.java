package inc.encora.FlightSearch.amadeus.client;

import inc.encora.FlightSearch.amadeus.constants.common.AmadeusWebClientType;
import inc.encora.FlightSearch.amadeus.dto.request.AmadeusTokenRequestDTO;
import inc.encora.FlightSearch.amadeus.dto.response.AmadeusTokenResponseDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class AmadeusAuthClient {
    private final WebClient webClient;

    public AmadeusAuthClient(@Qualifier(AmadeusWebClientType.NO_CREDENTIALS) WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<AmadeusTokenResponseDTO> requestAccessToken(AmadeusTokenRequestDTO credentials) {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();

        body.add("grant_type", credentials.getGrantType());
        body.add("client_id", credentials.getClientId());
        body.add("client_secret", credentials.getClientSecret());

        return webClient
                .post()
                .uri("/v1/security/oauth2/token")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .body(BodyInserters.fromFormData(body))
                .retrieve()
                .bodyToMono(AmadeusTokenResponseDTO.class);
    }
}
