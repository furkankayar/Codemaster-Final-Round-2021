package com.codemaster.project.network.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllIssueTypeDTO {

    List<IssueTypeDTO> allIssueTypes = new ArrayList<>();

}
