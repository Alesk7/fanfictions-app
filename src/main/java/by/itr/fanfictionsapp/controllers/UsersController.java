package by.itr.fanfictionsapp.controllers;

import by.itr.fanfictionsapp.services.AuthenticationService;
import by.itr.fanfictionsapp.services.UserAccountService;
import by.itr.fanfictionsapp.services.dto.CredentialsUniqueDTO;
import by.itr.fanfictionsapp.services.dto.UserAccountDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/me/update")
    @ResponseStatus(HttpStatus.OK)
    public CredentialsUniqueDTO updateUserAccount(@RequestBody UserAccountDTO userAccountDTO){
        CredentialsUniqueDTO credentialsUniqueDTO = authenticationService.isCredentialsUnique(userAccountDTO);
        if(credentialsUniqueDTO.isUsernameUnique()) {
            userAccountService.updateMyUserAccount(userAccountDTO);
        }
        return credentialsUniqueDTO;
    }

    @PostMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void updateUserAccounts(@RequestBody UserAccountDTO[] users){
        userAccountService.updateUserAccounts(users);
    }

    @PostMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUserAccounts(@RequestBody UserAccountDTO[] users){
        userAccountService.deleteUserAccounts(users);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<UserAccountDTO> getAll(){
        return userAccountService.getAllUsers();
    }

}
