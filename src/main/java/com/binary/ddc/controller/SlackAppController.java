package com.binary.ddc.controller;

import com.binary.ddc.service.SlackAppService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SlackAppController {

    private final SlackAppService slackAppService;

    @PostMapping(value = "/slack/events", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> receiveEvents(@RequestBody Map<String, Object> data) {


        return ResponseEntity.ok(data.get("challenge").toString());
    }

}
