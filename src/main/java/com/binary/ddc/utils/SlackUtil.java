package com.binary.ddc.utils;

import com.slack.api.Slack;
import com.slack.api.model.block.LayoutBlock;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;

import static com.slack.api.model.block.composition.BlockCompositions.markdownText;
import static com.slack.api.model.block.composition.BlockCompositions.plainText;
import static com.slack.api.webhook.WebhookPayloads.payload;

@Slf4j
public class SlackUtil {

    private static Slack slack = Slack.getInstance();

    public static void send(String webhookUrl, List<LayoutBlock> layoutBlocks) throws IOException {
        try {
            slack.send(webhookUrl, payload(p -> p.blocks(layoutBlocks)));
        } catch (Exception e) {
            log.error("Slack Error Message={}", e);
        }
        
    }
}

