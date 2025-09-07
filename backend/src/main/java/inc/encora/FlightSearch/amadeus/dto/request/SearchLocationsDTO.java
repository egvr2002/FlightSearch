package inc.encora.FlightSearch.amadeus.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class SearchLocationsDTO {
    @NotNull(message = "Keyword is required")
    @NotBlank(message = "Keyword cannot be blank")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Invalid query parameter format")
    private String keyword;

    private String subType;
    private String sort;
    private String view;
    private int pageLimit = 10;
    private int pageOffset = 0;
}
