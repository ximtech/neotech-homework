package neotech.homework.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record PhoneNumberResponseDTO(
        @JsonProperty("phone_number") String enteredPhoneNumber,
        @JsonProperty("country_names") List<String> countryNames) {
}
