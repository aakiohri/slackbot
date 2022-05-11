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
    private ParentDto parent;
    private String customfield_10030;
    private String customfield_10031;
    private Float customfield_10029;
    private String customfield_10032;
}
