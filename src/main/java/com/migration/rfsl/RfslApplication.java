package com.migration.rfsl;


import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RfslApplication {

    public static void main(String[] args) {

        try {
            performGetRequest();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static void performGetRequest() throws IOException {
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet("http://www.google.com");
        HttpResponse response = client.execute(request);

        BufferedReader rd = new BufferedReader
                (new InputStreamReader(
                        response.getEntity().getContent()));

        StringBuilder textView = new StringBuilder();
        String line = "";
        while ((line = rd.readLine()) != null) {
            textView.append(line);
        }

        System.out.println(textView);
    }
}
