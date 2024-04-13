package com.demospring.springapp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.demospring.springapp.service.ImageService;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/api/images")
public class ImageController {

    private final ImageService imageService;
    private final Cloudinary cloudinary;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
        this.cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "dxj5ohl9j",
            "api_key", "789545947643535",
            "api_secret", "twsxl3lOKUFroBVQvZr4eE0iNdQ",
            "secure", true
        ));
    }
    
    @PostMapping("/upload")
    public Map uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap(
            "transformation", new Transformation()
            .width(1000).crop("scale").chain()
            .quality("auto:best").chain()
            .fetchFormat("auto")
        ));
        String url = cloudinary.url().generate(uploadResult.get("public_id").toString());
        uploadResult.put("result_url", url);
        return uploadResult;
    }

    @DeleteMapping("/{id}")
    public Map deleteImg(@PathVariable("id") String id) throws IOException {
        return cloudinary.uploader().destroy(id, ObjectUtils.emptyMap());
    }
    

}
