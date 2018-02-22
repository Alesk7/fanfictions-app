package by.itr.fanfictionsapp.services;

import by.itr.fanfictionsapp.models.Chapter;
import by.itr.fanfictionsapp.repositories.ChaptersRepository;
import by.itr.fanfictionsapp.repositories.FanfictionsRepository;
import by.itr.fanfictionsapp.services.dto.ChapterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChaptersService {

    private final ChaptersRepository chaptersRepository;
    private final FanfictionsRepository fanfictionsRepository;

    public ChapterDTO getChapter(Long fanfictionId, int chapter){
        Chapter c = fanfictionsRepository.findOne(fanfictionId).getChapters().get(chapter);
        return new ChapterDTO(c);
    }

}
