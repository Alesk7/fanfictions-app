package by.itr.fanfictionsapp.services;

import by.itr.fanfictionsapp.models.Chapter;
import by.itr.fanfictionsapp.models.Fanfiction;
import by.itr.fanfictionsapp.models.Tag;
import by.itr.fanfictionsapp.models.UserAccount;
import by.itr.fanfictionsapp.repositories.FanfictionsRepository;
import by.itr.fanfictionsapp.repositories.TagsRepository;
import by.itr.fanfictionsapp.security.models.UserAccountDetails;
import by.itr.fanfictionsapp.services.dto.FanfictionDTO;
import by.itr.fanfictionsapp.services.dto.FanfictionResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FanfictionsService {

    private final FanfictionsRepository fanfictionsRepository;
    private final ChaptersService chaptersService;
    private final UserAccountService userAccountService;
    private final TagsRepository tagsRepository;

    public FanfictionResponseDTO getUserFanfictions(String email, int page){
        if(email == null){
            Long id = ((UserAccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
            return new FanfictionResponseDTO(fanfictionsRepository.findByUserAccountId(id, new PageRequest(page, 10)));
        } else {
            return new FanfictionResponseDTO(fanfictionsRepository.findByUserAccountEmail(email, new PageRequest(page, 10)));
        }
    }

    public FanfictionResponseDTO getFreshFanfictions(int page){
        return new FanfictionResponseDTO(fanfictionsRepository.findFresh(new PageRequest(page, 10)));
    }

    public FanfictionDTO getFanfiction(Long fanfictionId, String userEmail){
        FanfictionDTO fanfictionDTO = null;
        Fanfiction fanfiction = fanfictionsRepository.findOne(fanfictionId);
        if(fanfiction.getUserAccount().getEmail().equals(userEmail) || userEmail == null) {
            fanfictionDTO = new FanfictionDTO(fanfiction);
            fanfictionDTO.setChapters(fanfiction.getChapters());
            fanfictionDTO.setComments(fanfiction.getComments());
        }
        return fanfictionDTO;
    }

    public void deleteFanfiction(Long id){
        fanfictionsRepository.delete(id);
    }

    @Transactional
    public void createFanfiction(String email, FanfictionDTO fanfictionDTO){
        UserAccount userAccount = userAccountService.findUser(email);
        Fanfiction fanfiction = new Fanfiction(fanfictionDTO, userAccount);
        fanfiction.setTags(getTags(fanfictionDTO.getTags()));
        fanfictionsRepository.save(fanfiction);
        List<Chapter> chapters = chaptersService.createChapters(fanfictionDTO.getChapters());
        fanfiction.setChapters(chapters);
        fanfictionsRepository.save(fanfiction);
    }

    @Transactional
    public void updateFanfiction(FanfictionDTO fanfictionDTO){
        Fanfiction fanfiction = fanfictionsRepository.findOne(fanfictionDTO.getId());
        fanfiction.setDataFromDTO(fanfictionDTO);
        fanfiction.setTags(getTags(fanfictionDTO.getTags()));
        List<Chapter> chapters = chaptersService.createChapters(fanfictionDTO.getChapters());
        fanfiction.setChapters(chapters);
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
                tag.setWeight(tag.getWeight() + 1);
                tags.add(tag);
            }
        }
        return tags;
    }

}
