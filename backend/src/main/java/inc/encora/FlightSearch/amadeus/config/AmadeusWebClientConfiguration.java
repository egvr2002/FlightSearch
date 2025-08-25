package inc.encora.FlightSearch.amadeus.config;

import inc.encora.FlightSearch.amadeus.constants.AmadeusWebClientType;
import inc.encora.FlightSearch.amadeus.filters.AddAuthHeaderExchangeFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@RequiredArgsConstructor
public class AmadeusWebClientConfiguration {
    @Value("${application.amadeus.base.url}")
    private String amadeusAPIBaseUrl;

    private WebClient.Builder baseAmadeusWebClientBuilder(WebClient.Builder webClientBuilder) {
        return webClientBuilder.baseUrl(amadeusAPIBaseUrl);
    }

    @Bean(name = AmadeusWebClientType.NO_CREDENTIALS)
    @Qualifier(AmadeusWebClientType.NO_CREDENTIALS)
    public WebClient amadeusWebClient(WebClient.Builder webClientBuilder) {
        return baseAmadeusWebClientBuilder(webClientBuilder).build();
    }

    @Bean(name = AmadeusWebClientType.WITH_CREDENTIALS)
    @Qualifier(AmadeusWebClientType.WITH_CREDENTIALS)
    public WebClient amadeusWebClientWithCredentials(WebClient.Builder webClientBuilder,
            AddAuthHeaderExchangeFilter addAuthHeaderExchangeFilter) {
        return baseAmadeusWebClientBuilder(webClientBuilder)
                .filter(addAuthHeaderExchangeFilter)
                .build();
    }
}
