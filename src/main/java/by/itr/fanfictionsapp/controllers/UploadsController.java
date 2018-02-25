package by.itr.fanfictionsapp.controllers;

import by.itr.fanfictionsapp.services.UploadsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/uploads")
@RequiredArgsConstructor
public class UploadsController {

    private final UploadsService uploadsService;

    @PostMapping("/uploadImage")
    @ResponseStatus(HttpStatus.OK)
    public String uploadImage(@RequestBody MultipartFile file) throws IOException {
        return uploadsService.uploadImage(file);
    }

}
