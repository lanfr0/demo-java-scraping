package it.lan.demojavascraping.task;

import it.lan.demojavascraping.service.ScrapeBike;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@EnableAsync
@EnableScheduling
@Service
public class ScheduledTask {
    private final Logger logger = LoggerFactory.getLogger(ScheduledTask.class);

    @Autowired
    private ScrapeBike scrapeBike;

    @Async
    @Scheduled(fixedRate = 90000)
    public void runTask() {
        scrapeBike.getAvailability();
    }
}
