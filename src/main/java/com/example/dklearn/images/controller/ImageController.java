package com.example.dklearn.images.controller;

import com.example.dklearn.courses.dto.CoursesDto;
import com.example.dklearn.courses.resp.CourseResponse;
import com.example.dklearn.images.dto.ImageDto;
import com.example.dklearn.images.dto.ImageReponse;
import com.example.dklearn.images.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "/dl/api/v1/images", produces = {MediaType.APPLICATION_JSON_VALUE})

public class ImageController {

    @Autowired
    ImageService imageService;

    @RequestMapping(value = "/add" ,method = RequestMethod.POST)
    public ResponseEntity<ImageReponse> addImages(@RequestParam("files") MultipartFile files, @RequestParam("name") String name, @RequestParam("type") String type){
        ImageDto imageDto=new ImageDto();
        imageDto.setMultipartFile(files);
        imageDto.setName(name);
        imageDto.setType(type);
        ImageReponse courseResponse=imageService.addImages(imageDto);
        if(courseResponse.getResponseDto().getCode().equalsIgnoreCase("dk00")){
            return new ResponseEntity<ImageReponse>(courseResponse, HttpStatus.OK);
        }
        else
            return new ResponseEntity<ImageReponse>(courseResponse, HttpStatus.BAD_REQUEST);
    }
}
