package com.migration.rfsl.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Planets {
    String count;
    String next;
    String previous;
    List<Result> results;

    public String getCount() {
        return count;
    }

    public String getNext() {
        return next;
    }

    public String getPrevious() {
        return previous;
    }

    public List<Result> getResults() {
        return results;
    }
}
