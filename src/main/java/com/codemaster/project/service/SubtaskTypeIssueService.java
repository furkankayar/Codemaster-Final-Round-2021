package com.codemaster.project.service;

import com.codemaster.project.network.service.APIService;
import com.codemaster.project.response.Issue;
import com.codemaster.project.response.SubtaskTypeIssuesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubtaskTypeIssueService {

    @Autowired
    private APIService apiService;

    public SubtaskTypeIssuesResponse getSubtaskTypeIssues(String jiraUrl, String projectId){
        SubtaskTypeIssuesResponse response = apiService.getSubtaskTypeIssues(jiraUrl, projectId);

        List<Issue> newIssues = new ArrayList<Issue>();
        for(Issue issue : response.getIssues()){
            if(issue.getFields().getIssueType().getSubtask()){
                newIssues.add(issue);
            }
        }

        response.setIssues(newIssues);

        return response;
    }
}
