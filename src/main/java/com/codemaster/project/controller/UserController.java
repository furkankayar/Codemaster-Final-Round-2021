package com.codemaster.project.controller;

import com.codemaster.project.Properties;
import com.codemaster.project.advice.RequestBodyEmptyException;
import com.codemaster.project.response.User;
import com.codemaster.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(
        value = Properties.API_VERSION + "/users",
        method = {RequestMethod.POST})
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/find-top-n-users")
    public List<User> findTopNUsers(@RequestParam(required = false) String jiraUrl, @RequestParam(required = false) String topn, @RequestBody String[] projectIds){

        if (jiraUrl == null || jiraUrl.equals("")) {
            throw new IllegalArgumentException("Required String parameter 'jiraUrl' is not present");
        }

        if (topn == null || topn.equals("")) {
            throw new IllegalArgumentException("Required Integer parameter 'topn' is not present");
        }

        try{
            int topnInt = Integer.parseInt(topn);
            if(topnInt <= 0){
                throw new IllegalArgumentException("Required Integer parameter 'topn' must be positive'"+ topn +"'");
            }

            if(projectIds == null){
                throw new IllegalArgumentException("Required request body is missing");
            }

            if(projectIds.length == 0){
                throw new RequestBodyEmptyException("projects not found");
            }

            return userService.findTopNUsers(jiraUrl, topnInt, projectIds);
        }
        catch(NumberFormatException ex){
            throw new IllegalArgumentException("Required Integer parameter 'topn' cannot be parsed from given value'"+ topn +"'");
        }
    }
}
