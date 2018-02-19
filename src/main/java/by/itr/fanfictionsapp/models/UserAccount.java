package by.itr.fanfictionsapp.models;

import by.itr.fanfictionsapp.services.dto.RegisterRequestDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class UserAccount {

    @Id
    @GeneratedValue
    private Long id;

    private String username;

    private String password;

    private String email;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    private boolean enabled;

    private boolean nonBlocked;

    public UserAccount(RegisterRequestDTO registerRequestDTO){
        this.username = registerRequestDTO.getUsername();
        this.email = registerRequestDTO.getEmail();
        this.password = registerRequestDTO.getPassword();
        userRole = UserRole.ROLE_USER;
        enabled = false;
        nonBlocked = true;
    }

    public void setUserRole(String role){
        this.userRole = UserRole.valueOf(role);
    }

    public void setUserRole(UserRole role){
        this.userRole = role;
    }

}
