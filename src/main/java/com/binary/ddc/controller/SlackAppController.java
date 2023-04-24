package com.binary.ddc.controller;

import com.slack.api.bolt.App;
import com.slack.api.bolt.AppConfig;
import com.slack.api.bolt.servlet.SlackAppServlet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.annotation.WebServlet;

@Slf4j
@RestController
@WebServlet("/slack/events")
public class SlackAppController extends SlackAppServlet {

    public SlackAppController(App app) {
        super(app);
    }
}
