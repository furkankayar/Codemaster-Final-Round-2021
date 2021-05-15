package com.codemaster.project.service;

import com.codemaster.project.network.service.APIService;
import com.codemaster.project.response.Assignee;
import com.codemaster.project.response.Issue;
import com.codemaster.project.response.SubtaskTypeIssuesResponse;
import com.codemaster.project.response.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

@Service
public class UserService {

    @Autowired
    private APIService apiService;

    public List<User> findTopNUsers(String jiraUrl, int topn, String[] projectIds){

        Map<String, User> users = new HashMap<String, User>();

        for(String projectId : projectIds){
            try{
                SubtaskTypeIssuesResponse response = apiService.getSubtaskTypeIssues(jiraUrl, projectId);
                for(Issue issue : response.getIssues()){
                    Assignee assignee = issue.getFields().getAssignee();
                    if(assignee != null && assignee.getDisplayName() != null){
                        User user = users.get(assignee.getDisplayName());
                        if(user == null){
                            user = User.builder()
                                    .name(assignee.getName())
                                    .key(assignee.getKey())
                                    .emailAddress(assignee.getEmailAddress())
                                    .displayName(assignee.getDisplayName())
                                    .issueCount(1)
                                    .build();
                            users.put(user.getDisplayName(), user);
                        }
                        else{
                            user.setIssueCount(user.getIssueCount() + 1);
                        }
                    }
                }
            }
            catch(WebClientResponseException ignored){

            }
        }

        Stream<User> sortedUsers = users.values().stream().sorted(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return Integer.compare(o2.getIssueCount(), o1.getIssueCount());
            }
        });

        List<User> result = new ArrayList<User>();
        AtomicInteger count = new AtomicInteger();

        sortedUsers.forEach(user -> {
            if(count.get() < topn){
                result.add(user);
            }
            count.getAndIncrement();
        });

        return result;
    }

}
