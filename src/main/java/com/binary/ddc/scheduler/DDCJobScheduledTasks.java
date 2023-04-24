package com.binary.ddc.scheduler;

import com.binary.ddc.utils.SlackUtil;
import com.slack.api.model.block.LayoutBlock;
import kotlin.collections.ArrayDeque;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static com.slack.api.model.block.Blocks.*;
import static com.slack.api.model.block.composition.BlockCompositions.markdownText;
import static com.slack.api.model.block.composition.BlockCompositions.plainText;
import static com.slack.api.model.block.element.BlockElements.button;

@Slf4j
@Component
public class DDCJobScheduledTasks {

    @Value("${slack.webhook.ddc-job-url}")
    private String ddcJobWebhookUrl;

    @Value("${crawling.ddc-job-url}")
    private String ddcJobUrl;

    @Scheduled(cron = "0/10 * * * * *")
    public void ddcJob() throws IOException {
        log.info("START >>>>>>>>> ");
        Document doc = Jsoup.connect(ddcJobUrl).get();
        Elements tr =  doc.select(".bbs_default > tbody > tr");
        String toDate = LocalDate.now().toString();


        for (int index = tr.size()-1; index >= 0; index--) {
            log.info("toDay={}", toDate);
            log.info("jobInformation={}", tr.get(index));

            String contentText = tr.get(index).select("td > a").text();

            if(toDate.equals(tr.get(index).lastElementChild().text())) {
                List<LayoutBlock> layoutBlocks = new ArrayDeque<>();
                layoutBlocks.add(header(header -> header.text(plainText("🔔동두천시 신규 일자리 알림"))));
                layoutBlocks.add(divider());
                layoutBlocks.add(section(section -> section.text(markdownText(contentText)).accessory(button(btn -> btn.text(plainText("홈페이지")).url(ddcJobUrl).actionId("button-action")))));

                SlackUtil.send(ddcJobWebhookUrl, layoutBlocks);
            }
        }
        log.info("END >>>>>>>>> ");
    }
}
