package com.migration.rfsl.model.alfresco;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AlfrescoEntry {
    EntryData entry;

    public EntryData getEntry() {
        return entry;
    }

    public void setEntry(EntryData entry) {
        this.entry = entry;
    }
}
