package by.itr.fanfictionsapp.repositories;

import by.itr.fanfictionsapp.models.Tag;
import org.springframework.data.repository.CrudRepository;

public interface TagsRepository extends CrudRepository<Tag, String> {
    Tag findByTag(String tag);
}
