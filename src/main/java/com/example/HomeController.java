package com.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

	@Autowired //don't forget the setter
	private UsersRepository repository; 
	
	@Autowired //don't forget the setter
	private ProjectsRepository repositoryprj;

	@RequestMapping("/")
	public String login() {
		return "login";
	}
	
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Model model, @PageableDefault(value=4, page=0) Pageable pageable) {
		Page users = repository.findAll(pageable);
		PageWrapper<Users> page = new PageWrapper<Users>(users, "/home");
		
		String active = "home";
		
		model.addAttribute("users", users.getContent());
		model.addAttribute("page", page);
		model.addAttribute("active", active);
		
		return "home";
	}
	
	@RequestMapping("/projetos")
	public String projetos(Model model) {
		model.addAttribute("projects", repositoryprj.findAll());
		String active = "prj";
		model.addAttribute("active", active);
		
		return "projetos";
	}
	
	@RequestMapping("/pesquisaprj")
	public String pesquisaprj(Model model, @RequestParam(value="description", required=false, defaultValue="") String description) {
		
		String active = "prj";
		model.addAttribute("active", active);
		
		if(!description.isEmpty()){
			model.addAttribute("projects", repositoryprj.findByDescription(description));
		}else{
			model.addAttribute("projects", repositoryprj.findAll());
		}
		return "projetos";
	}
	

	@RequestMapping("/pesquisa")
	public String pesquisa(Model model, @PageableDefault(value=4, page=0) Pageable pageable, @RequestParam(value="name", required=false, defaultValue="") String name) {
		String active = "home";
		model.addAttribute("active", active);
		
		Page users = repository.findAll(pageable);
		PageWrapper<Users> page = new PageWrapper<Users>(users, "/home");
		
		model.addAttribute("page", page);
		model.addAttribute("active", active);
		
		if(!name.isEmpty()){
			model.addAttribute("users", repository.findByName(name));
		}else{
			model.addAttribute("users", users.getContent());
		}
		return "home";
	}


	@RequestMapping("/inserir")
	public String inserir(Model model) {
		String active = "form";
		model.addAttribute("active", active);
		model.addAttribute("projects", repositoryprj.findAll());
		return "inserir";
	}


	@RequestMapping("/editar/{id}")
	public String editar(Model model, @PathVariable("id") Long id) {
		String active = "form";
		model.addAttribute("active", active);
		model.addAttribute("projects", repositoryprj.findAll());
		model.addAttribute("users", repository.findOne(id));

		return "editar";
	}


	@RequestMapping("/delete/{id}")
	public String delete(Model model, @PathVariable("id") Long id) {
		String active = "home";
		model.addAttribute("active", active);
		Users users = repository.findOne(id);    	
		model.addAttribute("name", users.getName());
		repository.delete(users);

		return "deleted";
	}


	@RequestMapping("/save")
	public String process(@RequestParam(value="name", required=false, defaultValue="") String name, 
			@RequestParam(value="password", required=false, defaultValue="") String password, 
			@RequestParam(value="project", required=false, defaultValue="") Integer project, 
			Model model){
		String active = "home";
		model.addAttribute("active", active);
		try {
			Projects prj = repositoryprj.findOne(project);
			repository.save(new Users(name, password, prj));

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
			@RequestParam(value="password", required=false, defaultValue="") String password, 
			@RequestParam(value="project", required=false, defaultValue="") Integer project, 
			Model model){
		
		String active = "home";
		model.addAttribute("active", active);

		Projects prj = repositoryprj.findOne(project);
		
		Users users = repository.findOne(id);
		users.setName(name);
		users.setPassword(password);
		users.setProject(prj);

		repository.save(users);

		model.addAttribute("name", name);
		model.addAttribute("password", password);
		model.addAttribute("project", project);
		return "sucesso";
	}


}
