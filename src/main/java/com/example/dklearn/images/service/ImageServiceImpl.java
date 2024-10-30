package com.example.dklearn.images.service;

import com.example.dklearn.admin.cloudinary.dto.CloudiaryResponseDto;
import com.example.dklearn.admin.cloudinary.service.CloudinaryStorageService;
import com.example.dklearn.images.dto.ImageDto;
import com.example.dklearn.images.dto.ImageReponse;
import com.example.dklearn.images.model.Images;
import com.example.dklearn.images.repository.ImageRepository;
import com.example.dklearn.response.ResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class ImageServiceImpl implements ImageService{

    @Autowired
    ImageRepository imageRepository;
    @Autowired
    CloudinaryStorageService cloudinaryStorageService;
    @Autowired
    ModelMapper mapper;
    @Autowired
    MessageSource messageSource;

    @Override
    public ImageReponse addImages(ImageDto imageDto) {
        try{
            ResponseDto responseDto=new ResponseDto();
            ImageReponse imageReponse=new ImageReponse();
            CloudiaryResponseDto url=new CloudiaryResponseDto();
            if(imageDto.getType().equalsIgnoreCase("image")){
                 url= cloudinaryStorageService.uploadImage(imageDto.getMultipartFile());
            }

            if(imageDto.getType().equalsIgnoreCase("video")){
                url= cloudinaryStorageService.uploadVideo(imageDto.getMultipartFile());
            }
            Images images=mapper.map(imageDto, Images.class);
            images.setUrl(url.getSecureUrl());
            imageRepository.save(images);
            responseDto.setCode(messageSource.getMessage("dk.success",null, Locale.ENGLISH));
            responseDto.setMessage(messageSource.getMessage("image.add.success",null,Locale.ENGLISH));
            imageReponse.setResponseDto(responseDto);
            imageReponse.setUrl(url.getSecureUrl());
            return imageReponse;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }
}
