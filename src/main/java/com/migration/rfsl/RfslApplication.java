package com.migration.rfsl;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.migration.rfsl.model.Planets;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;

public class RfslApplication {

    ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) {
        RfslApplication app = new RfslApplication();

        try {
            Planets planets = app.getPlanets();

            planets.getResults().forEach(p -> System.out.println(p.getName()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Planets getPlanets() throws IOException {
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet("https://swapi.co/api/planets");
        request.setHeader(HttpHeaders.ACCEPT, "application/json");
        HttpResponse response = client.execute(request);

        return mapper.readValue(response.getEntity().getContent(), Planets.class);
    }
}
