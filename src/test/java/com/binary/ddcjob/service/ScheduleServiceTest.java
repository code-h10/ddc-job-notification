package com.binary.ddcjob.service;

import com.slack.api.Slack;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.response.api.ApiTestResponse;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class ScheduleServiceTest {

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

    @Test
    public void should_checkValidSlack_WhenAfterSetUp() throws SlackApiException, IOException {
        Slack slack = Slack.getInstance();
        ApiTestResponse response = slack.methods().apiTest(r -> r.foo("bar"));
        System.out.println(response);
    }
}