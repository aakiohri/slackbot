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
    RestTemplate restTemplate;

    @Autowired
    JiraConfiguration configuration;

    @Override
    public TeamsResponse create(String text, String from) {

        String[] split = text.split("\\|");

        if (split.length != 4) {
            return TeamsResponse.create("incorrect referral format, please try with correct format < name | experience | requirementId | resumeUrl > , exclude < >");
        }

        HttpHeaders header = getHttpHeaders();

        String checkIfKeyIsValidRequirementJQL = String.format("?jql=key=%s AND issuetype=Story", split[2].trim());

        Long total;
        try {
            total = restTemplate.exchange(configuration.getBaseUrl() + "/rest/api/2/search" + checkIfKeyIsValidRequirementJQL,
                            HttpMethod.GET,
                            new HttpEntity<>(null, header), JQLSearchResponse.class)
                    .getBody().getTotal();
        } catch (Exception ex) {
            return TeamsResponse.create("Invalid requirement ID or you don't have access to refer to this ID");
        }

        if (total != 1) {
            return TeamsResponse.create("Couldn't find a valid requirement by ID : " + split[2]);
        }

        JiraRequest request = JiraRequest.create(configuration.getProjectKey(), split[0], split[1], split[2].trim(), split[3], from);

        String key = restTemplate.exchange(configuration.getBaseUrl() + "/rest/api/2/issue/", HttpMethod.POST,
                        new HttpEntity<>(request, header), JiraResponse.class)
                .getBody().getKey();

        return TeamsResponse.create("Referral created with ID : " + key + ". Please keep this for id for your reference.");
    }


    public TeamsResponse status(String text, String from) {

        String[] split = text.split(":");

        if (split.length != 2) {
            return TeamsResponse.create("incorrect status retrieval format, please try with correct format < status : REF_ID > , exclude < >");
        }

        String checkIfKeyAndReferrerValidJQL = String.format("?jql=key=%s AND issuetype=Sub-task AND referrer~\"%s\"", split[1].trim(), from);

        JQLSearchResponse response;
        try {
            response = restTemplate.exchange(configuration.getBaseUrl() + "/rest/api/2/search" + checkIfKeyAndReferrerValidJQL,
                            HttpMethod.GET,
                            new HttpEntity<>(null, getHttpHeaders()), JQLSearchResponse.class)
                    .getBody();
        } catch (Exception ex) {
            return TeamsResponse.create("Couldn't find a valid referral STATUS for id: " + split[1]);
        }

        if (response.getTotal() != 1) {
            return TeamsResponse.create("Couldn't find valid referral STATUS for id : " + split[1]);
        }

        String status = response.getIssues().get(0).getFields().getStatus().getName();
        return TeamsResponse.create("Status for your referral : " + status);
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders header = new HttpHeaders();
        header.add("Authorization", configuration.getApiKey());
        return header;
    }


}
