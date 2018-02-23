package by.itr.fanfictionsapp.services;

import by.itr.fanfictionsapp.models.Tag;
import by.itr.fanfictionsapp.repositories.TagsRepository;
import by.itr.fanfictionsapp.services.dto.TagDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class TagsService {

    private final TagsRepository tagsRepository;

    public List<TagDTO> getTags(){
        Iterable<Tag> tags = tagsRepository.findAll();
        return StreamSupport.stream(tags.spliterator(), false)
                .map(TagDTO::new)
                .collect(Collectors.toList());
    }

}
