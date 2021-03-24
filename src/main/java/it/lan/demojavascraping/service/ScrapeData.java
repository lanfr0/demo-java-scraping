package it.lan.demojavascraping.service;

import it.lan.demojavascraping.model.CovidData;
import it.lan.demojavascraping.model.Mag;

import it.lan.demojavascraping.model.ProxyData;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class ScrapeData {
    private static final Logger logger = LoggerFactory.getLogger(ScrapeData.class);
    //private static final String[] titles = {"Focus", "Vogue", "GQ", "Forbes"};
    private static final String[] titles = {"Vogue", "GQ", "Forbes"};


    public List<CovidData> retrieveCovidData() {
        List<CovidData> covidData = new ArrayList<>();

        try {
            Document webPage = Jsoup
                    .connect("https://en.wikipedia.org/wiki/COVID-19_pandemic_by_country_and_territory")
                    .get();

            Element tbody = webPage.getElementById("thetable").getElementsByTag("tbody").get(0);

            Elements rows = tbody.children();

            for(Element row : rows) {
                String country = row.getElementsByTag("a").get(0).text();
                Elements tds = row.getElementsByTag("td");

                if(tds.size() < 3)
                    continue;

                String cases = tds.get(0).text().replace(",","");
                String death = tds.get(1).text().replace(",","");
                String recoveries = tds.get(2).text().replace(",","");

                covidData.add(new CovidData(country, cases, death, recoveries));
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return covidData;
    }

    public List<Mag> retrieveMagazine() throws IOException {
        List<Mag> magazineData = new ArrayList<>();

        URL webUrl = new URL("https://eurekaddl.buzz/edicola-categoria/riviste/page/1");

        int count = 1;

        while(count <= 10) {
            StringBuilder progressiveUrl = new StringBuilder();
            progressiveUrl.append(webUrl.toString());
            progressiveUrl.deleteCharAt(progressiveUrl.toString().length()-1);
            progressiveUrl.append(count).append("/");

            logger.info("{}", progressiveUrl.toString());

            try {
                Document webPage = Jsoup
                        .connect(progressiveUrl.toString())
                        .get();

                Elements tbody = webPage.getElementsByClass("teaser-box");

                for(Element t : tbody) {
                    String a = t.getElementsByTag("a").text();

                    for(String query : titles) {
                        if(a.startsWith(query)) {
                            Mag magazine = new Mag();
                            //logger.info("{}", a);
                            String link = t.getElementsByTag("a").attr("href");
                            //logger.info("{}", link);
                            magazine.setName(a);
                            magazine.setLink(link);
                            magazine.setLinkSource(progressiveUrl.toString());
                            magazineData.add(magazine);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            count++;
        }

        return magazineData;
    }

    public List<Mag> retrieveMagazineProxy() throws IOException {
        List<Mag> magazineData = new ArrayList<>();

        String url = "https://eurekaddl.buzz/edicola-categoria/riviste/page/1";

        // System.setProperty("http.proxyhost", "98.162.25.23");
        // System.setProperty("http.proxyport", "4145");

        SocketAddress addr = new InetSocketAddress("37.235.97.13", 3128);
        Proxy proxy = new Proxy(Proxy.Type.HTTP, addr);

        URL urlN = new URL("https://eurekaddl.buzz/edicola-categoria/riviste/page/1");
        URLConnection conn = urlN.openConnection(proxy);

        int count = 1;

        while(count <= 10) {

            StringBuilder progressiveUrl = new StringBuilder();
            progressiveUrl.append(url);
            progressiveUrl.deleteCharAt(progressiveUrl.toString().length()-1);
            progressiveUrl.append(count).append("/");

            logger.info(progressiveUrl.toString());

            try {
                Document webPage = Jsoup.connect(progressiveUrl.toString())
                    .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                    .ignoreContentType(true)
                    .timeout(5000)
                    .get();

                Elements tbody = webPage.getElementsByClass("teaser-box");

                for(Element t : tbody) {
                    String a = t.getElementsByTag("a").text();

                    for(String query : titles) {
                        if(a.startsWith(query)) {
                            Mag magazine = new Mag();
                            //logger.info("{}", a);
                            String link = t.getElementsByTag("a").attr("href");
                            //logger.info("{}", link);
                            magazine.setName(a);
                            magazine.setLink(link);
                            magazine.setLinkSource(progressiveUrl.toString());
                            magazineData.add(magazine);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


            count++;
        }

        return magazineData;
    }

    public List<Mag> testSelenium() throws IOException {
        ChromeDriver driver = new ChromeDriver();
        List<Mag> magazineData = new ArrayList<>();

        String url = "https://eurekaddl.rest/edicola-categoria/riviste/page/1";

        System.setProperty("http.proxyhost", "138.199.30.137");
        System.setProperty("http.proxyport", "3128");

        int count = 1;

        while(count <= 10) {
            StringBuilder progressiveUrl = new StringBuilder();
            progressiveUrl.append(url);
            progressiveUrl.deleteCharAt(progressiveUrl.toString().length() - 1);
            progressiveUrl.append(count).append("/");

            driver.get(progressiveUrl.toString());

            count++;
        }

        driver.quit();

        return magazineData;
    }
}
