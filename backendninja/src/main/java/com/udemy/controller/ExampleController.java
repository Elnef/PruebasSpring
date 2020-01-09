package com.udemy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.udemy.component.ExampleComponent;
import com.udemy.service.ExampleService;

@Controller
@RequestMapping("/example")
public class ExampleController {
	
	public static final String EXAMPLE_VIEW = "example";
	
	@Autowired
	@Qualifier("exampleService")
	private ExampleService exampleService;
	
	@Autowired  //Se usa para indicar a spring que es un component existente
	@Qualifier("exampleComponent")  //el didentificador
	private ExampleComponent exampleComponent;
	
	//Primera forma. ES buena cuando se insertan pocos datos
	//@RequestMapping(value="/exampleString", method=RequestMethod.GET)  Es lo mismo que la siguente en versiones anteriores
	@GetMapping("/exampleString")
	public String exampleString(Model model) {
		exampleComponent.sayHello();
		model.addAttribute("people", exampleService.getListPeople());
		return EXAMPLE_VIEW;
	}

	//Segunda forma. Esta es mejor si vamos a insertar muchos datos
	//@RequestMapping(value="/exampleMAV", method=RequestMethod.GET) Es lo mismo que la siguente en versiones anteriores
	@GetMapping("/exampleMAV")
	public ModelAndView exampleMAV() {
		ModelAndView mav = new ModelAndView(EXAMPLE_VIEW);
		mav.addObject("people", exampleService.getListPeople());
		
		return mav;
	}
	
	
}
