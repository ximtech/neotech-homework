package neotech.homework.service;

import lombok.RequiredArgsConstructor;
import neotech.homework.domain.entity.PhoneCountryData;
import neotech.homework.domain.repository.PhoneCountryDataRepository;
import neotech.homework.dto.PhoneNumberDTO;
import neotech.homework.dto.PhoneNumberResponseDTO;
import neotech.homework.exception.ApiException;
import neotech.homework.mapper.PhoneCountryDataMapper;
import neotech.homework.utils.PhoneNumberUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PhoneNumberCountryService {

    final PhoneCountryDataRepository phoneCountryDataRepository;
    final PhoneCountryDataMapper phoneCountryDataMapper;

    @Cacheable(cacheNames = "phoneNumberCountryCache")
    public PhoneNumberResponseDTO getPhoneNumberCountryData(String phoneNumber) {
        PhoneNumberUtils.validatePossiblePhoneNumber(phoneNumber);
        PhoneNumberDTO phoneNumberDTO = PhoneNumberUtils.parsePhoneNumber(phoneNumber);
        
        List<PhoneCountryData> phoneCountryDataList = phoneCountryDataRepository.findCountryData(phoneNumberDTO.worldZoneId(), phoneNumberDTO.fullNumber());
        if (CollectionUtils.isEmpty(phoneCountryDataList)) {
            throw new ApiException("Country data for phone: %s do not exist".formatted(phoneNumber), HttpStatus.NOT_FOUND);
        }
        
        List<String> countryNames = phoneCountryDataMapper.mapToCountryNames(phoneCountryDataList);
        return new PhoneNumberResponseDTO(phoneNumber, countryNames);
    }
}
