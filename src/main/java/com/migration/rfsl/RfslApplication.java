package com.migration.rfsl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.migration.rfsl.model.alfresco.EntryData;
import com.migration.rfsl.model.alfresco.ResponseResult;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;


public class RfslApplication {

    private static final Logger LOG = LoggerFactory.getLogger(RfslApplication.class);

    ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {

        LOG.info("Performing the request...");

        RfslApplication app = new RfslApplication();

        app.migrateTheLesbians();
    }

    private void migrateTheLesbians() throws IOException {

            EntryData rootFolder = new EntryData();
            rootFolder.setId("-root-");
            rootFolder.setName("Root Folder");

            parseFolders(asList(rootFolder));
    }

    private void parseFolders(List<EntryData> folders) throws IOException {

        for (EntryData folder : folders) {

            LOG.info("Getting content for folder with name [{}] and ID [{}]", folder.getName(), folder.getId());

            ResponseResult result = getFolderContents(folder.getId());

            List<EntryData> nextFolderIteration = new ArrayList<>();

            result.getList().getEntries().forEach(entry -> {
                EntryData entryData = entry.getEntry();
                if (entryData.getFolder()) {
                    nextFolderIteration.add(entryData);
                } else if (entryData.getFile()) {
                    // Edw mporeis na kaneis construct kai print to download url gia to arxeio pou vrikes
                    LOG.info("Found file with name [{}] and ID [{}]", entryData.getName(), entryData.getId());
                } else {
                    LOG.error("The following item is neither a folder nor a file!?!? Name [{}] ID [{}]", entryData.getName(), entryData.getId());
                }
            });

            parseFolders(nextFolderIteration);
        }
    }

    private ResponseResult getFolderContents(String folder) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet("http://localhost:8080/alfresco/api/-default-/public/alfresco/versions/1/nodes/" + folder + "/children");
        request.setHeader(HttpHeaders.ACCEPT, "application/json");
        request.setHeader(HttpHeaders.AUTHORIZATION, "Basic YWRtaW46aGl6aWdneWM=");
        HttpResponse response = client.execute(request);
        return mapper.readValue(response.getEntity().getContent(), ResponseResult.class);
    }
}
