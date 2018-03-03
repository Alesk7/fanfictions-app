package by.itr.fanfictionsapp.services;

import by.itr.fanfictionsapp.models.Comment;
import by.itr.fanfictionsapp.models.Fanfiction;
import by.itr.fanfictionsapp.models.UserAccount;
import by.itr.fanfictionsapp.repositories.CommentsRepository;
import by.itr.fanfictionsapp.repositories.FanfictionsRepository;
import by.itr.fanfictionsapp.services.dto.CommentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentsService {

    private final CommentsRepository commentsRepository;
    private final UserAccountService userAccountService;
    private final FanfictionsRepository fanfictionsRepository;

    public void createComment(CommentDTO commentDTO, Long fanfictionId){
        UserAccount userAccount = userAccountService.findUser(null);
        Comment comment = new Comment(commentDTO);
        comment.setUserAccount(userAccount);
        Fanfiction fanfiction = fanfictionsRepository.findOne(fanfictionId);
        comment.setFanfiction(fanfiction);
        commentsRepository.save(comment);
        fanfiction.getComments().add(comment);
        fanfictionsRepository.save(fanfiction);
    }

}
