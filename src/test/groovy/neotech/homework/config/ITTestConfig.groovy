package neotech.homework.config

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import org.springframework.util.ResourceUtils
import spock.lang.Specification

@TestConfiguration
class ITTestConfig extends Specification {
    
    @Bean
    @Primary
    Document getJsoupDocument() {
        def testDataFile = ResourceUtils.getFile(this.getClass().getResource("/test_data.html"));
        return Jsoup.parse(testDataFile, "UTF-8", "test_data.html")
    }
}
