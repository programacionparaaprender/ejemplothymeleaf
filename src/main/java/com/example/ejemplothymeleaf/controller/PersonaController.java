package com.example.ejemplothymeleaf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.ejemplothymeleaf.model.Persona;
import com.example.ejemplothymeleaf.service.api.PersonaServiceAPI;
import com.example.ejemplothymeleaf.service.impl.PersonaServiceImpl;

import org.springframework.ui.Model;

@Controller
public class PersonaController {
	@Autowired
	private PersonaServiceImpl personaServiceAPI;
	
	
	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("list", personaServiceAPI.getAll());
		return "index";
	}
		
	@GetMapping("/save/{id}")
	public String showSave(@PathVariable("id")Long id, Model model) {
		if(id!=null && id != 0) {
			model.addAttribute("persona",personaServiceAPI.get(id));
		}else {
			model.addAttribute("persona",new Persona());
		}
		return "save";
	}
	@PostMapping("/save")
	public String save(Persona persona, Model model) {
		personaServiceAPI.save(persona);
		return "redirect:/";
	}
	@GetMapping("/delete/{id}")
	//@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id")Long id) {
		//if(id!=null && id != 0) {}
		personaServiceAPI.delete(id);
		return "redirect:/";
	}
}
