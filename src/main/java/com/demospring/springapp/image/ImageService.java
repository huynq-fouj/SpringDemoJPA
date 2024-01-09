package com.demospring.springapp.image;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Service
public class ImageService {
    
    public Map uploadToCluodinary(MultipartFile file) {
        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "dxj5ohl9j",
            "api_key", "789545947643535",
            "api_secret", "twsxl3lOKUFroBVQvZr4eE0iNdQ",
            "secure", true
        ));

        try {
            return cloudinary.uploader().upload(file.getBytes(),ObjectUtils.emptyMap());
        } catch (IOException e) {
            throw new RuntimeException("Image upload fail");
        }
    }

}
