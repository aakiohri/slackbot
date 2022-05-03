package com.slackbot.controller;

import com.slackbot.dto.EventRoot;
import com.slackbot.service.IssueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/event")
public class EventsController {

    @Autowired
    IssueService service;

    @PostMapping
    public ResponseEntity<String> verification(@RequestBody EventRoot event) {
        if (Objects.nonNull(event.getChallenge())) {
            return ResponseEntity.status(200).body(event.getChallenge());
        }

        String response = Objects.nonNull(event.getEvent())
                ? service.create(event.getEvent().getText()) : "No Event Received";

        log.info("event response received : {}", response);

        return ResponseEntity.status(200).body(response);
    }
}
