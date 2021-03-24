package it.lan.demojavascraping.config;

import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class SeleniumConfiguration {

    @PostConstruct
    void postConstruct() {
        System.setProperty("webdriver.chrome.driver","/Users/marcolanfranchi/exec/chromedriver");
    }
}
