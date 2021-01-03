package com.emmanuel.plumas.p7JavaLibrarEasyWEB.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController extends CommonController{

	@GetMapping(value="/")
	public String homePage(Model model){
		String userLastName=getPrincipal();
	 	model.addAttribute("principal",userLastName);
		return "accueil";
	}
	
}
