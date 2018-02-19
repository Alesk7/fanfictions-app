package by.itr.fanfictionsapp;

import by.itr.fanfictionsapp.configurations.SecurityConfig;
import by.itr.fanfictionsapp.models.UserAccount;
import by.itr.fanfictionsapp.models.UserRole;
import by.itr.fanfictionsapp.repositories.UserAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@Import({ SecurityConfig.class })
@ComponentScan
public class FanfictionsAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(FanfictionsAppApplication.class, args);
	}

    @Bean
    CommandLineRunner bootstrap(final UserAccountRepository userAccountRepository, final PasswordEncoder passwordEncoder) {
        return (args) -> {
            UserAccount user = new UserAccount();
            user.setUsername("TestUser");
            user.setEmail("user@mail.com");
            user.setPassword(passwordEncoder.encode("1234qwer"));
            user.setUserRole(UserRole.ROLE_USER);
            user.setEnabled(true);
            userAccountRepository.save(user);
        };
    }

}
