package inc.encora.FlightSearch.amadeus.filters;

import inc.encora.FlightSearch.amadeus.service.AmadeusAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class AddAuthHeaderExchangeFilter implements ExchangeFilterFunction {
    private final AmadeusAuthService amadeusAuthService;

    @Override
    public Mono<ClientResponse> filter(ClientRequest request, ExchangeFunction next) {
        ClientRequest newRequest = ClientRequest.from(request)
                .headers((httpHeaders -> httpHeaders.setBearerAuth(
                        amadeusAuthService.getAccessToken().getValue()
                )))
                .build();
        return next.exchange(newRequest);
    }
}
