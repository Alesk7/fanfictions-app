package by.itr.fanfictionsapp.services;

import by.itr.fanfictionsapp.models.UserAccount;
import by.itr.fanfictionsapp.repositories.UserAccountRepository;
import by.itr.fanfictionsapp.security.exceptions.CredentialsNotUniqueException;
import by.itr.fanfictionsapp.security.models.UserAccountDetails;
import by.itr.fanfictionsapp.services.dto.AppUserDTO;
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

    public AppUserDTO getMe(){
        UserAccountDetails userDetails = (UserAccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserAccount user = userAccountRepository.findOne(userDetails.getId());
        return new AppUserDTO(user);
    }

    public void createUserAccount(RegisterRequestDTO registerRequestDTO) throws CredentialsNotUniqueException{
        if(authenticationService.isCredentialsUnique(registerRequestDTO).isCredentialsUnique())
            throw new CredentialsNotUniqueException("Credentials not unique");
        UserAccount userAccount = new UserAccount();
        userAccount.setEmail(registerRequestDTO.getEmail());
        userAccount.setUsername(registerRequestDTO.getUsername());
        userAccount.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
        userAccountRepository.save(userAccount);
    }

}
