package com.railpnrdetails.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;


@RestController
public class RailAPIController {




//	@RequestMapping(method = RequestMethod.GET, value = "/{userId}")
//	public String test(@PathVariable String userId, @PathVariable Long bookmarkId) {
//		return userId;
//	}

	@RequestMapping(value = "/api", method= RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> get() {
		
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("code", "200");
		return new ResponseEntity<Object>(response,HttpStatus.OK);
	}

}