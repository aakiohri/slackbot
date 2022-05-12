package com.slackbot.controller;

import com.slackbot.dto.TeamsEvent;
import com.slackbot.dto.TeamsResponse;
import com.slackbot.service.IssueService;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/teams")
public class TeamsEventController {

    @Autowired
    IssueService service;

    @PostMapping
    public ResponseEntity<TeamsResponse> verification(@RequestBody TeamsEvent event) {

        String text = Objects.isNull(event.getText()) ? "" :
                Jsoup.parse(event.getText()).text().replaceFirst("referbuddy", "");

        log.info("Text after html parsing : {}", text);

        TeamsResponse teamsResponse = text.trim().toLowerCase().contains("status") ?
                service.status(text, event.getFrom().getName()) :
                service.create(text, event.getFrom().getName());

        log.info("response message : {}", teamsResponse.getMessage());

        return ResponseEntity.status(200).body(teamsResponse);
    }


    @GetMapping
    public String hello() {
        return "hello";
    }
}
