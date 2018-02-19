package by.itr.fanfictionsapp.services;

import by.itr.fanfictionsapp.models.UserAccount;
import by.itr.fanfictionsapp.models.UserRole;
import by.itr.fanfictionsapp.repositories.UserAccountRepository;
import by.itr.fanfictionsapp.security.exceptions.CredentialsNotUniqueException;
import by.itr.fanfictionsapp.security.models.UserAccountDetails;
import by.itr.fanfictionsapp.services.dto.UserAccountDTO;
import by.itr.fanfictionsapp.services.dto.RegisterRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
        return new UserAccountDTO(user);
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

    public UserAccount updateUserAccount(UserAccountDTO userAccountDTO){
        UserAccountDetails userDetails = (UserAccountDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserAccount user = userAccountRepository.findOne(userDetails.getId());
        if(!userAccountDTO.getUsername().isEmpty())
            user.setUsername(userAccountDTO.getUsername());
        return userAccountRepository.save(user);
    }

}
