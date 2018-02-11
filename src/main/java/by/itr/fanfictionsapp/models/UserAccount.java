package by.itr.fanfictionsapp.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class UserAccount {

    @Id
    @GeneratedValue
    private Long id;

    private String username;

    private String password;

    private String email;

}
