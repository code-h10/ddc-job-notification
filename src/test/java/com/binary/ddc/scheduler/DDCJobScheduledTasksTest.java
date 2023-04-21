package com.binary.ddc.scheduler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestPropertySource(properties = { "crawling.ddc-job-url=https://www.ddc.go.kr/ddc/selectGosiList.do?key=469&not_ancmt_se_code=05" })
@ExtendWith(SpringExtension.class)
@SpringBootTest
class DDCJobScheduledTasksTest {

    @Value("${slack.ddc-job-webhook-url}")
    private String ddcJobWebhookUrl;

    @Value("${crawling.ddc-job-url}")
    private String ddcJobUrl;

    @Disabled
    @Test
    public void should_ReturnDDCJobHtml_WhenApiCall() throws IOException {

        Document doc = Jsoup.connect(ddcJobUrl).timeout(30000).get();
        Elements tr =  doc.select(".bbs_default > tbody > tr");
        String toDate = LocalDate.now().toString();


        int count = 0;
        for (int index = tr.size()-1; index >= 0; index--) {
            String contentText = tr.get(index).select("td > a").text();
            count++;

            assertNotNull(contentText);
        }

        assertEquals(count, 10);
    }

}