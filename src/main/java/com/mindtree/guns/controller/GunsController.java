package com.mindtree.guns.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.mindtree.guns.entity.Guns;
import com.mindtree.guns.service.GunsService;

@RestController
public class GunsController {
	
	@Autowired
	GunsService gunsService;
	
	
	@RequestMapping("/index")
	public ModelAndView indexPage() {
		return new ModelAndView("index");
	}
	@RequestMapping(value = "/addGun", method = RequestMethod.POST)
	public ModelAndView addEmployee(@ModelAttribute("employee") @Validated Guns gun, Model model)
	{
		gunsService.add(gun);
		return new ModelAndView("display", "message", "Add Successfully");
	}
	
	
	@RequestMapping(value = "/deleteGun", method = RequestMethod.POST)
	public ModelAndView deleteEmployee(Guns gun, Model model) {
		
		gunsService.delete(gun.getName());
		return new ModelAndView("display", "message", "Deleted Successfully");
	}
	
	@RequestMapping(value = "/getByName", method = RequestMethod.POST)
	public ModelAndView getEmployeeById(Guns gun, Model model) {
		Guns g = gunsService.getById(gun.getName());
		List<Guns> guns = new ArrayList<Guns>();
		guns.add(g);
		return new ModelAndView("getByNamePage", "message",guns);
	}
	
	@RequestMapping("/getAllGuns")
	public ModelAndView getEmployeePage() {
		List<Guns> guns = gunsService.getAll();
		System.out.println(guns);
		return new ModelAndView("getAllGunsPage", "message",guns);
	}
	
	
	//redirecting
	@RequestMapping("/addPage1")
	public ModelAndView addPageRedirecting() {
		return new ModelAndView("addPage");
	}
	@RequestMapping("/deletePage1")
	public ModelAndView deletePageRedirecting() {
		return new ModelAndView("deletePage");
	}
	@RequestMapping("/getByName1")
	public ModelAndView getByNamePageRedirecting() {
		return new ModelAndView("getByNamePage");
	}
	
}
