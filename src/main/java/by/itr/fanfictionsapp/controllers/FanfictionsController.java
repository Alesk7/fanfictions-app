package by.itr.fanfictionsapp.controllers;

import by.itr.fanfictionsapp.services.FanfictionsService;
import by.itr.fanfictionsapp.services.dto.FanfictionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fanfictions")
@RequiredArgsConstructor
public class FanfictionsController {

    private final FanfictionsService fanfictionsService;

    @GetMapping("/pages")
    @ResponseStatus(HttpStatus.OK)
    public Long getEntitiesCount(){
        return fanfictionsService.getEntitiesCount();
    }

    @GetMapping("/my")
    @ResponseStatus(HttpStatus.OK)
    public List<FanfictionDTO> getMyFanfictions(@RequestParam(value = "email", required = false) String email,
                                                @RequestParam("page") int page) {
        return fanfictionsService.getUserFanfictions(email, page);
    }

    @GetMapping("/fresh")
    @ResponseStatus(HttpStatus.OK)
    public List<FanfictionDTO> getFreshFanfictions(@RequestParam("page") int page){
        return fanfictionsService.getFreshFanfictions(page);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public void createFanfiction(@RequestParam(value = "email", required = false) String email,
                                 @RequestBody FanfictionDTO fanfictionDTO){
        fanfictionsService.createFanfiction(email, fanfictionDTO);
    }

}
