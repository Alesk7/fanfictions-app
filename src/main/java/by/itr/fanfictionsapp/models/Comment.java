package by.itr.fanfictionsapp.models;

import by.itr.fanfictionsapp.services.dto.CommentDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue
    private Long id;

    private String comment;

    @ManyToOne(targetEntity = UserAccount.class, fetch = FetchType.EAGER)
    private UserAccount userAccount;

    @ManyToOne(targetEntity = Fanfiction.class, fetch = FetchType.EAGER)
    private Fanfiction fanfiction;

    public Comment(CommentDTO commentDTO){
        this.comment = commentDTO.getText();
    }

}
