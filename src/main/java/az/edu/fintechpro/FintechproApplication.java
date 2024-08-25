package az.edu.fintechpro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "az.edu.fintechpro")
public class FintechproApplication {

	public static void main(String[] args) {
		SpringApplication.run(FintechproApplication.class, args);
	}

}
