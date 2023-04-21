package com.binary.ddc.scheduler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;


@TestPropertySource(properties = { "crawling.ddc-library-url=http://211.46.124.148/EZ5500/SEAT/RoomStatus.aspx" })
@ExtendWith(SpringExtension.class)
class DDCLibraryScheduledTasksTest {

    @Value("${slack.webhook-url}")
    private String webhookUrl;

    @Value("${crawling.ddc-library-url}")
    private String ddcLibraryUrl;


    @Test
    public void should_ReturnDDCLibrary_WhenApiCall() throws IOException {
        Document doc = Jsoup.connect(ddcLibraryUrl).get();



    }
}