package com.migration.rfsl.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Result {
    String name;

    public String getName() {
        return name;
    }
}
