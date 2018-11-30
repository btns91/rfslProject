package com.migration.rfsl.model.alfresco;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ListObject {

    Pagination pagination;

    List<AlfrescoEntry> entries;

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public List<AlfrescoEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<AlfrescoEntry> entries) {
        this.entries = entries;
    }
}
