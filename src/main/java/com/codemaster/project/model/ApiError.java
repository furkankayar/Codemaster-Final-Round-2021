package com.codemaster.project.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
@Builder
public class ApiError {
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date timestamp;
    @JsonProperty("status")
    private Integer status;
    @JsonProperty("errors")
    private String errors;
}
