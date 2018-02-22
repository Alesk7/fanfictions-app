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
    public List<FanfictionDTO> getMyFanfictions(@RequestParam("page") int page) {
        return fanfictionsService.getUserFanfictions(null, page);
    }

}
