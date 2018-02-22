package by.itr.fanfictionsapp.models;

import by.itr.fanfictionsapp.services.dto.ChapterDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Chapter {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    @Lob
    private String textBlock;

    private String imageURL;

    public Chapter(ChapterDTO chapterDTO){
        this.title = chapterDTO.getTitle();
        this.textBlock = chapterDTO.getTextBlock();
        this.imageURL = chapterDTO.getImageURL();
    }

}
