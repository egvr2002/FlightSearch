package inc.encora.FlightSearch.amadeus.mapper;

import inc.encora.FlightSearch.amadeus.dto.response.AmadeusTokenResponseDTO;
import inc.encora.FlightSearch.amadeus.model.Token;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Mapper(componentModel = "spring", uses = {TokenExpirationMapper.class})
public interface TokenMapper {
    @Mapping(target = "value", source = "accessToken")
    @Mapping(target = "expiresAt", source = "expiresIn", qualifiedByName = "toInstant")
    Token toToken(AmadeusTokenResponseDTO amadeusTokenResponseDTO);
}

class TokenExpirationMapper {
    @Named("toInstant")
    public static Instant toInstant(Long value) {
        if (value == null) {
            return null;
        }
        return Instant.now().plus(value, ChronoUnit.SECONDS);
    }
}