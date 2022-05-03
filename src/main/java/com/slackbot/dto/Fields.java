package com.slackbot.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Fields {

    private String summary;
    private IssueType issuetype;
    private Project project;
    private String description;
}
