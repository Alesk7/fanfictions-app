package by.itr.fanfictionsapp.controllers;

import by.itr.fanfictionsapp.services.ChaptersService;
import by.itr.fanfictionsapp.services.dto.ChapterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chapters")
@RequiredArgsConstructor
public class ChaptersController {

    private final ChaptersService chaptersService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ChapterDTO getChapter(@RequestParam("fanfictionId") Long fanfictionId,
                                 @RequestParam("chapter") int chapter){
        return chaptersService.getChapter(fanfictionId, chapter);
    }

}
