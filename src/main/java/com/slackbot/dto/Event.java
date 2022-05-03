package com.slackbot.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Event {

    private String type;
    private String user;
    private String text;
    private String ts;
    private String channel;

    @JsonProperty("event_ts")
    private String eventTs;
}
