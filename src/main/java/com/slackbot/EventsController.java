package com.slackbot;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/event")
public class EventsController {

    @PostMapping
    public ResponseEntity<String> verification(@RequestBody EventRoot event) {
        return ResponseEntity.status(200).body(event.getChallenge());
    }
}
