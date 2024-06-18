package neotech.homework;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import neotech.homework.config.DataProviderConfig;
import neotech.homework.domain.entity.PhoneCountryData;
import neotech.homework.domain.repository.PhoneCountryDataRepository;
import neotech.homework.service.WikiPhoneNumberService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.config.EnableWebFlux;

import java.util.List;

@Slf4j
@EnableCaching
@SpringBootApplication
@RequiredArgsConstructor
public class NeoTechHomeworkApplication {
    
    final DataProviderConfig dataProviderConfig;
    final WikiPhoneNumberService wikiPhoneNumberService;
    final PhoneCountryDataRepository phoneCountryDataRepository;

    public static void main(String[] args) {
        SpringApplication.run(NeoTechHomeworkApplication.class, args);
    }
    
    @Bean
    public CommandLineRunner databaseDataInitializer() {
        return args -> {
            log.info("Starting database initialization");
            phoneCountryDataRepository.deleteAllInBatch(); // truncate table from previous data
            Document document = dataProviderConfig.getJsoupDocument();
            List<PhoneCountryData> phoneCountyCodeDataList = wikiPhoneNumberService.parseWikiPagePhoneNumbers(document);
            phoneCountryDataRepository.saveAllAndFlush(phoneCountyCodeDataList);
            log.info("Database successfully initialized");
        };
    }

}
