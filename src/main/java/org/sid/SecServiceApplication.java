package org.sid;

import java.util.stream.Stream;

import org.sid.entities.AppRole;
import org.sid.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SecServiceApplication implements CommandLineRunner {
	
	@Autowired
	private AccountService acountService;
	public static void main(String[] args) {
		SpringApplication.run(SecServiceApplication.class, args);
	}
	
	@Bean
	BCryptPasswordEncoder getBCPE() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void run(String... args) throws Exception {
		acountService.save(new AppRole(null, "USER"));
		acountService.save(new AppRole(null, "ADMIN"));
		Stream.of("user1","user2","user3").forEach(un->{
			acountService.saveUser(un, "1234", "1234");
		});		
		acountService.saveUser("admin", "1234", "1234");
		acountService.addRoleToUser("admin", "ADMIN");
	}
}
