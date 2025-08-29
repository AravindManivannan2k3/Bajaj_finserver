package com.example.bajaj.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FinalQueryRequest {
    @JsonProperty("finalQuery")
    private String finalQuery;
}
