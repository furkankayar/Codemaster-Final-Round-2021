package com.codemaster.project.controller;

import com.codemaster.project.Properties;
import com.codemaster.project.advice.RequestBodyEmptyException;
import com.codemaster.project.response.ProjectWithCount;
import com.codemaster.project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(
        value = Properties.API_VERSION + "/projects",
        method = {RequestMethod.POST})
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping("/find-min-n-issues")
    public List<ProjectWithCount> minNIssues(@RequestParam(required = false) String jiraUrl, @RequestParam(required=false) String minn, @RequestParam(required = false) String topm, @RequestBody String[] users) {
        if (jiraUrl == null || jiraUrl.equals("")) {
            throw new IllegalArgumentException("Required String parameter 'jiraUrl' is not present");
        }

        if (minn == null || minn.equals("")) {
            minn = "5";
        }

        if (topm == null || topm.equals("")) {
            topm = "10";
        }

        try {
            int minnInt = Integer.parseInt(minn);
            int topmInt = Integer.parseInt(topm);

            if (users == null) {
                throw new IllegalArgumentException("Required request body is missing");
            }

            if (users.length == 0){
                throw new RequestBodyEmptyException("users not found");
            }

            return projectService.findTopmProjects(jiraUrl, minnInt, topmInt,users);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Required Integer parameter cannot be parsed from given value");
        }

    }
}
