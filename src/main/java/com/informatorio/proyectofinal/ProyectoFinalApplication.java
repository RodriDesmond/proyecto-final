package com.informatorio.proyectofinal;

import com.informatorio.proyectofinal.security.AppUser;
import com.informatorio.proyectofinal.security.LoggedInUser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ProyectoFinalApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProyectoFinalApplication.class, args);
	}
	@GetMapping("/secured")
	public Object secured(@LoggedInUser AppUser appUser){
		return appUser.getUser();
	}

	/*
	@GetMapping("/secured-admin")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String securedAdmin(){
		return "Only admin can see this";
	}
	 */
}
