package inc.encora.FlightSearch.amadeus.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SearchFlightsDTO {
    @NotNull(message = "Origin location code is required")
    @NotBlank(message = "Origin location code cannot be blank")
    private String originLocationCode;

    @NotNull(message = "Destination location code is required")
    @NotBlank(message = "Destination location code cannot be blank")
    private String destinationLocationCode;

    @NotNull(message = "Departure date is required")
    @FutureOrPresent(message = "Departure date must be today or in the future")
    private LocalDate departureDate;

    @Future(message = "Return date must be in the future")
    private LocalDate returnDate;

    @NotNull(message = "Number of adults is required")
    @Min(value = 1, message = "At least one adult is required")
    private int adults;

    @Pattern(regexp = "^[A-Z]{3}$", message = "Currency code must be a valid ISO 4217 code")
    @Pattern(regexp = "^(USD|EUR|MXN)$", message = "Currency code must be one of USD, EUR, or MXN")
    private String currencyCode;

    private boolean nonStop;
    private int max;
}
