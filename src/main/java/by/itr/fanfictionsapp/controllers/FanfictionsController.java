package by.itr.fanfictionsapp.controllers;

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

    @PostMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void updateFanfiction(@RequestBody FanfictionDTO fanfictionDTO){
        fanfictionsService.updateFanfiction(fanfictionDTO);
    }

    @GetMapping("/get/{fanfictionId}")
    @ResponseStatus(HttpStatus.OK)
    public FanfictionDTO getFanfiction(@PathVariable Long fanfictionId,
                                       @RequestParam(value = "email", required = false) String userEmail){
        return fanfictionsService.getFanfiction(fanfictionId, userEmail);
    }

    @PostMapping("/delete/{fanfictionId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteFanfiction (@PathVariable Long fanfictionId){
        fanfictionsService.deleteFanfiction(fanfictionId);
    }

    @GetMapping("/tags")
    @ResponseStatus(HttpStatus.OK)
    public List<TagDTO> getTags(){
        return tagsService.getTags();
    }

}
