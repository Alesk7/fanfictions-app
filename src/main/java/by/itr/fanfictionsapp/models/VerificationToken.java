package by.itr.fanfictionsapp.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Entity
@Getter
@Setter
public class VerificationToken {

    private static final long EXPIRATION = TimeUnit.HOURS.toMillis(24);

    @Id
    @GeneratedValue
    private Long id;

    private String token;

    @OneToOne(targetEntity = UserAccount.class, fetch = FetchType.EAGER)
    private UserAccount userAccount;

    private Date expiryDate;

    public VerificationToken(){
        expiryDate = new Date(new Date().getTime() + EXPIRATION);
    }

    public VerificationToken(UserAccount userAccount, String token){
        expiryDate = new Date(new Date().getTime() + EXPIRATION);
        this.userAccount = userAccount;
        this.token = token;
    }

}
