package by.itr.fanfictionsapp.models;

import by.itr.fanfictionsapp.services.dto.FanfictionDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Fanfiction {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(targetEntity = UserAccount.class, fetch = FetchType.EAGER)
    private UserAccount userAccount;

    private String title;

    @Size(max = 280)
    private String description;

    private String imageURL;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    @ManyToMany(targetEntity = Tag.class, fetch = FetchType.EAGER)
    private List<Tag> tags;

    private Date creationDate;

    @OneToMany(targetEntity = Chapter.class, fetch = FetchType.LAZY)
    private List<Chapter> chapters;

    @OneToMany(targetEntity = Comment.class, fetch = FetchType.LAZY)
    private List<Comment> comments;

    @OneToMany(targetEntity = Rating.class, fetch = FetchType.LAZY)
    private List<Rating> rates;

    public Fanfiction(FanfictionDTO fanfictionDTO, UserAccount userAccount){
        this.userAccount = userAccount;
        setDataFromDTO(fanfictionDTO);
    }

    public void setChapters(List<Chapter> chapters){
        this.chapters = chapters;
    }

    public void setDataFromDTO(FanfictionDTO fanfictionDTO){
        this.title = fanfictionDTO.getTitle();
        this.description = fanfictionDTO.getDescription();
        this.imageURL = fanfictionDTO.getImageURL();
        this.genre = Genre.valueOf(fanfictionDTO.getGenre());
        this.creationDate = new Date();
    }

}
