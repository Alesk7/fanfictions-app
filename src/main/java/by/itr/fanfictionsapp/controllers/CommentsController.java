package by.itr.fanfictionsapp.controllers;

import by.itr.fanfictionsapp.services.CommentsService;
import by.itr.fanfictionsapp.services.dto.CommentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentsController {

    private final CommentsService commentsService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.OK)
    public void addComment(@RequestParam("fanfictionId") Long id,
                           @RequestBody CommentDTO commentDTO){
        commentsService.createComment(commentDTO, id);
    }

}
