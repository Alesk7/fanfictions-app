package by.itr.fanfictionsapp;

import by.itr.fanfictionsapp.configurations.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({ SecurityConfig.class})
@ComponentScan
public class FanfictionsAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(FanfictionsAppApplication.class, args);
	}

}
