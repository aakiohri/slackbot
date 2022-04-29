package com.slackbot;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/slackbot")
public class EventsController {

    @PostMapping(value = "/verify")
    public ResponseEntity<String> verification(@RequestBody Verification verification) {
        return ResponseEntity.status(200).body(verification.getChallenge());
    }
}
