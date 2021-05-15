package com.codemaster.project.controller;

import com.codemaster.project.Properties;
import com.codemaster.project.network.dto.AllIssueTypeDTO;
import com.codemaster.project.network.dto.IssueTypeDTO;
import com.codemaster.project.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(
        value = Properties.API_VERSION + "/issuetypes",
        method = {RequestMethod.GET})
public class IssueController {

    @Autowired
    private IssueService issueService;

    @GetMapping("")
    public IssueTypeDTO[] getIssueTypes(@RequestParam(required = false) String jiraUrl){

        if (jiraUrl == null || jiraUrl.equals("")) {
            throw new IllegalArgumentException("Required String parameter 'jiraUrl' is not present");
        }

        return issueService.getIssueTypes(jiraUrl);
    }
}
