package neotech.homework.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

@Getter
@Configuration
@RequiredArgsConstructor
public class DataProviderConfig {
    
    @Value("${wiki.phone.country.source.url}")
    String wikiPhoneCountrySourceUrl;
    
    @Bean
    public Document getJsoupDocument() throws IOException {
        return Jsoup.connect(wikiPhoneCountrySourceUrl).get();
    }

}
