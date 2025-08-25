package inc.encora.FlightSearch.amadeus.service;

import inc.encora.FlightSearch.amadeus.model.Token;

public interface AmadeusAuthService {
    Token getAccessToken();
}