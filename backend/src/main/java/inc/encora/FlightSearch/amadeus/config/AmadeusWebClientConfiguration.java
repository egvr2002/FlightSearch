package inc.encora.FlightSearch.amadeus.config;

import inc.encora.FlightSearch.amadeus.constants.common.AmadeusWebClientType;
import inc.encora.FlightSearch.amadeus.filters.AddAuthHeaderExchangeFilter;
import inc.encora.FlightSearch.amadeus.filters.ErrorHandlingExchangeFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class AmadeusWebClientConfiguration {
    private final ErrorHandlingExchangeFilter errorHandlingExchangeFilter;
    @Value("${application.amadeus.base.url}")
    private String amadeusAPIBaseUrl;

    private WebClient.Builder baseAmadeusWebClientBuilder(WebClient.Builder webClientBuilder) {
        return webClientBuilder.baseUrl(amadeusAPIBaseUrl).filter(errorHandlingExchangeFilter);
    }

    @Bean(name = AmadeusWebClientType.NO_CREDENTIALS)
    @Qualifier(AmadeusWebClientType.NO_CREDENTIALS)
    public WebClient amadeusWebClient(WebClient.Builder webClientBuilder) {
        return baseAmadeusWebClientBuilder(webClientBuilder).build();
    }

    @Bean(name = AmadeusWebClientType.WITH_CREDENTIALS)
    @Qualifier(AmadeusWebClientType.WITH_CREDENTIALS)
    public WebClient amadeusWebClientWithCredentials(WebClient.Builder webClientBuilder, AddAuthHeaderExchangeFilter addAuthHeaderExchangeFilter) {
        return baseAmadeusWebClientBuilder(webClientBuilder).filter(addAuthHeaderExchangeFilter).build();
    }
}
