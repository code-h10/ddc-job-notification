package com.binary.ddcjob.service;

import com.slack.api.Slack;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.response.api.ApiTestResponse;
import com.slack.api.webhook.Payload;
import com.slack.api.webhook.WebhookResponse;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.Collections;

import static com.slack.api.model.block.Blocks.*;
import static com.slack.api.model.block.composition.BlockCompositions.markdownText;
import static com.slack.api.model.block.composition.BlockCompositions.plainText;
import static com.slack.api.model.block.element.BlockElements.asElements;
import static com.slack.api.model.block.element.BlockElements.button;
import static com.slack.api.webhook.WebhookPayloads.payload;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ScheduleServiceTest {

    private String webhookUrl;
    @Autowired private ScheduleService scheduleService;

    @BeforeEach
    void setUp() {
        webhookUrl = "https://hooks.slack.com/services/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";
    }

    @Disabled
    @Test
    public void should_returnDDCJobHtml_whenConnect() throws IOException {
        Document doc = Jsoup.connect("https://www.ddc.go.kr/ddc/selectGosiList.do?key=469&not_ancmt_se_code=05").get();
        Elements tr =  doc.select(".bbs_default > tbody > tr");

        String currentDate = LocalDate.now().toString();
        for (int index = tr.size()-1; index > 0; index--) {
            if(currentDate.equals(tr.get(index).firstElementChild())) {

            }
        }
    }

    @Disabled
    @Test
    public void should_checkValidSlack_WhenAfterSetUp() throws SlackApiException, IOException {
        Slack slack = Slack.getInstance();

        WebhookResponse response = slack.send(webhookUrl, payload(p -> p
                .blocks(asBlocks(section(section -> section.text(markdownText("⛰동두천시 일자리 채용 정보가 업데이트 되었습니다."))),
                        section(section -> section.text(plainText("https://www.ddc.go.kr/ddc/selectGosiList.do?key=469&not_ancmt_se_code=05")))
                ))
        ));
    }
}