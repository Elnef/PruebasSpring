package com.bytecode.core.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bytecode.core.components.PostComponent;
import com.bytecode.core.configuration.Paginas;
import com.bytecode.core.model.Post;

@Controller
@RequestMapping("/home")
public class ControllerBasic {
	private PostComponent _postComponent;
	
	@GetMapping(path = {"/posts", "/"})
	public String saludar(Model model){
		model.addAttribute("posts", this._postComponent.getPosts());		
		return "index"; //esto hace referencia a la web index.html
	}
 
	@GetMapping(path = {"/post","/post/p/{post}"})
	public ModelAndView getPostIndividual (
			//@RequestParam(defaultValue = "1", name="id", required=false) int id) // de esta forma se manda la variable de modo ...post?id=2
			@PathVariable(required = true, name = "post") int id) // de esta forma se manda escribiendo la dirección ...post/p/2 
			{ 
		ModelAndView modelAndView = new ModelAndView(Paginas.POST);
		
		List<Post> postFiltrado = this._postComponent.getPosts().stream().filter( (p) -> {return p.getId() == id;}).collect(Collectors.toList());
		
		modelAndView.addObject("post", postFiltrado.get(0));
		return modelAndView;
	}
}
