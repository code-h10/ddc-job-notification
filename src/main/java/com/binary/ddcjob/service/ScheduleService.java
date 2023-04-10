package com.binary.ddcjob.service;

import com.slack.api.Slack;
import com.slack.api.webhook.WebhookResponse;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;

import static com.slack.api.model.block.Blocks.asBlocks;
import static com.slack.api.model.block.Blocks.section;
import static com.slack.api.model.block.composition.BlockCompositions.markdownText;
import static com.slack.api.model.block.composition.BlockCompositions.plainText;
import static com.slack.api.webhook.WebhookPayloads.payload;

@Service
@Slf4j
public class ScheduleService {

    @Value("${slack.webhook-url}")
    private String webhookUrl;

    @Value("${crawling.ddc-url}")
    private String ddcUrl;

    private Slack slack = Slack.getInstance();

    @Scheduled(cron = "0 0 9-21 * * *")
    public void dongducheonJob() throws IOException {

        Document doc = Jsoup.connect(ddcUrl).get();
        Elements tr =  doc.select(".bbs_default > tbody > tr");

        String currentDate = LocalDate.now().toString();
        for (int index = tr.size()-1; index > 0; index--) {
            if(currentDate.equals(tr.get(index).firstElementChild())) {
                WebhookResponse response = slack.send(webhookUrl, payload(p -> p
                        .text("동두천시 일자리 채용 정보 업데이트")
                        .blocks(asBlocks(section(
                                section -> section.text(markdownText("⛰동두천시 일자리 채용 정보가 업데이트 되었습니다."))),
                                section(section -> section.text(plainText(ddcUrl)))
                        ))
                ));
            }
        }
    }
}
