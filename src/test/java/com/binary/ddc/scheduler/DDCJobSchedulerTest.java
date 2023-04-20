package com.binary.ddc.scheduler;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.slack.api.model.block.composition.BlockCompositions.markdownText;
import static com.slack.api.model.block.composition.BlockCompositions.plainText;

@SpringBootTest
class DDCJobSchedulerTest {

    private String webhookUrl;
    @Autowired private DDCJobScheduler DDCJobScheduler;

    @BeforeEach
    void setUp() {
        webhookUrl = "https://hooks.slack.com/services/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";
    }

    
}