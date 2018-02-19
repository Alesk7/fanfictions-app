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

import java.util.Optional;

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

    public UserAccount updateUserAccount(String email, UserAccountDTO userAccountDTO){
        UserAccount user;
        if(email != null){
            user = userAccountRepository.findByEmail(email);
        } else {
            UserAccountDetails userDetails = (UserAccountDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            user = userAccountRepository.findOne(userDetails.getId());
        }
        Optional.ofNullable(userAccountDTO.getUsername()).ifPresent(user::setUsername);
        Optional.ofNullable(userAccountDTO.getEmail()).ifPresent(user::setEmail);
        Optional.ofNullable(userAccountDTO.getRole()).ifPresent(user::setUserRole);
        user.setNonBlocked(!userAccountDTO.isBlocked());
        return userAccountRepository.save(user);
    }

}
