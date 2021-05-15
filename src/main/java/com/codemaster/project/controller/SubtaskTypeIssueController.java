package com.codemaster.project.controller;

import com.codemaster.project.Properties;
import com.codemaster.project.response.SubtaskTypeIssuesResponse;
import com.codemaster.project.service.SubtaskTypeIssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(
        value = Properties.API_VERSION + "/issues",
        method = {RequestMethod.GET})
public class SubtaskTypeIssueController {

    @Autowired
    private SubtaskTypeIssueService subtaskTypeIssueService;

    @GetMapping("/subtasks")
    public SubtaskTypeIssuesResponse getSubtasks(@RequestParam(required = false) String jiraUrl, @RequestParam(required = false) String projectId){

        if (jiraUrl == null || jiraUrl.equals("")) {
            throw new IllegalArgumentException("Required String parameter 'jiraUrl' is not present");
        }

        if (projectId == null || projectId.equals("")) {
            throw new IllegalArgumentException("Required String parameter 'projectId' is not present");
        }

        return subtaskTypeIssueService.getSubtaskTypeIssues(jiraUrl, projectId);
    }

}