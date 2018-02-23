package by.itr.fanfictionsapp.controllers;

import by.itr.fanfictionsapp.models.Genre;
import by.itr.fanfictionsapp.services.FanfictionsService;
import by.itr.fanfictionsapp.services.TagsService;
import by.itr.fanfictionsapp.services.dto.FanfictionDTO;
import by.itr.fanfictionsapp.services.dto.FanfictionResponseDTO;
import by.itr.fanfictionsapp.services.dto.TagDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fanfictions")
@RequiredArgsConstructor
public class FanfictionsController {

    private final FanfictionsService fanfictionsService;
    private final TagsService tagsService;

    @GetMapping("/my")
    @ResponseStatus(HttpStatus.OK)
    public FanfictionResponseDTO getMyFanfictions(@RequestParam(value = "email", required = false) String email,
                                                  @RequestParam("page") int page) {
        return fanfictionsService.getUserFanfictions(email, page);
    }

    @GetMapping("/fresh")
    @ResponseStatus(HttpStatus.OK)
    public FanfictionResponseDTO getFreshFanfictions(@RequestParam("page") int page){
        return fanfictionsService.getFreshFanfictions(page);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public void createFanfiction(@RequestParam(value = "email", required = false) String email,
                                 @RequestBody FanfictionDTO fanfictionDTO){
        fanfictionsService.createFanfiction(email, fanfictionDTO);
    }

    @GetMapping("/get/{fanfictionId}")
    @ResponseStatus(HttpStatus.OK)
    public FanfictionDTO getFanfiction(@PathVariable Long fanfictionId,
                                       @RequestParam(value = "userId", required = false) Long userId){
        return fanfictionsService.getFanfiction(fanfictionId, userId);
    }

    @GetMapping("/genres")
    @ResponseStatus(HttpStatus.OK)
    public Genre[] getGenres(){
        return Genre.values();
    }

    @GetMapping("/tags")
    @ResponseStatus(HttpStatus.OK)
    public List<TagDTO> getTags(){
        return tagsService.getTags();
    }

}
