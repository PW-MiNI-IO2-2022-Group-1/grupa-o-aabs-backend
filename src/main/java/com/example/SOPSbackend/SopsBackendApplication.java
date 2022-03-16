
package com.example.SOPSbackend;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SopsBackendApplication {	
	public static void main(String[] args) {
		SpringApplication.run(SopsBackendApplication.class, args);
	}

	@GetMapping("/hello")
	@Secured({"ROLE_DOCTOR"})
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s! Mozesz zmienic parametr 'name' w URL na swoje imie lub nawet zamienic slowo 'hello' na 'goodbye' (w pewien transcendentny i ulotny sposob jest to naprawde smutne - zwlaszcza jezeli usuniesz wszystko od znaku zapytania do konca url i wpiszesz 'goodbye' zamiast 'hello').", name);
	}


	@GetMapping("/goodbye")
	public String goodbye(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Goodbye %s...", name);
	}
}