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
public class AppUserDTO {

    String username;
    String email;

    public AppUserDTO(UserAccount user){
        this.username = user.getUsername();
        this.email = user.getEmail();
    }
}
