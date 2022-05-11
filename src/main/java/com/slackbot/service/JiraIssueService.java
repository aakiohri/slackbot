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
    public TeamsResponse create(String[] text, String from) {

        HttpHeaders header = new HttpHeaders();
        header.add("Authorization", configuration.getApiKey());

        String checkIfKeyIsValidRequirementJQL = String.format("?jql=key=%s AND issuetype=Story", text[2]);

        Long total;
        try {
            total = restTemplate.exchange(configuration.getBaseUrl() + "/rest/api/2/search" + checkIfKeyIsValidRequirementJQL,
                            HttpMethod.GET,
                            new HttpEntity<>(null, header), JQLSearchResponse.class)
                    .getBody().getTotal();
        } catch (Exception ex) {
            return TeamsResponse.create("Invalid requirement ID or you dont have access to refer to this ID");
        }

        if (total != 1) {
            return TeamsResponse.create("Couldn't find a valid requirement by ID : " + text[2]);
        }

        JiraRequest request = JiraRequest.create(configuration.getProjectKey(), text[0], text[1], text[2].trim(), text[3], from);

        String key = restTemplate.exchange(configuration.getBaseUrl() + "/rest/api/2/issue/", HttpMethod.POST,
                        new HttpEntity<>(request, header), JiraResponse.class)
                .getBody().getKey();

        return TeamsResponse.create("Referral created with ID : " + key + ". Please keep this for id for your reference.");
    }


}
