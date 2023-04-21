package com.binary.ddc.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;

@Slf4j
@Service
public class DDCLibraryScheduledTasks {


    @Value("${slack.ddc-library-webhook-url}")
    private String ddcLibraryWebhookUrl;

    @Value("${crawling.ddc-library-url}")
    private String ddcLibraryUrl;

//    @Scheduled(cron = "0 0/10 * * * *")
    public void ddcLibrary() throws IOException {
        log.info("START >>>>>>>>> ");
        Document doc = Jsoup.connect(ddcLibraryUrl).get();
        Elements tr =  doc.select(".bbs_default > tbody > tr");
        String toDate = LocalDate.now().toString();

        log.info("END >>>>>>>>> ");
    }

}
