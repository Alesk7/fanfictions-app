package by.itr.fanfictionsapp.services;

import by.itr.fanfictionsapp.models.UserAccount;
import by.itr.fanfictionsapp.repositories.UserAccountRepository;
import by.itr.fanfictionsapp.security.models.UserAccountDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAccountDao implements UserDetailsService {

    private final UserAccountRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount user = userRepository.findByUsername(username);
        if (user == null) throw new UsernameNotFoundException("User not found");
        return new UserAccountDetails(user);
    }
}
