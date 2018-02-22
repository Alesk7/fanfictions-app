package by.itr.fanfictionsapp.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Fanfiction {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(targetEntity = UserAccount.class, fetch = FetchType.EAGER)
    private UserAccount userAccount;

    private String title;

    private String description;

    private String imageURL;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    @ManyToMany(targetEntity = Tag.class, fetch = FetchType.EAGER)
    private List<Tag> tags;

    private Date creationDate;

}
