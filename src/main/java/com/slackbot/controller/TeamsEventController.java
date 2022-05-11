package com.slackbot.controller;

import com.slackbot.dto.TeamsEvent;
import com.slackbot.dto.TeamsResponse;
import com.slackbot.service.IssueService;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/teams")
public class TeamsEventController {

    @Autowired
    IssueService service;

    @PostMapping
    public ResponseEntity<TeamsResponse> verification(@RequestBody TeamsEvent event) {

        String text = "";

        if (Objects.nonNull(event.getText())) {
            text = Jsoup.parse(event.getText()).text().replaceFirst("referbuddy", "");
        }

        String[] split = text.split("\\|");

        if (split.length != 4) {
            return ResponseEntity.status(200).body(TeamsResponse.create("incorrect referral format, please try with correct format < name | experience | requirementId | resumeUrl > , exclude < >"));
        }

        TeamsResponse teamsResponse = service.create(split, event.getFrom().getName());

        log.info("response message : {}", teamsResponse.getMessage());

        return ResponseEntity.status(200).body(teamsResponse);
    }
}
