package by.itr.fanfictionsapp.services;

import by.itr.fanfictionsapp.models.UserAccount;
import by.itr.fanfictionsapp.models.UserRole;
import by.itr.fanfictionsapp.repositories.UserAccountRepository;
import by.itr.fanfictionsapp.security.exceptions.CredentialsNotUniqueException;
import by.itr.fanfictionsapp.security.models.UserAccountDetails;
import by.itr.fanfictionsapp.services.dto.LoginRequestDTO;
import by.itr.fanfictionsapp.services.dto.RegisterRequestDTO;
import by.itr.fanfictionsapp.services.dto.UserAccountDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class UserAccountService {

    private final UserAccountRepository userAccountRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationService authenticationService;

    public UserAccountDTO getMe(){
        UserAccountDetails userDetails = (UserAccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserAccount user = userAccountRepository.findOne(userDetails.getId());
        return new UserAccountDTO(user);
    }

    public UserAccountDTO getUserByEmail(String email){
        UserAccount user = userAccountRepository.findByEmail(email);
        return new LoginRequestDTO(user);
    }

    public UserAccount createUserAccount(RegisterRequestDTO registerRequestDTO) throws CredentialsNotUniqueException {
        if(!authenticationService.isCredentialsUnique(registerRequestDTO).isCredentialsUnique())
            throw new CredentialsNotUniqueException("Credentials not unique");
        UserAccount userAccount = new UserAccount();
        userAccount.setEmail(registerRequestDTO.getEmail());
        userAccount.setUsername(registerRequestDTO.getUsername());
        userAccount.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
        userAccount.setUserRole(UserRole.ROLE_USER);
        userAccountRepository.save(userAccount);
        return userAccount;
    }

    @Transactional
    public void updateUserAccounts(UserAccountDTO... users){
        for(UserAccountDTO u: users){
            UserAccount user = userAccountRepository.findByEmail(u.getEmail());
            Optional.ofNullable(u.getUsername()).ifPresent(user::setUsername);
            Optional.ofNullable(u.getRole()).ifPresent(user::setUserRole);
            user.setNonBlocked(!u.isBlocked());
            userAccountRepository.save(user);
        }
    }

    @Transactional
    public UserAccount updateMyUserAccount(UserAccountDTO userAccountDTO){
        UserAccountDetails userDetails = (UserAccountDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserAccount userAccount = userAccountRepository.findOne(userDetails.getId());
        Optional.ofNullable(userAccountDTO.getUsername()).ifPresent(userAccount::setUsername);
        return userAccountRepository.save(userAccount);
    }

    public List<UserAccountDTO> getAllUsers(){
        Iterable<UserAccount> users = userAccountRepository.findAll();
        return StreamSupport.stream(users.spliterator(), false)
                .map(UserAccountDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteUserAccounts(UserAccountDTO... users){
        for(UserAccountDTO u: users){
            UserAccount user = userAccountRepository.findByEmail(u.getEmail());
            userAccountRepository.delete(user);
        }
    }

}
