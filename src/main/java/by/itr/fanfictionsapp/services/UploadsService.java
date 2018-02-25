package by.itr.fanfictionsapp.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;

@Service
public class UploadsService {

    private final Cloudinary cloudinary;
    private final ObjectMapper objectMapper;

    public UploadsService(Environment env, ObjectMapper objectMapper){
        this.objectMapper = objectMapper;
        this.cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", env.getProperty("cloudinary.cloudName"),
                "api_key", env.getProperty("cloudinary.apiKey"),
                "api_secret", env.getProperty("cloudinary.apiSecret")
        ));
    }

    public String uploadImage(MultipartFile file) throws IOException{
        File f = Files.createTempFile("temp", file.getOriginalFilename()).toFile();
        file.transferTo(f);
        Map uploadResult = cloudinary.uploader().upload(f, ObjectUtils.emptyMap());
        String imageURL = (String)uploadResult.get("url");
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("imageURL", imageURL);
        return objectNode.toString();
    }

}
