/*
package com.migration.rfsl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.migration.rfsl.model.alfresco.ListObject;
import com.migration.rfsl.model.alfresco.ResponseResult;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.omg.CORBA.Object;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class RfslApplication {

    private static final Logger LOG = LoggerFactory.getLogger(RfslApplication.class);

    ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) {

        LOG.info("Performing the request...");

        RfslApplication app = new RfslApplication();
        try {

            ResponseResult result = app.performRequest(null);
//            int count = result.getList().getPagination().getCount();
//            int totalItems = result.getList().getPagination().getTotalItems();

            //LOG.info("Count is " + count);
            //LOG.info("Total items is " + totalItems);

            //for(int j = 0; j <2; j++) {
            String[] ID = app.rootNode(result);

            // ArrayList<ResponseResult> listOfChildrens = new ArrayList<ResponseResult>();


            for (int i = 0; i < result.getList().getPagination().getCount(); i++) {


                //LOG.info(ID[i]);
                ResponseResult resultss = app.performRequest(ID[i]);

                //listOfChildrens.add(resultss);

                app.rootNode(resultss);

                //String[] IDs = app.rootNode(result);

                //ap.add(app.performRequest(ID))
            }
            //}

            */
/*for(int i=0; i<result.getList().getPagination().getCount(); i++){
                String ID = result.getList().getEntries().get(i).getEntry().getId();

                LOG.info("names of folder are " + result.getList().getEntries().get(i).getEntry().getName());
                LOG.info("id" + result.getList().getEntries().get(i).getEntry().getId());

                ResponseResult ap  = app.performRequest(ID);

                *//*
*/
/*List<ResponseResult> ap = null;
                ap.add(app.performRequest(ID));*//*
*/
/*

                LOG.info("Count is " + ap.getList().getPagination().getCount());
                LOG.info("Total items is " + ap.getList().getPagination().getTotalItems());

                for(int j=0; j<ap.getList().getPagination().getCount(); j++) {

                    LOG.info("names of folder are " + ap.getList().getEntries().get(j).getEntry().getName());
                    LOG.info("id " + ap.getList().getEntries().get(j).getEntry().getId());
                }
            }*//*

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ResponseResult performRequest(String ID) throws IOException {
        if(ID == null ){
            ID = "-root-";
        }
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet("http://localhost:8080/alfresco/api/-default-/public/alfresco/versions/1/nodes/"+ID+"/children");

        //LOG.info("http://localhost:8080/alfresco/api/-default-/public/alfresco/versions/1/nodes/"+ID+"/children");
//        LOG.info("--------------------------------------");
//        LOG.info("subfolders");
//        LOG.info("--------------------------------------");

        request.setHeader(HttpHeaders.ACCEPT, "application/json");
        request.setHeader(HttpHeaders.AUTHORIZATION, "Basic YWRtaW46aGl6aWdneWM=");
        HttpResponse response = client.execute(request);
        return mapper.readValue(response.getEntity().getContent(), ResponseResult.class);
    }
    private String[] rootNode (ResponseResult result){

        String[] id = new String[result.getList().getPagination().getCount()];

        for(int i=0; i<result.getList().getPagination().getCount(); i++) {
            LOG.info("names of folder are " + result.getList().getEntries().get(i).getEntry().getName());
            LOG.info("id " + result.getList().getEntries().get(i).getEntry().getId());
            LOG.info("-------------------------------");


            id[i]  = result.getList().getEntries().get(i).getEntry().getId();

        }

        return id ;
    }


}
*/
