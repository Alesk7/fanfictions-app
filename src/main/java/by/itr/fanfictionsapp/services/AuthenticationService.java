package by.itr.fanfictionsapp.services;

import by.itr.fanfictionsapp.models.UserAccount;
import by.itr.fanfictionsapp.models.VerificationToken;
import by.itr.fanfictionsapp.repositories.UserAccountRepository;
import by.itr.fanfictionsapp.repositories.VerificationTokenRepository;
import by.itr.fanfictionsapp.security.JWTHelper;
import by.itr.fanfictionsapp.security.exceptions.VerificationTokenException;
import by.itr.fanfictionsapp.security.models.UserAccountDetails;
import by.itr.fanfictionsapp.services.dto.LoginRequestDTO;
import by.itr.fanfictionsapp.services.dto.LoginResponseDTO;
import by.itr.fanfictionsapp.services.dto.RegisterRequestDTO;
import by.itr.fanfictionsapp.services.dto.CredentialsUniqueDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@PropertySource("classpath:application.properties")
public class AuthenticationService {

    @Value("${application.domain}")
    private String applicationDomain;

    private final JWTHelper jwtHelper;
    private final AuthenticationManager authenticationManager;
    private final UserAccountRepository userAccountRepository;
    private final JavaMailSender mailSender;
    private final VerificationTokenRepository verificationTokenRepository;

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

    public void sendConfirmationMail(UserAccount userAccount) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken(userAccount, token);
        verificationTokenRepository.save(verificationToken);
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(userAccount.getEmail());
        mail.setSubject("Registration confirmation");
        mail.setText(applicationDomain + "auth/register/confirm?token=" + token);
        mailSender.send(mail);
    }

    public void confirmUserAccount(String token) throws VerificationTokenException{
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token);
        if(verificationToken == null)
            throw new VerificationTokenException("Token not found");
        if(verificationToken.getExpiryDate().getTime() < new Date().getTime())
            throw new VerificationTokenException("Token expired");
        UserAccount userAccount = verificationToken.getUserAccount();
        userAccount.setEnabled(true);
        userAccountRepository.save(userAccount);
        verificationTokenRepository.delete(verificationToken);
    }
}
