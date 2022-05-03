package com.slackbot.service;

import com.slackbot.configuration.JiraConfiguration;
import com.slackbot.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class JiraIssueService implements IssueService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    JiraConfiguration configuration;

    @Override
    public String create(String text) {

        String[] textSplit = text.split("\\|");

        if (textSplit.length < 2) {
            log.error("text splitting don't have 2 pipe separated text");
            return "Invalid Text Message";
        }

        JiraRequest request = JiraRequest.create(textSplit[0], textSplit[1], configuration.getProjectKey());

        HttpHeaders header = new HttpHeaders();
        header.add("Authorization", configuration.getApiKey());

        return restTemplate.exchange(configuration.getBaseUrl() + "/rest/api/2/issue/", HttpMethod.POST,
                        new HttpEntity<>(request, header), JiraResponse.class)
                .getBody().getKey();
    }


}
