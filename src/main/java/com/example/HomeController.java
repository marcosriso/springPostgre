package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

	@Autowired //don't forget the setter
	private UsersRepository repository; 

	@RequestMapping("/")
	public String home(Model model) {
		model.addAttribute("users", repository.findAll());
		return "home";
	}
	

	@RequestMapping("/pesquisa")
	public String pesquisa(Model model, @RequestParam(value="name", required=false, defaultValue="") String name) {
		
		if(!name.isEmpty()){
			model.addAttribute("users", repository.findByName(name));
		}else{
			model.addAttribute("users", repository.findAll());
		}
		return "home";
	}


	@RequestMapping("/inserir")
	public String inserir() {
		return "inserir";
	}


	@RequestMapping("/editar/{id}")
	public String editar(Model model, @PathVariable("id") Long id) {

		model.addAttribute("users", repository.findOne(id));

		return "editar";
	}


	@RequestMapping("/delete/{id}")
	public String delete(Model model, @PathVariable("id") Long id) {

		Users users = repository.findOne(id);    	
		model.addAttribute("name", users.getName());
		repository.delete(users);

		return "deleted";
	}


	@RequestMapping("/save")
	public String process(@RequestParam(value="name", required=false, defaultValue="") String name, @RequestParam(value="password", required=false, defaultValue="") String password, Model model){
		try {
			repository.save(new Users(name, password));

			model.addAttribute("name", name);
			model.addAttribute("password", password);
			return "sucesso";
		} catch (RuntimeException ex) {
			return "error";
		}
	}

	@RequestMapping("/saveedit")
	public String saveedit(@RequestParam(value="id", required=true, defaultValue="") Long id, 
			@RequestParam(value="name", required=false, defaultValue="") String name, 
			@RequestParam(value="password", required=false, defaultValue="") String password, Model model){

		Users users = repository.findOne(id);
		users.setName(name);
		users.setPassword(password);

		repository.save(users);

		model.addAttribute("name", name);
		model.addAttribute("password", password);
		return "sucesso";
	}


}
