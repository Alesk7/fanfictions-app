package by.itr.fanfictionsapp.controllers;

import by.itr.fanfictionsapp.services.FanfictionsService;
import by.itr.fanfictionsapp.services.dto.FanfictionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/fanfictions")
@RequiredArgsConstructor
public class FanfictionsController {

    private final FanfictionsService fanfictionsService;

    @GetMapping("/my")
    @ResponseStatus(HttpStatus.OK)
    public List<FanfictionDTO> getMyFanfictions(){
        return fanfictionsService.getUserFanfictions(null);
    }

}
