package com.responsive.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Cacheable(value = "users", key = "#id")
    public String getUserById(int id) {
        System.out.println("Fetching from DB (simulated)...");

        // Simulate slow DB call (2 seconds)
        try { Thread.sleep(2000); } catch (Exception e) {}

        return "User-" + id;
    }

}
