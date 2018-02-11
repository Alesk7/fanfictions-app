package by.itr.fanfictionsapp.services;

import by.itr.fanfictionsapp.models.UserAccount;
import by.itr.fanfictionsapp.repositories.UserAccountRepository;
import by.itr.fanfictionsapp.security.models.UserAccountDetails;
import by.itr.fanfictionsapp.services.dto.AppUserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserAccountRepository userAccountRepository;

    public AppUserDTO getMe(){
        UserAccountDetails userDetails = (UserAccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserAccount user = userAccountRepository.findOne(userDetails.getId());
        return new AppUserDTO(user);
    }
}
