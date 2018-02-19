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
public class UserAccountDTO {

    String username;
    String email;
    String password;

    public UserAccountDTO(UserAccount user){
        this.username = user.getUsername();
        this.email = user.getEmail();
    }
}
