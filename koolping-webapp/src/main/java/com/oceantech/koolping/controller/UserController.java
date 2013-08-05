package com.oceantech.koolping.controller;


import com.oceantech.koolping.command.UserCommand;
import com.oceantech.koolping.domain.model.identity.Role;
import com.oceantech.koolping.domain.model.identity.User;
import com.oceantech.koolping.infrastructure.persistence.UserRepository;
import com.oceantech.koolping.service.UserService;
import com.oceantech.koolping.util.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired 
	private UserService service;
	
	@ModelAttribute("allRoles")
	public List<Integer> getAllRoles() {
		List<Integer> roles = new ArrayList<Integer>();
		roles.add(1);
		roles.add(2);
		return roles;
	}
	
	@RequestMapping
	public String getUsersPage(ModelMap model) {
		Pageable pageRequest = new PageRequest(0, 10);
		Page<User> users = repository.findAll(pageRequest);
		model.addAttribute("users", UserMapper.map(users));
		model.addAttribute("commanduser", new UserCommand());
		model.addAttribute("usertype", "new");
		return "users";
	}
	
	@RequestMapping(value="/get", produces="application/json")
	public @ResponseBody UserCommand get(@RequestBody UserCommand user) {
		return UserMapper.map(repository.findByUserName(user.getUsername()));
	}

	@RequestMapping(value="/create", produces="application/json", method=RequestMethod.POST)
	public String create(UserCommand dto) {
		if (dto.getId() != null)  {
			User existingUser = new User(dto.getUsername(), 
				dto.getFirstname(),
				dto.getLastname(),
				new Role(dto.getRole()));
			service.update(existingUser);
		} else {
			User newUser = new User(dto.getUsername(), 
				null, 
				dto.getFirstname(),
				dto.getLastname(),
				new Role(dto.getRole()));
			service.create(newUser);
		} 
		
		return "redirect:/users";
	}
	
	@RequestMapping(value="/edit")
	public String edit(Long id, ModelMap model) {
		Pageable pageRequest = new PageRequest(0, 10);
		Page<User> users = repository.findAll(pageRequest);
		model.addAttribute("users", UserMapper.map(users));
		model.addAttribute("commanduser", UserMapper.map(repository.findOne(id)));
		model.addAttribute("usertype", "update");
		return "users";
	}
	
	@RequestMapping(value="/delete")
	public String delete(Long id) {

		User existingUser = new User();
		existingUser.setId(id);
		service.delete(existingUser);
		return "redirect:/users";
	}
}
