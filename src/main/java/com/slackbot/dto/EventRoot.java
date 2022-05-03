package com.slackbot.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventRoot {

    private String challenge;
    private String token;
    private Event event;
    private String type;

    @JsonProperty("team_id")
    private String teamId;

    @JsonProperty("api_app_id")
    private String apiAppId;

    @JsonProperty("event_id")
    private String eventId;

    @JsonProperty("event_time")
    private Date eventTime;

    @JsonProperty("authed_users")
    private List<String> authedUsers;
}
