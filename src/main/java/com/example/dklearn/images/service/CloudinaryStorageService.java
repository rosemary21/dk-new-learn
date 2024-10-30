//package com.example.dklearn.images.service;
//
//
//import com.cloudinary.Cloudinary;
//import com.cloudinary.EagerTransformation;
//import com.cloudinary.utils.ObjectUtils;
//import com.example.dklearn.admin.cloudinary.dto.CloudiaryResponseDto;
//import com.fasterxml.jackson.databind.DeserializationFeature;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.Arrays;
//
//@Slf4j
//@Service
//public class CloudinaryStorageService implements StorageService {
//    @Autowired
//    private Cloudinary cloudinaryConfig;
//
//    @Override
//    public CloudiaryResponseDto uploadImage(MultipartFile file) throws IOException {
//        final ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        File uploadedFile = convertMultiPartToFile(file);
//        var result = cloudinaryConfig.uploader().upload(uploadedFile, ObjectUtils.emptyMap());
//        uploadedFile.delete();
//        return mapper.convertValue(result, CloudiaryResponseDto.class);
//    }
//
//    private File convertMultiPartToFile(MultipartFile file) throws IOException {
//        File convFile = new File(file.getOriginalFilename());
//        FileOutputStream fos = new FileOutputStream(convFile);
//        fos.write(file.getBytes());
//        fos.close();
//        return convFile;
//    }
//
//
//    @Override
//    public CloudiaryResponseDto uploadVideo(MultipartFile file) throws IOException {
//        final ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        File uploadedFile = convertMultiPartToFile(file);
//        log.info("About uploading the videos");
//        var result= cloudinaryConfig.uploader().upload(uploadedFile,
//                ObjectUtils.asMap("resource_type", "video",
//                        "public_id", "myfolder/mysubfolder/dog_closeup",
//                        "eager", Arrays.asList(
//                                new EagerTransformation().width(300).height(300).crop("pad").audioCodec("none"),
//                                new EagerTransformation().width(160).height(100).crop("crop").gravity("south").audioCodec("none")),
//                        "eager_async", true,
//                        "eager_notification_url", "https://mysite.example.com/notify_endpoint"));
//        uploadedFile.delete();
//        return mapper.convertValue(result, CloudiaryResponseDto.class);
//    }
//}
