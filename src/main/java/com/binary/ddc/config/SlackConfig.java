package com.binary.ddc.config;

import com.slack.api.app_backend.events.payload.EventsApiPayload;
import com.slack.api.bolt.App;
import com.slack.api.bolt.AppConfig;
import com.slack.api.bolt.context.builtin.EventContext;
import com.slack.api.bolt.handler.BoltEventHandler;
import com.slack.api.bolt.response.Response;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.response.chat.ChatPostMessageResponse;
import com.slack.api.model.event.AppMentionEvent;
import com.slack.api.model.event.ReactionAddedEvent;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class SlackConfig {

    @Bean
    public App initSlackApp() {
        AppConfig appConfig = AppConfig.builder().signingSecret("a79b0dbae8c8e281f765f3795c62570f").singleTeamBotToken("xoxb-724594805526-5153240438033-3ObNPFVFT1SLhpn8prJM77o0").build();
        App app = new App(appConfig);

        app.event(AppMentionEvent.class, (payload, ctx) -> {
            AppMentionEvent event = payload.getEvent();
            ChatPostMessageResponse message = ctx.client().chatPostMessage(r -> r
                    .channel(event.getChannel())
                    .threadTs(event.getThreadTs())
                    .text("<@" + event.getUser() + "> Thank you! We greatly appreciate your efforts :two_hearts:"));
            if (!message.isOk()) {
                ctx.logger.error("chat.postMessage failed: {}", message.getError());
            }
            return ctx.ack();
        });
        return app;
    }
}
