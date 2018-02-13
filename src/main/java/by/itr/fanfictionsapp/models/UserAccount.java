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

    public UserAccount(RegisterRequestDTO registerRequestDTO){
        this.username = registerRequestDTO.getUsername();
        this.email = registerRequestDTO.getEmail();
        this.password = registerRequestDTO.getPassword();
        userRole = UserRole.ROLE_USER;
    }

}
