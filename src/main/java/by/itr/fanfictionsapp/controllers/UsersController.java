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

    @PostMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public CredentialsUniqueDTO updateUser(@RequestBody UserAccountDTO userAccountDTO){
        CredentialsUniqueDTO credentialsUniqueDTO = authenticationService.isCredentialsUnique(userAccountDTO);
        if(credentialsUniqueDTO.isCredentialsUnique()) {
            userAccountService.updateUserAccount(null, userAccountDTO);
        }
        return credentialsUniqueDTO;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/updateByEmail")
    @ResponseStatus(HttpStatus.OK)
    public void updateUserByEMail(@RequestParam("email") String email,
                                  @RequestBody UserAccountDTO userAccountDTO){
        userAccountService.updateUserAccount(email, userAccountDTO);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<UserAccountDTO> getAll(){
        return userAccountService.getAllUsers();
    }

}
