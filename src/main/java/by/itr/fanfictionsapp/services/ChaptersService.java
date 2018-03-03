package by.itr.fanfictionsapp.services;

import by.itr.fanfictionsapp.models.Chapter;
import by.itr.fanfictionsapp.repositories.ChaptersRepository;
import by.itr.fanfictionsapp.services.dto.ChapterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChaptersService {

    private final ChaptersRepository chaptersRepository;

    public List<Chapter> createChapters(List<ChapterDTO> chapters){
        List<Chapter> chapterList = new ArrayList<>();
        for(ChapterDTO c: chapters){
            Chapter chapter = new Chapter(c);
            chapterList.add(chapter);
            chaptersRepository.save(chapter);
        }
        return chapterList;
    }

}
