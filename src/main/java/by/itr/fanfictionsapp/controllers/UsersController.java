package by.itr.fanfictionsapp.controllers;

import by.itr.fanfictionsapp.services.UserAccountService;
import by.itr.fanfictionsapp.services.dto.AppUserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    private final UserAccountService userAccountService;

    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public AppUserDTO getMe(){
        return userAccountService.getMe();
    }
}
