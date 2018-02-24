package by.itr.fanfictionsapp.models;

import by.itr.fanfictionsapp.services.dto.ChapterDTO;
import by.itr.fanfictionsapp.services.dto.FanfictionDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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

    private String description;

    private String imageURL;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    @ManyToMany(targetEntity = Tag.class, fetch = FetchType.EAGER)
    private List<Tag> tags;

    private Date creationDate;

    @OneToMany(targetEntity = Chapter.class, fetch = FetchType.LAZY)
    private List<Chapter> chapters;

    public Fanfiction(FanfictionDTO fanfictionDTO, UserAccount userAccount){
        this.userAccount = userAccount;
        set(fanfictionDTO);
    }

    public void setChapters(Iterable<ChapterDTO> chapters){
        this.chapters = StreamSupport.stream(chapters.spliterator(), false)
                .map(Chapter::new)
                .collect(Collectors.toList());
    }

    public void setChapters(List<Chapter> chapters){
        this.chapters = chapters;
    }

    public void set(FanfictionDTO fanfictionDTO){
        this.title = fanfictionDTO.getTitle();
        this.description = fanfictionDTO.getDescription();
        this.imageURL = fanfictionDTO.getImageURL();
        this.genre = Genre.valueOf(fanfictionDTO.getGenre());
        this.creationDate = new Date();
    }

}
