package com.udemy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/example4")
public class Example4Controller {
	
	private static final String VIEW_404= "404";
	private static final String VIEW_500= "500";
	
	@GetMapping("/showerror1")
	public String showError1() {
		return VIEW_404;
	}

	@GetMapping("/showerror2")
	public String showError2() {
		return VIEW_500;
	}
}
