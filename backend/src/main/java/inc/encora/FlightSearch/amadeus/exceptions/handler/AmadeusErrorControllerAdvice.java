package inc.encora.FlightSearch.amadeus.exceptions.handler;

import inc.encora.FlightSearch.amadeus.dto.response.APIResponseErrorDTO;
import inc.encora.FlightSearch.amadeus.exceptions.api.AmadeusAPIException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@ControllerAdvice
public class AmadeusErrorControllerAdvice {

    @ExceptionHandler({AmadeusAPIException.class})
    public ResponseEntity<APIResponseErrorDTO> handleAmadeusAPIException(AmadeusAPIException e) {
        log.error("Amadeus API Error: {}", e.getMessage(), e);

        APIResponseErrorDTO errorDTO = new APIResponseErrorDTO();
        errorDTO.setStatus(HttpStatus.BAD_REQUEST.toString());
        errorDTO.setMessage(e.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDTO);
    }

    @ExceptionHandler({WebClientResponseException.class})
    public ResponseEntity<APIResponseErrorDTO> handleWebClientResponseException(WebClientResponseException e) {
        log.error("WebClient Error: {}", e.getMessage(), e);

        APIResponseErrorDTO errorDTO = new APIResponseErrorDTO();
        errorDTO.setStatus(e.getStatusCode().toString());
        errorDTO.setMessage("Error connecting to Amadeus API. " + e.getMessage());

        return ResponseEntity.status(e.getStatusCode()).body(errorDTO);
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<APIResponseErrorDTO> handleRuntimeException(RuntimeException e) {
        log.error("Runtime Error: {}", e.getMessage(), e);

        APIResponseErrorDTO errorDTO = new APIResponseErrorDTO();
        errorDTO.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
        errorDTO.setMessage("Internal server error. " + e.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDTO);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<APIResponseErrorDTO> handleGenericException(Exception e) {
        log.error("Unexpected Error: {}", e.getMessage(), e);

        APIResponseErrorDTO errorDTO = new APIResponseErrorDTO();
        errorDTO.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
        errorDTO.setMessage("Unexpected error. " + e.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDTO);
    }

    @ExceptionHandler({NoSuchElementException.class})
    public ResponseEntity<APIResponseErrorDTO> handleNoSuchElementException(NoSuchElementException e) {
        log.error("No Such Element Error: {}", e.getMessage(), e);

        APIResponseErrorDTO errorDTO = new APIResponseErrorDTO();
        errorDTO.setStatus(HttpStatus.NOT_FOUND.toString());
        errorDTO.setMessage(e.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIResponseErrorDTO> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errorMessage = "Validation Error";
        Optional<FieldError> firstError = ex.getBindingResult().getFieldErrors().stream().findFirst();

        if (firstError.isPresent()) {
            FieldError fieldError = firstError.get();
            errorMessage = fieldError.getField() + ": " + fieldError.getDefaultMessage();
        }

        log.error("Validation Error: {}", errorMessage, ex);

        APIResponseErrorDTO errorDTO = new APIResponseErrorDTO();
        errorDTO.setStatus(HttpStatus.BAD_REQUEST.toString());
        errorDTO.setMessage(errorMessage);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDTO);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<APIResponseErrorDTO> handleConstraintViolationException(ConstraintViolationException ex) {
        String errorMessage = "Constraint Violation";
        Optional<ConstraintViolation<?>> firstViolation = ex.getConstraintViolations().stream().findFirst();

        if (firstViolation.isPresent()) {
            ConstraintViolation<?> violation = firstViolation.get();
            errorMessage = violation.getMessage();
        }
        log.error("Constraint Violation: {}", errorMessage, ex);

        APIResponseErrorDTO errorDTO = new APIResponseErrorDTO();
        errorDTO.setStatus(HttpStatus.BAD_REQUEST.toString());
        errorDTO.setMessage(errorMessage);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDTO);
    }
}
