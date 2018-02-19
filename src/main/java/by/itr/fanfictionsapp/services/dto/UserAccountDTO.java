package by.itr.fanfictionsapp.services.dto;

import by.itr.fanfictionsapp.models.UserAccount;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserAccountDTO {
    private String username;
    private String email;
    private String role;
    private boolean blocked;

    public UserAccountDTO(UserAccount user){
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.role = user.getUserRole().name();
        this.blocked = !user.isNonBlocked();
    }

}
