package by.itr.fanfictionsapp.services;

import by.itr.fanfictionsapp.models.Fanfiction;
import by.itr.fanfictionsapp.models.Rating;
import by.itr.fanfictionsapp.models.UserAccount;
import by.itr.fanfictionsapp.repositories.FanfictionsRepository;
import by.itr.fanfictionsapp.repositories.RatingRepository;
import by.itr.fanfictionsapp.services.dto.FanfictionRatingDTO;
import by.itr.fanfictionsapp.services.dto.RatingDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RatingsService {

    private final RatingRepository ratingRepository;
    private final FanfictionsRepository fanfictionsRepository;
    private final UserAccountService userAccountService;

    public FanfictionRatingDTO getFanfictionRating(Long fanfictionId){
        FanfictionRatingDTO fanfictionRatingDTO = new FanfictionRatingDTO();
        Double averageRate = ratingRepository.getAverageRateByFanfictionId(fanfictionId);
        fanfictionRatingDTO.setAverageRate(averageRate.floatValue());
        UserAccount userAccount = userAccountService.findUser(null);
        Integer myRate = ratingRepository.getUserRate(userAccount.getId(), fanfictionId);
        fanfictionRatingDTO.setMyRate(myRate);
        return fanfictionRatingDTO;
    }

    public void addRatingToFanfiction(Long fanfictionId, RatingDTO rating){
        Fanfiction fanfiction = fanfictionsRepository.findOne(fanfictionId);
        UserAccount userAccount = userAccountService.findUser(null);
        if(ratingRepository.getUserRate(userAccount.getId(), fanfictionId) == null) {
            Rating newRate = new Rating(rating.getRate(), userAccount, fanfiction);
            ratingRepository.save(newRate);
            fanfiction.getRates().add(newRate);
            fanfictionsRepository.save(fanfiction);
        }
    }

}
