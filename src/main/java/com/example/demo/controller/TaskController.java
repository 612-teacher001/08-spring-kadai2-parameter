package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TaskController {
	@GetMapping("/sp20")
	public String index() {
		return "taskLinks";
	}
}
