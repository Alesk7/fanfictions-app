package by.itr.fanfictionsapp.controllers;

import by.itr.fanfictionsapp.services.RatingsService;
import by.itr.fanfictionsapp.services.dto.FanfictionRatingDTO;
import by.itr.fanfictionsapp.services.dto.RatingDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rate")
@RequiredArgsConstructor
public class RatingController {

    private final RatingsService ratingsService;

    @GetMapping("/get")
    @ResponseStatus(HttpStatus.OK)
    public FanfictionRatingDTO getFanfictionRating(@RequestParam("fanfictionId") Long fanfictionId){
        return ratingsService.getFanfictionRating(fanfictionId);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.OK)
    public void addChapterRating(@RequestParam("fanfictionId") Long fanfictionId,
                                 @RequestBody RatingDTO rating){
        ratingsService.addRatingToFanfiction(fanfictionId, rating);
    }

}
