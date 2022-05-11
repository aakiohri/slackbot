package com.slackbot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JiraRequest {

    private Fields fields;

    public static JiraRequest create(String projectId, String name, String experience, String reqId, String resumeLink,
                                     String from) {
        return new JiraRequest(Fields.builder()
                .issuetype(new IssueType("Sub-task"))
                .project(new Project(projectId))
                .parent(new ParentDto(reqId))
                .summary(String.format("%s - %s YOE", name, experience))
                .description(resumeLink)
                .customfield_10029(Float.parseFloat(experience))
                .customfield_10030(from)
                .customfield_10031(name)
                .customfield_10032(resumeLink)
                .build());
    }
}
