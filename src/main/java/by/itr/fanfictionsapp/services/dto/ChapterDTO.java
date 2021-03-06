package by.itr.fanfictionsapp.services.dto;

import by.itr.fanfictionsapp.models.Chapter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChapterDTO {

    private Long id;
    private String title;
    private String textBlock;
    private String imageURL;

    public ChapterDTO(Chapter chapter){
        id = chapter.getId();
        title = chapter.getTitle();
        textBlock = chapter.getTextBlock();
        imageURL = chapter.getImageURL();
    }

}
