package com.responsive.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.responsive.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
	
	private final UserService userService;
	
    public UserController(UserService userService) {
		this.userService = userService;
	}

    @GetMapping("/{id}")
    public String getUser(@PathVariable int id) {
        return userService.getUserById(id);
    }

}
