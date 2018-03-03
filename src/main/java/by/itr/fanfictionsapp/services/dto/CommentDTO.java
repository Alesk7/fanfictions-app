package by.itr.fanfictionsapp.services.dto;

import by.itr.fanfictionsapp.models.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentDTO {

    private String text;
    private String author;

    public CommentDTO(Comment comment){
        text = comment.getComment();
        author = comment.getUserAccount().getUsername();
    }

}
