package com.example.dklearn.images.service;

import com.example.dklearn.images.dto.ImageDto;
import com.example.dklearn.images.dto.ImageReponse;

public interface ImageService {

    ImageReponse addImages(ImageDto imageDto);
}
