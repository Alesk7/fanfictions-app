package by.itr.fanfictionsapp.services;

import by.itr.fanfictionsapp.security.JWTHelper;
import by.itr.fanfictionsapp.security.models.UserAccountDetails;
import by.itr.fanfictionsapp.services.dto.LoginRequestDTO;
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

    public String login(LoginRequestDTO loginRequestDTO){
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                loginRequestDTO.getUsername(), loginRequestDTO.getPassword());
        Authentication authResult = authenticationManager.authenticate(authRequest);
        if(authResult.isAuthenticated()){
            UserAccountDetails userDetails = (UserAccountDetails) authResult.getPrincipal();
            return jwtHelper.createToken(userDetails.getId());
        } else {
            throw new AuthenticationCredentialsNotFoundException("Athentication failed.");
        }
    }
}
