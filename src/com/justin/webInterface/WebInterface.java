package com.justin.webInterface;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebInterface {
    public String getHTTPS(String https) {
        try {
            String urlResult = "";
            URL url = new URL(https);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                urlResult += line;
            }
            rd.close();
            return urlResult;
        } catch (Exception e) {
            return null;
        }
    }

    public String getHTTP(String http) {
        try {
            String urlResult = "";
            URL url = new URL(http);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                urlResult += line;
            }
            rd.close();
            return urlResult;
        } catch (Exception e) {
            return null;
        }
    }

}
