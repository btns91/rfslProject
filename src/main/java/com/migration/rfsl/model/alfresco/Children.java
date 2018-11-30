package com.migration.rfsl.model.alfresco;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Children {
    ListObject list;

    public ListObject getList() {
        return list;
    }

    public void setList(ListObject list) {
        this.list = list;
    }
}
