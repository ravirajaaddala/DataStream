package com.rra.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ScreenController {

	@RequestMapping(value = "/home")
	public String ush() {
		return "home";
	}
}
