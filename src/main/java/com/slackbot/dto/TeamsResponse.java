package com.slackbot.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@JsonIgnoreProperties
@Accessors(chain = true)
public class TeamsResponse {
    private String message;
    private String text;

    public static TeamsResponse create(String text) {
        return new TeamsResponse()
                .setMessage("message")
                .setText(text);
    }
}
