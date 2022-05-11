package com.slackbot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JiraRequest {

    private Fields fields;

    public static JiraRequest create(String projectId, String name, String experience, String reqId, String resumeLink) {
        return new JiraRequest(Fields.builder()
                .issuetype(new IssueType("Subtask"))
                .project(new Project(projectId))
                .parent(new ParentDto(reqId))
                .summary(String.format("%s - %s YOE", name, experience))
                .description(resumeLink)
                .build());
    }
}
