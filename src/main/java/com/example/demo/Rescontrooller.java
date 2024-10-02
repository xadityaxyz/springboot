package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@ResponseBody
@RestController
public class Rescontrooller {
	
	@GetMapping("/home")
	public String callingHome() {
		
		return "time to go home";
	}
	

}
