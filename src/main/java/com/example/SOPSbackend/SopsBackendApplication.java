
package com.example.SOPSbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SopsBackendApplication {	
	public static void main(String[] args) {
		SpringApplication.run(SopsBackendApplication.class, args);
	}
}