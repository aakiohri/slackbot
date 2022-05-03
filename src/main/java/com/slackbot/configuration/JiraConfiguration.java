package com.slackbot.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class JiraConfiguration {

    @Value("${jira.apiKey}")
    private String apiKey;

    @Value("${jira.url}")
    private String baseUrl;

    @Value("${jira.projectKey}")
    private String projectKey;
}
