package by.itr.fanfictionsapp.services.dto;

import by.itr.fanfictionsapp.models.UserAccount;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDTO extends UserAccountDTO {

    private String username;
    private String email;
    private String password;

    public LoginRequestDTO(UserAccount user){
        this.username = user.getUsername();
        this.email = user.getEmail();
    }
}
