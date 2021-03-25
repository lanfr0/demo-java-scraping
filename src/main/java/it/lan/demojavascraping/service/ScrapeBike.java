package it.lan.demojavascraping.service;

import it.lan.demojavascraping.controller.FeedbackController;
import it.lan.demojavascraping.model.Bike;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class ScrapeBike {
    private final Logger logger = LoggerFactory.getLogger(ScrapeBike.class);
    private static String url = "https://www.decathlon.it/p/mtb-st-540-grigio-rosso-27-5/_/R-p-301097?mc=8529702&c=BEIGE";

    private final FeedbackController feedbackController;

    public ScrapeBike(FeedbackController feedbackController) {
        this.feedbackController = feedbackController;
    }

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

            Map<String, String> codeValue = new HashMap<>();
            for(Element ilChild : ulChildrens) {
                //taglia e disponibilità
                Elements childrens = ilChild.children();
                String size = childrens.get(0).text();
                String disp = childrens.get(1).text();
                //logger.info("{} {}", size, disp);

                codeValue = ilChild.dataset();

                res.append(size).append(" ").append(disp).append(" ");

                Bike bike = new Bike(title.text(), color.text(), size, disp, codeValue);
                if(size.contains("L") && !disp.substring(0,1).equals("0")) {
                    //size L e disponibilità diversa da 0
//                    logger.info("Size L o XL {}", size);
//                    logger.info(disp.substring(0,1));
//                    logger.info("{}", disp.substring(0,1).equals("0"));
                    feedbackController.sendInfo(bike.toString());
                } else {
                    logger.info("Taglia {} non disponibile ({})", size, disp);
                }
            }


        } catch (IOException e) {
            logger.info("Can't connect to: {}", url);
            e.printStackTrace();
        }
    }
}
