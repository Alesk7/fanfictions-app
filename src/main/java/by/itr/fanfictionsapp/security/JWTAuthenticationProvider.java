package by.itr.fanfictionsapp.security;

import by.itr.fanfictionsapp.models.UserAccount;
import by.itr.fanfictionsapp.repositories.UserAccountRepository;
import by.itr.fanfictionsapp.security.exceptions.InvalidTokenAuthenticationException;
import by.itr.fanfictionsapp.security.models.UserAccountDetails;
import by.itr.fanfictionsapp.security.models.JWTAuthentication;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JWTAuthenticationProvider implements AuthenticationProvider {

    private final JWTHelper jwtHelper;
    private final UserAccountRepository userAccountRepository;

    @Override
    public Authentication authenticate(Authentication authRequest) throws AuthenticationException {
        String token = (String) authRequest.getCredentials();
        Long userId = (Long) jwtHelper.decodeToken(token).get(JWTHelper.USER_ID_CLAIM);
        UserAccount user = userAccountRepository.findOne(userId);
        if(user == null) {
            throw new InvalidTokenAuthenticationException("Token does not contain existed user id.");
        }
        UserAccountDetails userDetails = new UserAccountDetails(user);
        if(!userDetails.isAccountNonLocked()) {
            throw new LockedException("User account is locked");
        }
        return new JWTAuthentication(userDetails);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JWTAuthentication.class.isAssignableFrom(authentication);
    }
}
