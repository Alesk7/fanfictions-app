package by.itr.fanfictionsapp.controllers;

import by.itr.fanfictionsapp.security.exceptions.CredentialsNotUniqueException;
import by.itr.fanfictionsapp.services.AuthenticationService;
import by.itr.fanfictionsapp.services.UserAccountService;
import by.itr.fanfictionsapp.services.dto.CredentialsUniqueDTO;
import by.itr.fanfictionsapp.services.dto.LoginRequestDTO;
import by.itr.fanfictionsapp.services.dto.LoginResponseDTO;
import by.itr.fanfictionsapp.services.dto.RegisterRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
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
    public void register(@RequestBody @Valid RegisterRequestDTO registerRequestDTO) throws CredentialsNotUniqueException{
        userAccountService.createUserAccount(registerRequestDTO);
    }

    @PostMapping("/isCredentialsUnique")
    public CredentialsUniqueDTO isCredentialsUnique(@RequestBody RegisterRequestDTO registerRequestDTO){
        return authenticationService.isCredentialsUnique(registerRequestDTO);
    }
}
