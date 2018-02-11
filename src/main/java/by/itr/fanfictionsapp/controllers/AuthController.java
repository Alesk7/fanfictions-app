package by.itr.fanfictionsapp.controllers;

import by.itr.fanfictionsapp.services.AuthenticationService;
import by.itr.fanfictionsapp.services.dto.LoginRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public String login(@RequestBody LoginRequestDTO loginRequestDTO){
        return authenticationService.login(loginRequestDTO);
    }
}
