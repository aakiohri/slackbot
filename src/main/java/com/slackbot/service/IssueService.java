package com.slackbot.service;

import com.slackbot.dto.TeamsResponse;

public interface IssueService {

    public TeamsResponse create(String[] text, String from);
}
