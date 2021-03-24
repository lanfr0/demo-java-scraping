package it.lan.demojavascraping.controller;

import it.lan.demojavascraping.model.CovidData;
import it.lan.demojavascraping.model.Mag;
import it.lan.demojavascraping.model.ProxyData;
import it.lan.demojavascraping.service.ScrapeData;
import it.lan.demojavascraping.service.Utils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/scrape/v1")
public class DataController {

    private final ScrapeData scrapeData = new ScrapeData();

    @RequestMapping(value = "/data", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> getCovidData() {
        HttpStatus httpStatus = HttpStatus.OK;

        List<CovidData> datas = scrapeData.retrieveCovidData();

        Map<String, Object> map= new HashMap<>();
        if (datas.isEmpty()) {
            map.put("success", false);
        }else {
            map.put("success", true);
            map.put("contracts", datas);
        }
        ResponseEntity<Map<String, Object>> response = new ResponseEntity<>(map, httpStatus);
        return response;
    }

    @RequestMapping(value = "/magazine", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> getMagazine() throws IOException {
        HttpStatus httpStatus = HttpStatus.OK;

        List<Mag> magazine = scrapeData.retrieveMagazine();

        Map<String, Object> map= new HashMap<>();
        if (magazine.isEmpty()) {
            map.put("success", false);
        }else {
            map.put("success", true);
            map.put("contracts", magazine);
        }
        return new ResponseEntity<>(map, httpStatus);
    }

    @RequestMapping(value = "/magazineproxy", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> getMagazineProxy() throws IOException {
        HttpStatus httpStatus = HttpStatus.OK;

        List<Mag> magazine = scrapeData.retrieveMagazineProxy();

        Map<String, Object> map= new HashMap<>();
        if (magazine.isEmpty()) {
            map.put("success", false);
        }else {
            map.put("success", true);
            map.put("contracts", magazine);
        }
        return new ResponseEntity<>(map, httpStatus);
    }

    @RequestMapping(value = "/magazine-proxy-selenium", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> getMagazineProxySelenium() throws IOException {
        HttpStatus httpStatus = HttpStatus.OK;

        List<Mag> magazine = scrapeData.testSelenium();

        Map<String, Object> map= new HashMap<>();
        if (magazine.isEmpty()) {
            map.put("success", false);
        }else {
            map.put("success", true);
            map.put("contracts", magazine);
        }
        return new ResponseEntity<>(map, httpStatus);
    }
}


