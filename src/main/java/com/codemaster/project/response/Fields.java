package com.codemaster.project.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Fields {

    @JsonProperty("issuetype")
    private IssueType issueType;

    @JsonProperty("assignee")
    private Assignee assignee;

    @JsonProperty("reporter")
    private Assignee reporter;

    @JsonProperty("project")
    private Project project;

    public IssueType getIssueType() {
        return issueType;
    }
}
