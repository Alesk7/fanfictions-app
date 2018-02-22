package by.itr.fanfictionsapp.services;

import by.itr.fanfictionsapp.models.Fanfiction;
import by.itr.fanfictionsapp.models.Tag;
import by.itr.fanfictionsapp.models.UserAccount;
import by.itr.fanfictionsapp.repositories.FanfictionsRepository;
import by.itr.fanfictionsapp.repositories.TagsRepository;
import by.itr.fanfictionsapp.repositories.UserAccountRepository;
import by.itr.fanfictionsapp.security.models.UserAccountDetails;
import by.itr.fanfictionsapp.services.dto.FanfictionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class FanfictionsService {

    private final FanfictionsRepository fanfictionsRepository;
    private final UserAccountRepository userAccountRepository;
    private final TagsRepository tagsRepository;

    public List<FanfictionDTO> getUserFanfictions(String email, int page){
        Iterable<Fanfiction> fanfictions;
        if(email == null){
            Long id = ((UserAccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
            fanfictions = fanfictionsRepository.findByUserAccountId(id, new PageRequest(page, 10));
        } else {
            fanfictions = fanfictionsRepository.findByUserAccountEmail(email, new PageRequest(page, 10));
        }
        return StreamSupport.stream(fanfictions.spliterator(), false)
                .map(FanfictionDTO::new)
                .collect(Collectors.toList());
    }

    public Long getEntitiesCount(){
        return fanfictionsRepository.count();
    }

    public List<FanfictionDTO> getFreshFanfictions(int page){
        Iterable<Fanfiction> fanfictions = fanfictionsRepository.findFresh(new PageRequest(page, 10));
        return StreamSupport.stream(fanfictions.spliterator(), false)
                .map(FanfictionDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void createFanfiction(String email, FanfictionDTO fanfictionDTO){
        UserAccount userAccount;
        if(email == null){
            Long id = ((UserAccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
            userAccount = userAccountRepository.findOne(id);
        } else {
            userAccount = userAccountRepository.findByEmail(email);
        }
        Fanfiction fanfiction = new Fanfiction(fanfictionDTO, userAccount);
        fanfiction.setTags(getTags(fanfictionDTO.getTags()));
        fanfictionsRepository.save(fanfiction);
    }

    private List<Tag> getTags(List<String> tagStrings){
        List<Tag> tags = new ArrayList<>();
        for(String t: tagStrings){
            Tag tag = tagsRepository.findByTag(t);
            if(tag == null){
                Tag newTag = new Tag(t, 1);
                tagsRepository.save(newTag);
                tags.add(newTag);
            } else {
                tags.add(tag);
            }
        }
        return tags;
    }

}
