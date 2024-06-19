package neotech.homework.config

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import org.springframework.util.ResourceUtils
import spock.lang.Specification

@TestConfiguration
class ITTestConfig extends Specification {

    @Value('${wiki.phone.country.source.file}')
    String wikiPhoneCountrySourceFile
    
    @Bean
    @Primary
    Document getJsoupDocument() {
        def testDataFile = ResourceUtils.getFile(this.getClass().getResource("/${wikiPhoneCountrySourceFile}"));
        return Jsoup.parse(testDataFile, "UTF-8", wikiPhoneCountrySourceFile)
    }
}
