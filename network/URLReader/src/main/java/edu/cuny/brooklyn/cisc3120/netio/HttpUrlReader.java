package edu.cuny.brooklyn.cisc3120.netio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUrlReader 
{
    public static void main( String[] args ) throws IOException
    {
        if (args.length != 1) {
            System.out.println("Usage: HttpUrlReader http_url");
            return;
        }
        URL url;

        url = new URL(args[0]);
        
        HttpURLConnection httpConnection = (HttpURLConnection)url.openConnection();
        httpConnection.setDoOutput(true);

        httpConnection.setRequestMethod("GET");
        httpConnection.setRequestProperty("User-Agent", "Java Web Client");
        int responseCode = httpConnection.getResponseCode();
        String reponseMessage = httpConnection.getResponseMessage();
        System.out.println("Response Code: " + responseCode);
        System.out.println("Response Message: " + reponseMessage);
        
        try (BufferedReader in = 
                responseCode >= 200 && responseCode < 400
                        ? new BufferedReader(new InputStreamReader(httpConnection.getInputStream()))
                        : new BufferedReader(new InputStreamReader(httpConnection.getErrorStream()))) {
          String line = "";
          while ((line = in.readLine()) != null) {
              System.out.println(line);
          }
        }
    }
}
