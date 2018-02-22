package by.itr.fanfictionsapp.services;

import by.itr.fanfictionsapp.models.Fanfiction;
import by.itr.fanfictionsapp.repositories.FanfictionsRepository;
import by.itr.fanfictionsapp.security.models.UserAccountDetails;
import by.itr.fanfictionsapp.services.dto.FanfictionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class FanfictionsService {

    private final FanfictionsRepository fanfictionsRepository;

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

}
