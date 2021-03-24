package it.lan.demojavascraping;

import it.lan.demojavascraping.service.ScrapeBike;
import it.lan.demojavascraping.service.ScrapeData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

@SpringBootApplication
public class DemoJavaScrapingApplication {
    private static final Logger logger = LoggerFactory.getLogger(ScrapeData.class);

    public static void main(String[] args) {
        SpringApplication.run(DemoJavaScrapingApplication.class, args);
    }




}
