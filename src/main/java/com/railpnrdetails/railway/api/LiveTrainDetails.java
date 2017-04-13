package com.railpnrdetails.railway.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class LiveTrainDetails {

	public JsonObject getLiveTrainDetails(HttpServletRequest request, HttpServletResponse response,
			String requestBody) {

		JsonObject responseData = new JsonObject();

		try {

			JsonObject requestData = new Gson().fromJson(requestBody.toString(), JsonObject.class);

			String trainNumber = requestData.get("TrainNumber").getAsString();
			String trainDate = requestData.get("TrainDate").getAsString();

			response.setHeader("TrainNumber", trainNumber);
			response.setHeader("TrainDate", trainDate);

			String apiKey = request.getHeader("apiKey");
			// Test service
			URL url = new URL(
					"http://api.railwayapi.com/live/train/" + trainNumber + "/doj/" + trainDate + "/apikey/" + apiKey);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			conn.setDoOutput(true);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type", "application/json");

			OutputStream os = conn.getOutputStream();
			os.flush();

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			StringBuilder data = new StringBuilder();
			while ((output = br.readLine()) != null) {

				data.append(output);

			}

			responseData = new Gson().fromJson(data.toString(), JsonObject.class);
			conn.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();

		}

		return responseData;
	}
}
