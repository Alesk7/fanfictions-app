package by.itr.fanfictionsapp.controllers;

import by.itr.fanfictionsapp.models.UserAccount;
import by.itr.fanfictionsapp.security.exceptions.CredentialsNotUniqueException;
import by.itr.fanfictionsapp.security.exceptions.VerificationTokenException;
import by.itr.fanfictionsapp.services.AuthenticationService;
import by.itr.fanfictionsapp.services.UserAccountService;
import by.itr.fanfictionsapp.services.dto.CredentialsUniqueDTO;
import by.itr.fanfictionsapp.services.dto.LoginResponseDTO;
import by.itr.fanfictionsapp.services.dto.RegisterRequestDTO;
import by.itr.fanfictionsapp.services.dto.LoginRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;
    private final UserAccountService userAccountService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponseDTO login(@RequestBody LoginRequestDTO loginRequestDTO){
        return authenticationService.login(loginRequestDTO);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public CredentialsUniqueDTO register(@RequestBody @Valid RegisterRequestDTO registerRequestDTO) throws CredentialsNotUniqueException{
        CredentialsUniqueDTO credentialsUniqueDTO = authenticationService.isCredentialsUnique(registerRequestDTO);
        if(credentialsUniqueDTO.isCredentialsUnique()) {
            UserAccount userAccount = userAccountService.createUserAccount(registerRequestDTO);
            authenticationService.sendConfirmationMail(userAccount);
        }
        return credentialsUniqueDTO;
    }

    @GetMapping("/register/confirm")
    public RedirectView confirmRegistration(@RequestParam("token") String token) throws VerificationTokenException{
        authenticationService.confirmUserAccount(token);
        return new RedirectView("http://localhost:4200");
    }

    @PostMapping("/isCredentialsUnique")
    public CredentialsUniqueDTO isCredentialsUnique(@RequestBody RegisterRequestDTO registerRequestDTO){
        return authenticationService.isCredentialsUnique(registerRequestDTO);
    }

}
