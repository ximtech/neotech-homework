package neotech.homework.utils;

import io.micrometer.common.util.StringUtils;
import neotech.homework.dto.PhoneNumberDTO;
import neotech.homework.exception.ApiException;
import org.springframework.http.HttpStatus;

import java.util.regex.Pattern;

public class PhoneNumberUtils {

    // We don't allow input strings for parsing to be longer than 250 chars. This prevents malicious
    // input from overflowing the regular-expression engine.
    public static final int MAX_INPUT_STRING_LENGTH = 250;
    public static final Pattern PLUS_CHAR_PATTERN = Pattern.compile("^\\+.+");
    public static final Pattern VALID_PHONE_NUMBER_PATTERN = Pattern.compile("^\\+\\d{1,4}?[-.\\s]?\\(?\\d{1,3}?\\)?[-.\\s]?\\d{1,4}[-.\\s]?\\d{1,4}[-.\\s]?\\d{1,9}$");
    // Valid phone numbers can have different length, but for simplicity suppose that only standardized length is legal 
    public static final int LEGAL_PHONE_NUMBER_LENGTH = 10;


    public static void validatePossiblePhoneNumber(String rawPhoneNumber) {
        if (StringUtils.isBlank(rawPhoneNumber)) {
            throw new ApiException("The phone number supplied was empty", HttpStatus.BAD_REQUEST);
        }
        
        if (rawPhoneNumber.length() > MAX_INPUT_STRING_LENGTH) {
            throw new ApiException( "The phone number string supplied was too long to parse", HttpStatus.BAD_REQUEST);
        }
        
        if (!PLUS_CHAR_PATTERN.matcher(rawPhoneNumber).matches()) {
            throw new ApiException("%s phone should begin with '+' sign".formatted(rawPhoneNumber), HttpStatus.BAD_REQUEST);
        }

        if (!VALID_PHONE_NUMBER_PATTERN.matcher(rawPhoneNumber).matches()) {
            throw new ApiException("Invalid phone number format: %s. Legal phone should look like +371 1123, +1 (555) 111 4555, +15555".formatted(rawPhoneNumber), HttpStatus.BAD_REQUEST);
        }
    }

    public static PhoneNumberDTO parsePhoneNumber(String rawPhoneNumber) {
        String cleanPhoneNumber = rawPhoneNumber.replaceAll("[()\\-+\\s]", "");
        if (cleanPhoneNumber.length() < LEGAL_PHONE_NUMBER_LENGTH) {
            throw new ApiException("Invalid phone number length. Legal number must have not less than %d digits".formatted(LEGAL_PHONE_NUMBER_LENGTH), HttpStatus.BAD_REQUEST);
        }
        return new PhoneNumberDTO(cleanPhoneNumber, extractPhoneCountryZoneId(cleanPhoneNumber));
    }
    
    public static String extractPhoneCountryZoneId(String phoneNumber) {
        return phoneNumber.substring(0, 1);
    }

}
