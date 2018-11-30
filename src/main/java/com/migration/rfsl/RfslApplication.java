package com.migration.rfsl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.migration.rfsl.model.alfresco.ListObject;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


public class RfslApplication {

    private static final Logger LOG = LoggerFactory.getLogger(RfslApplication.class);

    ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) {

        LOG.info("Performing the request...");

        RfslApplication app = new RfslApplication();
        try {

            ListObject result = app.performRequest();

            LOG.info("Count is " + result.getPagination().getCount());
            LOG.info("Total items is " + result.getPagination().getTotalItems());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ListObject performRequest() throws IOException {

        CloseableHttpClient client = HttpClients.createDefault();

        HttpGet request = new HttpGet("http://localhost:8080/alfresco/api/-default-/public/alfresco/versions/1/nodes/-root-/children");
        request.setHeader(HttpHeaders.ACCEPT, "application/json");

        String authenticationString = new String("Basic " + Base64.encodeBase64("admin:hiziggyc".getBytes()));

        LOG.info("Authenticating with: " + authenticationString);

        request.setHeader(HttpHeaders.AUTHORIZATION, authenticationString);

        HttpResponse response = client.execute(request);

        return mapper.readValue(response.getEntity().getContent(), ListObject.class);
    }
}
