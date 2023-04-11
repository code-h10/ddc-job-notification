package com.binary.ddcjob.utils;

import com.slack.api.Slack;
import com.slack.api.webhook.WebhookResponse;

import java.io.IOException;

import static com.slack.api.model.block.Blocks.asBlocks;
import static com.slack.api.model.block.Blocks.section;
import static com.slack.api.model.block.composition.BlockCompositions.markdownText;
import static com.slack.api.model.block.composition.BlockCompositions.plainText;
import static com.slack.api.webhook.WebhookPayloads.payload;

public class SlackUtil {

    private static Slack slack = Slack.getInstance();

    public static WebhookResponse send(String webhookUrl, String content) throws IOException {
        return slack.send(webhookUrl, payload(p -> p.blocks(asBlocks(section(section -> section.text(markdownText(content)))))));
    }
}

