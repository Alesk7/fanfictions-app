package by.itr.fanfictionsapp.services.social;

import by.itr.fanfictionsapp.models.UserAccount;
import by.itr.fanfictionsapp.models.UserRole;
import by.itr.fanfictionsapp.repositories.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SimpleConnectionSignUp implements ConnectionSignUp {

    private final UserAccountRepository userAccountRepository;

    @Override
    public String execute(Connection<?> connection) {
        UserAccount userAccount= new UserAccount();
        userAccount.setUsername(connection.getDisplayName());
        userAccount.setUserRole(UserRole.ROLE_USER);
        userAccount.setEnabled(true);
        userAccountRepository.save(userAccount);
        return userAccount.getUsername();
    }

}
