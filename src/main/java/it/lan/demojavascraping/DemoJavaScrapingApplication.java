package it.lan.demojavascraping;

import it.lan.demojavascraping.service.ScrapeData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoJavaScrapingApplication {
    private static final Logger logger = LoggerFactory.getLogger(ScrapeData.class);

    public static void main(String[] args) {
        SpringApplication.run(DemoJavaScrapingApplication.class, args);
    }
}