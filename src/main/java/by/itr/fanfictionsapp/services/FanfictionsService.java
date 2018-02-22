package by.itr.fanfictionsapp.services;

import by.itr.fanfictionsapp.models.Fanfiction;
import by.itr.fanfictionsapp.repositories.FanfictionsRepository;
import by.itr.fanfictionsapp.security.models.UserAccountDetails;
import by.itr.fanfictionsapp.services.dto.FanfictionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class FanfictionsService {

    private final FanfictionsRepository fanfictionsRepository;

    public List<FanfictionDTO> getUserFanfictions(String email){
        Iterable<Fanfiction> fanfictions;
        if(email == null){
            Long id = ((UserAccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
            fanfictions = fanfictionsRepository.findByUserAccountId(id);
        } else {
            fanfictions = fanfictionsRepository.findByUserAccountEmail(email);
        }
        return StreamSupport.stream(fanfictions.spliterator(), false)
                .map(FanfictionDTO::new)
                .collect(Collectors.toList());
    }

}
