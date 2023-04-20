package com.binary.ddc.service;

import com.slack.api.Slack;
import com.slack.api.methods.SlackApiException;
import com.slack.api.webhook.WebhookResponse;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.time.LocalDate;

import static com.slack.api.model.block.Blocks.*;
import static com.slack.api.model.block.composition.BlockCompositions.markdownText;
import static com.slack.api.model.block.composition.BlockCompositions.plainText;
import static com.slack.api.webhook.WebhookPayloads.payload;

@SpringBootTest
class ScheduleServiceTest {

    private String webhookUrl;
    @Autowired private ScheduleService scheduleService;

    @BeforeEach
    void setUp() {
        webhookUrl = "https://hooks.slack.com/services/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";
    }

    
}