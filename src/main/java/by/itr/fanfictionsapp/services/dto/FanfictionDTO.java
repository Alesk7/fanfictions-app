package by.itr.fanfictionsapp.services.dto;

import by.itr.fanfictionsapp.models.Chapter;
import by.itr.fanfictionsapp.models.Fanfiction;
import by.itr.fanfictionsapp.models.Tag;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Getter
@Setter
@NoArgsConstructor
public class FanfictionDTO {

    private Long id;
    private String author;
    private String title;
    private String description;
    private String genre;
    private String creationDate;
    private List<String> tags;
    private String imageURL;
    private List<ChapterDTO> chapters;

    public FanfictionDTO(Fanfiction fanfiction){
        id = fanfiction.getId();
        title = fanfiction.getTitle();
        description = fanfiction.getDescription();
        genre = fanfiction.getGenre().name();
        creationDate = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(fanfiction.getCreationDate());
        tags = fanfiction.getTags().stream()
                .map(Tag::getTag)
                .collect(Collectors.toList());
        imageURL = fanfiction.getImageURL();
        author = fanfiction.getUserAccount().getUsername();
    }

    public void setChapters(Iterable<Chapter> chapters){
        this.chapters = StreamSupport.stream(chapters.spliterator(), false)
                .map(ChapterDTO::new)
                .collect(Collectors.toList());
    }

}
