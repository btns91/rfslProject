package com.migration.rfsl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.migration.rfsl.model.alfresco.ListObject;
import com.migration.rfsl.model.alfresco.RootObject;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
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

            RootObject result = app.performRequest();

            LOG.info("Count is " + result.getList().getPagination().getCount());
            LOG.info("Total items is " + result.getList().getPagination().getTotalItems());
            LOG.info("names of folder are " + result.getList().getEntries());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private RootObject performRequest() throws IOException {

        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet("http://localhost:8080/alfresco/api/-default-/public/alfresco/versions/1/nodes/-root-/children");
        request.setHeader(HttpHeaders.ACCEPT, "application/json");
        //auti i grammi einai to provlima. den kanei swsto base64 encoding
        //String authenticationString = new String("Basic " + Base64.encodeBase64("admin:hiziggyc".getBytes()));
        //LOG.info("Authenticating with: " + authenticationString);
        request.setHeader(HttpHeaders.AUTHORIZATION, "Basic YWRtaW46aGl6aWdneWM=");
        HttpResponse response = client.execute(request);
        //Auto tha tuposei auto pou tha epistrepsei to url
        //LOG.info("Got Response Back: " + EntityUtils.toString(response.getEntity()));
        //LOG.info("Http response code " + response.getStatusLine().getStatusCode());
        return mapper.readValue(response.getEntity().getContent(), RootObject.class);
    }
}
