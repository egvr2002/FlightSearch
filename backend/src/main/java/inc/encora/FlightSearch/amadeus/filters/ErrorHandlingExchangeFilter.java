package inc.encora.FlightSearch.amadeus.filters;

import inc.encora.FlightSearch.amadeus.dto.response.AmadeusErrorResponseDTO;
import inc.encora.FlightSearch.amadeus.exceptions.api.AmadeusAPIException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class ErrorHandlingExchangeFilter implements ExchangeFilterFunction {

    @Override
    public Mono<ClientResponse> filter(ClientRequest request, ExchangeFunction next) {
        return next.exchange(request)
                .flatMap(this::handleResponse);
    }

    private Mono<ClientResponse> handleResponse(ClientResponse clientResponse) {
        HttpStatusCode statusCode = clientResponse.statusCode();

        if (statusCode.isError()) {
            return clientResponse
                    .bodyToMono(AmadeusErrorResponseDTO.class)
                    .flatMap(errorResponseDTO -> {
                        String errorMessage = errorResponseDTO.getErrors().getFirst().getDetail();
                        log.info(errorMessage);
                        log.error("Amadeus API error response: {}", errorMessage);
                        return Mono.error(new AmadeusAPIException(errorMessage));
                    });
        }

        return Mono.just(clientResponse);
    }
}
