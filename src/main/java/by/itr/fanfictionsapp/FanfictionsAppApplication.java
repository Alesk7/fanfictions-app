package by.itr.fanfictionsapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class FanfictionsAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(FanfictionsAppApplication.class, args);
	}

}
