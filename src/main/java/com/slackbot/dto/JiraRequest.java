package com.slackbot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JiraRequest {

    private Fields fields;

    public static JiraRequest create(String summary, String description, String projectKey) {
        return new JiraRequest(Fields.builder()
                .issuetype(new IssueType("Bug"))
                .project(new Project(projectKey))
                .summary(summary)
                .description(description)
                .build());
    }
}
