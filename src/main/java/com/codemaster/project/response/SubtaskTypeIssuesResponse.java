package com.codemaster.project.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubtaskTypeIssuesResponse {

    @JsonProperty("issues")
    List<Issue> issues = new ArrayList<Issue>();

}
