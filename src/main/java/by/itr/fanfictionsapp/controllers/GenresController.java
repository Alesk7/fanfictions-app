package by.itr.fanfictionsapp.controllers;

import by.itr.fanfictionsapp.models.Genre;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/genres")
public class GenresController {

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public Genre[] getAllGenres(){
        return Genre.values();
    }

}
