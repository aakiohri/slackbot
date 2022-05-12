package com.slackbot.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
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
    private String customfield_10038;
    private Status status;
}
