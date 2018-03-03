package by.itr.fanfictionsapp.repositories;

import by.itr.fanfictionsapp.models.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentsRepository extends CrudRepository<Comment, Long> {}
