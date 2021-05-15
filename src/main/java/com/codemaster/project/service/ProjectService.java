package com.codemaster.project.service;

import com.codemaster.project.network.service.APIService;
import com.codemaster.project.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProjectService {

    @Autowired
    private APIService apiService;

    public List<ProjectWithCount> findTopmProjects(String jiraUrl, int minn, int topm, String[] users){

        Map<String, ProjectWithCount> projects = new HashMap<String, ProjectWithCount>();

        try{
            SubtaskTypeIssuesResponse response = apiService.getAllProjects(jiraUrl);

            for(Issue issue : response.getIssues()){
                Assignee assignee = issue.getFields().getAssignee();
                if(assignee == null) continue;
                for(String user : users){
                    if(assignee.getEmailAddress() != null && user.equals(assignee.getEmailAddress())
                            || (assignee.getKey() != null && user.equals(assignee.getKey()))
                            || (assignee.getName() != null && user.equals(assignee.getName()))
                            || (assignee.getDisplayName() != null && user.equals(assignee.getDisplayName()))){
                        Project project = issue.getFields().getProject();
                        ProjectWithCount projectWithCount = projects.get(project.getKey());
                        if(projectWithCount == null){
                            projectWithCount = ProjectWithCount.builder()
                                    .key(project.getKey())
                                    .id(project.getId())
                                    .name(project.getName())
                                    .issueCount(1)
                                    .build();
                            projects.put(projectWithCount.getKey(), projectWithCount);
                        } else {
                            projectWithCount.setIssueCount(projectWithCount.getIssueCount() + 1);
                        }
                    }
                    break;
                }
            }
        }
        catch(Exception ignored){
        }

        List<ProjectWithCount> result = new ArrayList<ProjectWithCount>();

        projects.values().forEach(projectWithCount -> {
            if(projectWithCount.getIssueCount() > minn){
                result.add(projectWithCount);
            }
        });

        result.sort(new Comparator<ProjectWithCount>() {
            @Override
            public int compare(ProjectWithCount o1, ProjectWithCount o2) {
                return Integer.compare(o2.getIssueCount(), o1.getIssueCount());
            }
        });

        return result.subList(0, Math.min(result.size(), topm));

    }

}
