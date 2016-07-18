package com.justin.json;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

public class JSON {
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

}
