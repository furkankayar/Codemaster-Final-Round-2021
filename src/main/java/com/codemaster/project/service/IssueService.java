package com.codemaster.project.service;

import com.codemaster.project.network.dto.AllIssueTypeDTO;
import com.codemaster.project.network.dto.IssueTypeDTO;
import com.codemaster.project.network.service.APIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IssueService {

    @Autowired
    private APIService apiService;

    public IssueTypeDTO[] getIssueTypes(String jiraUrl){
        return apiService.getAllIssueTypes(jiraUrl);
    }
}
