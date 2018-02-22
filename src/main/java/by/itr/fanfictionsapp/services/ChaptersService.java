package by.itr.fanfictionsapp.services;

import by.itr.fanfictionsapp.models.Chapter;
import by.itr.fanfictionsapp.models.Fanfiction;
import by.itr.fanfictionsapp.repositories.ChaptersRepository;
import by.itr.fanfictionsapp.repositories.FanfictionsRepository;
import by.itr.fanfictionsapp.services.dto.ChapterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChaptersService {

    private final ChaptersRepository chaptersRepository;
    private final FanfictionsRepository fanfictionsRepository;

    public ChapterDTO getChapter(Long fanfictionId, int chapter){
        Chapter c = fanfictionsRepository.findOne(fanfictionId).getChapters().get(chapter);
        return new ChapterDTO(c);
    }

    @Transactional
    public void createChapters(List<ChapterDTO> chapters, Long fanfictionId){
        List<Chapter> chapterList = new ArrayList<>();
        for(ChapterDTO c: chapters){
            Chapter chapter = new Chapter(c);
            chapterList.add(chapter);
            chaptersRepository.save(chapter);
        }
        Fanfiction fanfiction = fanfictionsRepository.findOne(fanfictionId);
        fanfiction.setChapters(chapterList);
        fanfictionsRepository.save(fanfiction);
    }

}
