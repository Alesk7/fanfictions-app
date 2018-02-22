package by.itr.fanfictionsapp;

import by.itr.fanfictionsapp.configurations.SecurityConfig;
import by.itr.fanfictionsapp.models.*;
import by.itr.fanfictionsapp.repositories.FanfictionsRepository;
import by.itr.fanfictionsapp.repositories.TagsRepository;
import by.itr.fanfictionsapp.repositories.UserAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Date;

@SpringBootApplication
@Import({ SecurityConfig.class })
@ComponentScan
public class FanfictionsAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(FanfictionsAppApplication.class, args);
	}

    @Bean
    CommandLineRunner bootstrap(final UserAccountRepository userAccountRepository,
                                final PasswordEncoder passwordEncoder,
                                final FanfictionsRepository fanfictionsRepository,
                                final TagsRepository tagsRepository) {
        return (args) -> {
            UserAccount user = new UserAccount();
            user.setUsername("TestUser");
            user.setEmail("user@mail.com");
            user.setPassword(passwordEncoder.encode("1234qwer"));
            user.setUserRole(UserRole.ROLE_ADMIN);
            user.setEnabled(true);
            user.setNonBlocked(true);
            userAccountRepository.save(user);

            UserAccount user1 = new UserAccount();
            user1.setUsername("Dipper");
            user1.setEmail("dipper@mail.com");
            user1.setPassword(passwordEncoder.encode("1234qwer"));
            user1.setUserRole(UserRole.ROLE_USER);
            user1.setEnabled(true);
            user1.setNonBlocked(true);
            userAccountRepository.save(user1);

            UserAccount user2 = new UserAccount();
            user2.setUsername("Mabel");
            user2.setEmail("mabel@mail.com");
            user2.setPassword(passwordEncoder.encode("1234qwer"));
            user2.setUserRole(UserRole.ROLE_ADMIN);
            user2.setEnabled(true);
            user2.setNonBlocked(true);
            userAccountRepository.save(user2);

            Tag tag = new Tag();
            tag.setTag("Forest");
            tag.setWeight(1);
            tagsRepository.save(tag);

            Tag tag1 = new Tag();
            tag1.setTag("Mystery");
            tag1.setWeight(2);
            tagsRepository.save(tag1);

            Tag tag2 = new Tag();
            tag2.setTag("Anal");
            tag2.setWeight(3);
            tagsRepository.save(tag2);

            Tag tag3 = new Tag();
            tag3.setTag("Girls");
            tag3.setWeight(4);
            tagsRepository.save(tag3);

            for(int i = 1; i <= 150; i++){
                Fanfiction fanfiction = new Fanfiction();
                fanfiction.setTitle("The fanfic " + i);
                fanfiction.setDescription("Imagine if someone had made it to the Glade before Alby. Someone who watched the boys amalgamate in order to survive, hidden by the shadows of fear and the walls that stretched to infinity.");
                fanfiction.setGenre(Genre.Adventure);
                fanfiction.setTags(Arrays.asList(tag, tag1, tag2, tag3));
                fanfiction.setCreationDate(new Date());
                fanfiction.setUserAccount(user);
                fanfiction.setImageURL("https://i.quotev.com/img/q/u/18/1/19/2vzexvp6jo.jpg");
                fanfictionsRepository.save(fanfiction);
            }

        };
    }

}
