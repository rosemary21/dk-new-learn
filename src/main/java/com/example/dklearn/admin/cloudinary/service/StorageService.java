package com.example.dklearn.admin.cloudinary.service;


import com.example.dklearn.admin.cloudinary.dto.CloudiaryResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface StorageService {

    CloudiaryResponseDto uploadImage(MultipartFile file) throws IOException;

    CloudiaryResponseDto uploadVideo(MultipartFile file) throws IOException;
}
