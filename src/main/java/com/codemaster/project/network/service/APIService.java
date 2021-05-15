package com.codemaster.project.network.service;

import com.codemaster.project.network.dto.AllIssueTypeDTO;
import com.codemaster.project.network.dto.IssueTypeDTO;
import com.codemaster.project.response.SubtaskTypeIssuesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class APIService {

    @Autowired
    private WebClient webClient;

    public IssueTypeDTO[] getAllIssueTypes(String url){
        return webClient
                .get()
                .uri(url+"/rest/api/2/issuetype")
                .retrieve()
                .bodyToMono(IssueTypeDTO[].class)
                .block();
    }

    public SubtaskTypeIssuesResponse getSubtaskTypeIssues(String url, String id){
        return webClient
                .get()
                .uri(url+ "/rest/api/2/search?jql=project="+id+"&maxResults=1000")
                .retrieve()
                .bodyToMono(SubtaskTypeIssuesResponse.class)
                .block();
    }

    public SubtaskTypeIssuesResponse getAllProjects(String url){
        return webClient
                .get()
                .uri(url+ "/rest/api/2/search?jql=&maxResults=10000")
                .retrieve()
                .bodyToMono(SubtaskTypeIssuesResponse.class)
                .block();
    }
}
