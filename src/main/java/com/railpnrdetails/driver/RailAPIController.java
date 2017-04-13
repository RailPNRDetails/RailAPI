package com.railpnrdetails.driver;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.railpnrdetails.railway.api.LiveTrainDetails;

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
	 * 
	 * @param request
	 * 
	 * @return String
	 * 
	 * @throws
	 */
	@RequestMapping(value = "/api/cancelledTrains/{ID}", method = RequestMethod.GET, consumes = JSON_MEDIA_TYPE, produces = JSON_MEDIA_TYPE)
	@ResponseBody
	public void getcancelledTrainsList(@PathVariable("ID") String ID, HttpServletRequest request,
			HttpServletResponse response) throws JsonSyntaxException, IOException {

		Gson gson = new Gson();

		JsonObject responseData = new JsonObject();
		responseData.addProperty("ID", ID);

		gson.fromJson(responseData, JsonObject.class).getAsJsonObject().toString().getBytes();
		response.setContentType(JSON_MEDIA_TYPE);

		try {
			response.getOutputStream().write(gson.fromJson(responseData, JsonObject.class).toString().getBytes());
		} finally {
			response.getOutputStream().close();
		}

	}

	/*
	 * @param headers
	 * 
	 * @param request
	 * 
	 * @return String
	 * 
	 * @throws
	 */
	@RequestMapping(value = "/api/liveTrainStatus", method = RequestMethod.POST, consumes = JSON_MEDIA_TYPE, produces = JSON_MEDIA_TYPE)
	@ResponseBody
	public void getLiveTrainsStatusList(@RequestBody String requestBody, HttpServletRequest request,
			HttpServletResponse response) throws JsonSyntaxException, IOException {

		Gson gson = new Gson();
		LiveTrainDetails trainDetails = new LiveTrainDetails();

		JsonObject responseData = trainDetails.getLiveTrainDetails(request, response, requestBody);

		response.setContentType(JSON_MEDIA_TYPE);

		try {
			response.getOutputStream().write(gson.fromJson(responseData, JsonObject.class).toString().getBytes());
		} finally {
			response.getOutputStream().close();
		}

	}

}