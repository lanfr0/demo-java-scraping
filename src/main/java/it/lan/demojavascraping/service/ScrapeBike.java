package it.lan.demojavascraping.service;

import it.lan.demojavascraping.controller.FeedbackController;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@EnableAsync
@Service
@EnableScheduling
public class ScrapeBike {
    private final Logger logger = LoggerFactory.getLogger(ScrapeBike.class);
    private static String url = "https://www.decathlon.it/p/mtb-st-540-grigio-rosso-27-5/_/R-p-301097?mc=8529702&c=BEIGE";

    private final FeedbackController feedbackController;

    public ScrapeBike(FeedbackController feedbackController) {
        this.feedbackController = feedbackController;
    }

    @Async
    @Scheduled(fixedRate = 90000)
    public void getAvailability() {
        Document webPage;

        try {
            webPage = Jsoup
                    .connect(url)
                    .get();

            StringBuilder res = new StringBuilder();

            Element title = webPage.getElementsByClass("title--main product-title-right").get(0);
            Element color = webPage.getElementsByClass("model-title").get(0);
            logger.info("{}", title.text());
            logger.info("{}", color.text());
            res.append(title.text()).append(" ").append(color.text()).append(" ");

            Elements ulChildrens = webPage.getElementsByClass("sizes__list").get(0).children();
            //logger.info("{}", ulChildrens.size());

            Map<String, String> codeValue = new HashMap<>();
            for(Element ilChild : ulChildrens) {
                //taglia e disponibilità
                Elements childrens = ilChild.children();

                String size = childrens.get(0).text();
                String disponibilita = childrens.get(1).text();
                logger.info("{} {}", size, disponibilita);

                codeValue = ilChild.dataset();

                res.append(size).append(" ").append(disponibilita).append(" ");
            }
            feedbackController.sendInfo(res.toString());

        } catch (IOException e) {
            logger.info("Can't connect to: {}", url);
            e.printStackTrace();
        }


    }
}
