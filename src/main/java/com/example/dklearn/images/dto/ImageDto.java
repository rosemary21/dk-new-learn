package com.example.dklearn.images.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
public class ImageDto {
    private MultipartFile multipartFile;
    private String name;
    private String type;
}
