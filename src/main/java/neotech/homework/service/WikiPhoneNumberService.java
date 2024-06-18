package neotech.homework.service;

import lombok.RequiredArgsConstructor;
import neotech.homework.domain.entity.PhoneCountryData;
import neotech.homework.domain.entity.PhoneWorldZone;
import neotech.homework.domain.repository.PhoneWorldZoneRepository;
import neotech.homework.utils.PhoneNumberUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class WikiPhoneNumberService {

    static final String ALL_NON_DIGIT_CHARS = "\\D+";
    static final int SINGLE_CODE_PER_COUNTRY = 1;
    static final int MAX_NUMBER_OF_PHONE_COUNTRIES = 300;

    final PhoneWorldZoneRepository phoneWorldZoneRepository;
    
    public List<PhoneCountryData> parseWikiPagePhoneNumbers(Document sourceDocument) {
        Elements countryAndPhoneCodeTableData = sourceDocument.select("table").get(0).select("tbody").select("tr");
        Map<String, PhoneWorldZone> phoneWorldZoneMap = phoneWorldZoneRepository.mapAllWorldZoneIds();
        
        List<PhoneCountryData> phoneCountyCodes = new ArrayList<>(MAX_NUMBER_OF_PHONE_COUNTRIES);
        for (Element element : countryAndPhoneCodeTableData) {
            Elements tableRow = element.select("td");
            if (tableRow.isEmpty()) {
                continue;
            }

            String countryName = normalizeText(tableRow.get(0).text());
            Pair<String, List<String>> phoneCountryCodesPair = extractPhoneDialingCodesWithWorldZoneId(tableRow.get(1).text());
            PhoneWorldZone phoneWorldZone = Objects.requireNonNull(phoneWorldZoneMap.get(phoneCountryCodesPair.getLeft()), "Unknown world phone code: " + phoneCountryCodesPair.getLeft());
            List<PhoneCountryData> phoneNumberCountryCodeDataList = phoneCountryCodesPair.getRight().stream()
                    .map((String dialingCode) -> new PhoneCountryData(countryName, dialingCode, phoneWorldZone))
                    .collect(Collectors.toList());
            phoneCountyCodes.addAll(phoneNumberCountryCodeDataList);
        }
        return phoneCountyCodes;
    }
    
    private String normalizeText(String textWithAccentsAndDiacritics) {
        String normalizedText = Normalizer.normalize(textWithAccentsAndDiacritics, Normalizer.Form.NFKD);
        return normalizedText.replaceAll("\\p{M}", "");
    }

    private Pair<String, List<String>> extractPhoneDialingCodesWithWorldZoneId(String phoneCodeRawString) {
        List<String> phoneCountryCodes = Arrays.asList(phoneCodeRawString.replaceAll(ALL_NON_DIGIT_CHARS, " ").split(" "));
        if (CollectionUtils.isEmpty(phoneCountryCodes)) {
            throw new RuntimeException("Codes not found for country");
        }

        String internationPhoneCode = phoneCountryCodes.getFirst().trim();
        String worldPhoneCountryZoneId = PhoneNumberUtils.extractPhoneCountryZoneId(internationPhoneCode);
        if (phoneCountryCodes.size() == SINGLE_CODE_PER_COUNTRY) {
            return Pair.of(worldPhoneCountryZoneId, phoneCountryCodes);
        }

        return Pair.of(worldPhoneCountryZoneId, Stream.iterate(1, index -> index + 1)    // concat all country dial codes with regional code
                .limit(phoneCountryCodes.size() - 1)
                .map((Integer index) -> internationPhoneCode + phoneCountryCodes.get(index))
                .collect(Collectors.toList()));
    }
}
