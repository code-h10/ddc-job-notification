package com.binary.ddcjob.service;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;

@Service
@Slf4j
public class ScheduleService {

    @Scheduled(cron = "0 0 9-21 * * *")
    public void dongducheonJob() throws IOException {

        Document doc = Jsoup.connect("https://www.ddc.go.kr/ddc/selectGosiList.do?key=469&not_ancmt_se_code=05").get();
        Elements tr =  doc.select(".bbs_default > tbody > tr");

        String currentDate = LocalDate.now().toString();
        for (int index = tr.size()-1; index > 0; index--) {
            if(currentDate.equals(tr.get(index).firstElementChild())) {

            }
        }
    }
}
