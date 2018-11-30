package com.migration.rfsl;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
//import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.migration.rfsl.model.Planets;
import org.apache.commons.codec.binary.Base64;


 import com.fasterxml.jackson.databind.ObjectMapper;



 public class  RfslApplication {
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
             String webPage = "http://localhost:8080/alfresco/api/-default-/public/alfresco/versions/1/nodes/-root-/children ";
             String name = "admin";
             String password = "hiziggyc";

             String authString = name + ":" + password;
             System.out.println("auth string: " + authString);
             byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
             String authStringEnc = new String(authEncBytes);
             System.out.println("Base64 encoded auth string: " + authStringEnc);

             URL url = new URL(webPage);
             URLConnection urlConnection = url.openConnection();
             urlConnection.setRequestProperty("Authorization", "Basic " + authStringEnc);
             InputStream is = urlConnection.getInputStream();
             InputStreamReader isr = new InputStreamReader(is);

             int numCharsRead;
             char[] charArray = new char[1024];
             StringBuffer sb = new StringBuffer();
             while ((numCharsRead = isr.read(charArray)) > 0) {
                 sb.append(charArray, 0, numCharsRead);
             }
             String result = sb.toString();

             //System.out.println("*** BEGIN ***");
             //System.out.println(result);
             //System.out.println("*** END ***");
             return mapper.readValue(result.getEntity().getContent(), Planets.class);
         }

     }
