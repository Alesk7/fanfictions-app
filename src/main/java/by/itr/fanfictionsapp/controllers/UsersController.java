package by.itr.fanfictionsapp.controllers;

import by.itr.fanfictionsapp.services.AuthenticationService;
import by.itr.fanfictionsapp.services.UserAccountService;
import by.itr.fanfictionsapp.services.dto.CredentialsUniqueDTO;
import by.itr.fanfictionsapp.services.dto.UserAccountDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    private final UserAccountService userAccountService;
    private final AuthenticationService authenticationService;

    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public UserAccountDTO getMe(@RequestParam(value = "email", required = false) String email){
        if(email == null){
            return userAccountService.getMe();
        } else {
            return userAccountService.getUserByEmail(email);
        }
    }

    @PostMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public CredentialsUniqueDTO updateUser(@RequestParam(value = "email", required = false) String email,
                                           @RequestBody UserAccountDTO userAccountDTO){
        CredentialsUniqueDTO credentialsUniqueDTO = authenticationService.isCredentialsUnique(userAccountDTO);
        if(credentialsUniqueDTO.isCredentialsUnique()) {
            userAccountService.updateUserAccount(email, userAccountDTO);
        }
        return credentialsUniqueDTO;
    }

}
