package by.itr.fanfictionsapp.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Rating {

    @Id
    @GeneratedValue
    private Long id;

    private Integer rate;

    @OneToOne(targetEntity = UserAccount.class)
    private UserAccount userAccount;

    @ManyToOne(targetEntity = Fanfiction.class)
    private Fanfiction fanfiction;

    public Rating(Integer rate, UserAccount userAccount, Fanfiction fanfiction){
        this.rate = rate;
        this.userAccount = userAccount;
        this.fanfiction = fanfiction;
    }

}
