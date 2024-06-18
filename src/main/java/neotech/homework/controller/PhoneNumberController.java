package neotech.homework.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import neotech.homework.dto.PhoneNumberResponseDTO;
import neotech.homework.service.PhoneNumberCountryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Slf4j
@RestController
@RequestMapping(value = "/api/phone/number")
@RequiredArgsConstructor
public class PhoneNumberController {
    
    final PhoneNumberCountryService phoneNumberCountryService;

    @GetMapping(path = "/country/{phoneNumber}")
    @CircuitBreaker(name = "phone-api-service")
    @TimeLimiter(name = "phone-api-service")
    @RateLimiter(name = "phone-api-service")
    public Mono<PhoneNumberResponseDTO> listClientAccounts(@PathVariable("phoneNumber") String phoneNumber) {
        log.debug("GET /api/phone/number/country [{}]", phoneNumber);
        return Mono.fromCallable(() -> phoneNumberCountryService.getPhoneNumberCountryData(phoneNumber))
                .publishOn(Schedulers.boundedElastic());
    }

}
