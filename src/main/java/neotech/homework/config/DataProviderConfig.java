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
    
    final Environment environment;
    
    @Value("${wiki.phone.country.source.url}")
    String wikiPhoneCountrySourceUrl;
    
    @Bean
    public Document getJsoupDocument() throws IOException {
        //if (isTestProfileEnabled()) {
        //    File input = new File("classpath:test_data.html");
        //    return Jsoup.parse(input, "UTF-8", "test_data.html");
        //}
        return Jsoup.connect(wikiPhoneCountrySourceUrl).get();
    }
    
    private boolean isTestProfileEnabled() {
        return Arrays.stream(environment.getActiveProfiles()).anyMatch(env -> env.contains("test"));
    }

}
