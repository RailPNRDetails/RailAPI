package com.railpnrdetails.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import org.springframework.http.HttpStatus;

@RestController
public class RailAPIController {
	
	public static final String JSON_MEDIA_TYPE = "application/json";

	@RequestMapping(value = "/api", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> get() {

		Map<String, Object> response = new HashMap<String, Object>();
		response.put("code", "200");
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

	
	/*
	 * @param headers
	 * @param request
	 * @return String
	 * @throws
	 */
	@RequestMapping(value = "/api/cancelledTrains/{ID}", method= RequestMethod.GET, consumes = JSON_MEDIA_TYPE, produces = JSON_MEDIA_TYPE)
	@ResponseBody
	public void getcancelledTrainsList(@PathVariable("ID") String ID, HttpServletRequest request, HttpServletResponse response) throws JsonSyntaxException, IOException {

		Gson gson = new Gson();
		
		JsonObject responseData = new JsonObject();
		responseData.addProperty("ID", ID);
		
		gson.fromJson(responseData, JsonObject.class).getAsJsonObject().toString().getBytes();
		response.setContentType(JSON_MEDIA_TYPE);
		
		try{
			response.getOutputStream().write(gson.fromJson(responseData, JsonObject.class).toString().getBytes());
		}finally {
			response.getOutputStream().close();
		}
		
	}
	
	/*
	 * @param headers
	 * @param request
	 * @return String
	 * @throws
	 */
	@RequestMapping(value = "/api/liveTrainStatus", method= RequestMethod.POST, consumes = JSON_MEDIA_TYPE, produces = JSON_MEDIA_TYPE)
	@ResponseBody
	public void getLiveTrainsStatusList(HttpServletRequest request, HttpServletResponse response) throws JsonSyntaxException, IOException {

		Gson gson = new Gson();
		
		
		JsonObject responseData = testService( request,  response);
		
		
		
		//gson.fromJson(responseData, JsonObject.class).getAsJsonObject().toString().getBytes();
		response.setContentType(JSON_MEDIA_TYPE);
		
		try{
			response.getOutputStream().write(gson.fromJson(responseData, JsonObject.class).toString().getBytes());
		}finally {
			response.getOutputStream().close();
		}
		
	}
	
	private JsonObject testService(HttpServletRequest request, HttpServletResponse response){
		
		JsonObject responseData = new JsonObject();
	try {

			int trainNumber = 12345;
			String date = "20170411"; //YYYYMMDD
			//Test service
			URL url = new URL("http://api.railwayapi.com/live/train/"+trainNumber+"/doj/"+date+"/apikey/0vfjouj0");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			
			Gson gson = new Gson();
		
			conn.setDoOutput(true);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type", "application/json");


			OutputStream os = conn.getOutputStream();
			os.flush();

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {

				responseData.addProperty("ResponseFromAPI", output);
				System.out.println(responseData);
			}

			conn.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();
		} catch (IOException e) {
 
			e.printStackTrace();

		}
	
	return responseData;
	}
	
	 

}