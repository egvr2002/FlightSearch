package inc.encora.FlightSearch.amadeus.service.impl;

import inc.encora.FlightSearch.amadeus.client.AmadeusAuthClient;
import inc.encora.FlightSearch.amadeus.constants.AmadeusWebClientType;
import inc.encora.FlightSearch.amadeus.dto.request.TokenRequestDTO;
import inc.encora.FlightSearch.amadeus.mapper.TokenMapper;
import inc.encora.FlightSearch.amadeus.model.Token;
import inc.encora.FlightSearch.amadeus.service.AmadeusAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class AmadeusAuthServiceImpl implements AmadeusAuthService {
    private final AmadeusAuthClient amadeusAuthClient;

    private final TokenMapper tokenMapper;

    private final AtomicReference<Token> accessTokenCache = new AtomicReference<>();

    @Value("${application.amadeus.api.key}")
    private String apiKey;

    @Value("${application.amadeus.api.secret}")
    private String apiSecret;

    private Token fetchAcessToken() {
        TokenRequestDTO credentials = new TokenRequestDTO("client_credentials", apiKey, apiSecret);
        return amadeusAuthClient.requestAccessToken(credentials)
                .map(tokenMapper::toToken)
                .block();
    }

    @Override
    public Token getAccessToken() {
        Token currentToken = accessTokenCache.get();
        if (currentToken == null || currentToken.isExpired()) {
            Token newToken = fetchAcessToken();
            accessTokenCache.set(newToken);
        }

        return accessTokenCache.get();
    }
}
