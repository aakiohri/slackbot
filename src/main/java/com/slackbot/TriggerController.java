package com.slackbot;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/slackbot")
public class TriggerController {

    @PostMapping(value = "/verify", produces = "application/json")
    public ResponseEntity<Verification> verification(@RequestBody Verification verification) {
        Verification response = new Verification();
        response.setChallenge(verification.getChallenge());
        return ResponseEntity.status(200).body(response);
    }
}
