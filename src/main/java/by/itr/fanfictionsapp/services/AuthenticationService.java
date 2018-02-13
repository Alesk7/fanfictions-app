package by.itr.fanfictionsapp.services;

import by.itr.fanfictionsapp.repositories.UserAccountRepository;
import by.itr.fanfictionsapp.security.JWTHelper;
import by.itr.fanfictionsapp.security.models.UserAccountDetails;
import by.itr.fanfictionsapp.services.dto.LoginRequestDTO;
import by.itr.fanfictionsapp.services.dto.LoginResponseDTO;
import by.itr.fanfictionsapp.services.dto.RegisterRequestDTO;
import by.itr.fanfictionsapp.services.dto.CredentialsUniqueDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final JWTHelper jwtHelper;
    private final AuthenticationManager authenticationManager;
    private final UserAccountRepository userAccountRepository;

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO){
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                loginRequestDTO.getUsername(), loginRequestDTO.getPassword());
        Authentication authResult = authenticationManager.authenticate(authRequest);
        if(authResult.isAuthenticated()){
            UserAccountDetails userDetails = (UserAccountDetails) authResult.getPrincipal();
            return new LoginResponseDTO(jwtHelper.createToken(userDetails.getId()));
        } else {
            throw new AuthenticationCredentialsNotFoundException("Athentication failed.");
        }
    }

    public CredentialsUniqueDTO isCredentialsUnique(RegisterRequestDTO registerRequestDTO){
        CredentialsUniqueDTO credentialsUniqueDTO = new CredentialsUniqueDTO();
        credentialsUniqueDTO.setEmailUnique(!emailExists(registerRequestDTO.getEmail()));
        credentialsUniqueDTO.setUsernameUnique(!usernameExists(registerRequestDTO.getUsername()));
        credentialsUniqueDTO.setCredentialsUnique(credentialsUniqueDTO.isEmailUnique() && credentialsUniqueDTO.isUsernameUnique());
        return credentialsUniqueDTO;
    }

    private boolean emailExists(String email){
        return userAccountRepository.findByEmail(email) != null;
    }

    private boolean usernameExists(String username){
        return userAccountRepository.findByUsername(username) != null;
    }
}
