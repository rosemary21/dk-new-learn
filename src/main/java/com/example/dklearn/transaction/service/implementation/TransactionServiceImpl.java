package com.example.dklearn.transaction.service.implementation;


import com.example.dklearn.admin.auth.CustomUserDetailsService;
import com.example.dklearn.admin.cloudinary.dto.CloudiaryResponseDto;
import com.example.dklearn.admin.cloudinary.service.CloudinaryStorageService;
//import com.example.dklearn.admin.productdescription.dto.PageDescription;
//import com.example.dklearn.admin.productdescription.model.Images;
//import com.example.dklearn.admin.productdescription.model.ProductDescription;
//import com.example.dklearn.admin.productdescription.repository.ProductDescriptionRepository;
import com.example.dklearn.admin.user.model.DekeralutiveUser;
import com.example.dklearn.admin.user.repository.UserRepository;
import com.example.dklearn.card.dto.CardResponse;
import com.example.dklearn.card.service.VerifyService;
import com.example.dklearn.customertransaction.model.Transaction;
import com.example.dklearn.email.dto.EmailDto;
import com.example.dklearn.email.service.implementation.TemplateEmailService;
import com.example.dklearn.images.repository.ImageRepository;
import com.example.dklearn.response.ResponseDto;
import com.example.dklearn.transaction.dto.TransactionDto;
import com.example.dklearn.transaction.dto.TransactionRecordDto;
import com.example.dklearn.transaction.repository.TransactionRepository;
import com.example.dklearn.transaction.resp.TransactionResponse;
import com.example.dklearn.transaction.service.TransactionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    UserRepository userRepository;
    @Autowired
    MessageSource messageSource;
    @Autowired
    VerifyService verifyService;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    ImageRepository imageRepository;
    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Autowired
    CloudinaryStorageService cloudinaryStorageService;

    @Autowired
    TemplateEmailService templateEmailService;

    @Override
    public TransactionResponse addTransactionRecord(TransactionDto transactionDto) {
        TransactionResponse transactionResponse=new TransactionResponse();

        try{
            ResponseDto responseDto=new ResponseDto();
            transactionDto.setTransactionStatus("Success");
            EmailDto emailDto=generateTransactionId();
            transactionDto.setTransactionId(emailDto.getTransactionId());
            boolean result=processTransaction(transactionDto);
            emailDto.setEmailAddressTo(transactionDto.getUserName());
            emailDto.setTransactionId(transactionDto.getTransactionId());
            emailDto.setTemplateName("SUCCESSFULTRANSACTION");
            templateEmailService.sendEmail(emailDto);
            responseDto.setCode(messageSource.getMessage("dk.success",null, Locale.ENGLISH));
            responseDto.setMessage(messageSource.getMessage("transaction.add.success",null,Locale.ENGLISH));
            transactionResponse.setResponseDto(responseDto);
        } catch (Exception e){
            e.printStackTrace();
        }
        return transactionResponse;

    }

//    @Override
//    public TransactionResponse uploadTransaction(TransactionDto transactionDto){
//        CloudiaryResponseDto cloudinaryResponse=null;
//
//        System.out.println("Entry getting the transaction "+transactionDto);
//        TransactionResponse transactionResponse=new TransactionResponse();
//        try{
//            ResponseDto responseDto=new ResponseDto();
////            if(transactionDto.getMultipartFile()!=null){
////                 cloudinaryResponse= cloudinaryStorageService.uploadImage(transactionDto.getMultipartFile());
////                transactionDto.setImageUrl(cloudinaryResponse.getSecureUrl());
////
////            }
//            EmailDto emailDto=generateTransactionId();
//            transactionDto.setTransactionStatus("Pending");
//            transactionDto.setTransactionId(emailDto.getTransactionId());
//            boolean result=saveuploadedTransaction(transactionDto);
//            emailDto.setEmailAddressTo(transactionDto.getUserName());
//            emailDto.setTransactionId(transactionDto.getTransactionId());
//            emailDto.setTemplateName("UPLOADED");
//            templateEmailService.sendEmail(emailDto);
//            responseDto.setCode(messageSource.getMessage("dk.success",null, Locale.ENGLISH));
//            responseDto.setMessage(messageSource.getMessage("transaction.add.success",null,Locale.ENGLISH));
//            transactionResponse.setResponseDto(responseDto);
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//        return transactionResponse;
//
//    }

    @Override
    public TransactionResponse getTransactionById(String id) {
        TransactionResponse transactionResponse=new TransactionResponse();
        ResponseDto responseDto=new ResponseDto();
        Long value=Long.valueOf(id);
        Optional<Transaction> transaction=transactionRepository.findById(value);
        transactionResponse.setTransaction(transaction.get());
        responseDto.setCode(messageSource.getMessage("dk.success",null, Locale.ENGLISH));
        responseDto.setMessage(messageSource.getMessage("transaction.id.success",null,Locale.ENGLISH));
        transactionResponse.setResponseDto(responseDto);
        return transactionResponse;
    }

    @Override
    public TransactionResponse approveTransaction(TransactionDto transactionDto){
        TransactionResponse transactionResponse=new TransactionResponse();
        ResponseDto responseDto=new ResponseDto();
        try{
           Optional<Transaction> transaction=  transactionRepository.findById(transactionDto.getId());
            transaction.get().setStatus("Approved");
            transactionRepository.save(transaction.get());
            responseDto.setCode(messageSource.getMessage("dk.success",null, Locale.ENGLISH));
            responseDto.setMessage(messageSource.getMessage("transaction.approve.success",null,Locale.ENGLISH));
            transactionResponse.setResponseDto(responseDto);
        } catch (Exception e){
            e.printStackTrace();
        }
        return transactionResponse;

    }

    @Override
    public TransactionResponse ChangeTransaction(TransactionDto transactionDto){
        TransactionResponse transactionResponse=new TransactionResponse();
        ResponseDto responseDto=new ResponseDto();
        try{
            Optional<Transaction> transaction=  transactionRepository.findById(transactionDto.getId());

            EmailDto emailDto=   generateTransactionId();
            transaction.get().setStatus(transactionDto.getTransactionStatus());
            transactionRepository.save(transaction.get());
            if(transactionDto.getTransactionStatus().equalsIgnoreCase("Approve")){
                emailDto.setTemplateName("APPROVED");
            }
            if(transactionDto.getTransactionStatus().equalsIgnoreCase("Decline")){
                emailDto.setTemplateName("DECLINED");

            }
            if(transactionDto.getTransactionStatus().equalsIgnoreCase("Confirmed")){
                emailDto.setTemplateName("CONFIRMED");
            }
            transaction.get().setTransactionId(emailDto.getTransactionId());
            DekeralutiveUser dekeralutiveUser=customUserDetailsService.getCurrentuser();

            //System.out.println("Entry get user name {}"+dekeralutiveUser.getEmail());
            emailDto.setEmailAddressTo(dekeralutiveUser.getEmail());
            emailDto.setTransactionId(transaction.get().getTransactionId());
            templateEmailService.sendEmail(emailDto);

            responseDto.setCode(messageSource.getMessage("dk.success",null, Locale.ENGLISH));
            responseDto.setMessage(messageSource.getMessage("transaction.status.success",null,Locale.ENGLISH));
            transactionResponse.setResponseDto(responseDto);
        } catch (Exception e){
            e.printStackTrace();
        }
        return transactionResponse;

    }

//    @Override
//    public TransactionResponse getAllTransactionByPage(PageDescription pageDescription) {
//        ResponseDto responseDto=new ResponseDto();
//        TransactionResponse transactionResponse=new TransactionResponse();
//        Page<Transaction> transactions=  transactionRepository.findAll(PageRequest.of(pageDescription.getPageNo(), pageDescription.getPageSize()));
//        transactionResponse.setTransactionList(converPageModelToDto(transactions));
//        responseDto.setCode(messageSource.getMessage("dk.success",null, Locale.ENGLISH));
//        responseDto.setMessage(messageSource.getMessage("product.description.all.success",null,Locale.ENGLISH));
//        transactionResponse.setResponseDto(responseDto);
//        return transactionResponse;
//    }
//
//    @Override
//    public TransactionResponse getPropertyAllTransactionByPage(PageDescription pageDescription) {
//        ResponseDto responseDto=new ResponseDto();
//        TransactionResponse transactionResponse=new TransactionResponse();
//        List<String> v=new ArrayList<>();
//        v.add("Approve");
//        v.add("Decline");
//        v.add("Pending");
//        DekeralutiveUser dekeralutiveUser=  customUserDetailsService.getCurrentuser();
//        Page<Transaction> transactions=  transactionRepository.findByDekeralutiveUserAndTransactionStatusIn(dekeralutiveUser,v,PageRequest.of(pageDescription.getPageNo(), pageDescription.getPageSize()));
//        transactionResponse.setTransactionList(converPageModelToDto(transactions));
//        responseDto.setCode(messageSource.getMessage("dk.success",null, Locale.ENGLISH));
//        responseDto.setMessage(messageSource.getMessage("product.description.all.success",null,Locale.ENGLISH));
//        transactionResponse.setResponseDto(responseDto);
//        return transactionResponse;
//    }
//
//
//    private List<TransactionDto> converPageModelToDto(Page<Transaction> transactions){
//        List<TransactionDto> productDescriptionDtoList=new ArrayList<>();
//        for(Transaction transaction:transactions){
//            TransactionDto transactionDto=   modelMapper.map(transaction,TransactionDto.class);
//            ProductDescription productDescription=  productDescriptionRepository.findFirstByCode(transactionDto.getDescriptionCode());
//            if(productDescription!=null){
//                transactionDto.setProductDescriptionId(String.valueOf(productDescription.getId()));
//            }
//            productDescriptionDtoList.add(transactionDto);
//        }
//        return productDescriptionDtoList;
//    }

//    private boolean saveuploadedTransaction(TransactionDto transactionDto){
//        try{
//            DekeralutiveUser dekeralutiveUser= userRepository.findByUserName(transactionDto.getUserName());
//            Transaction transaction=new Transaction();
//            List<Images> newImage=new ArrayList<>();
//
//            for(MultipartFile multipartFile:transactionDto.getMultipartFile()){
//                var cloudinaryResponse1 = cloudinaryStorageService.uploadImage(multipartFile);
//                Images image=new Images();
//                image.setImageUrl(cloudinaryResponse1.getSecureUrl());
//                Images saveImage=imageRepository.save(image);
//                newImage.add(saveImage);
//            }
//
//            for(TransactionRecordDto recordDto:transactionDto.getDescriptionCodeList()){
//               // transaction.setTransactionDate(LocalDateTime.now());
//                transaction.setCurrency(transactionDto.getCurrency());
//               // transaction.setDekeralutiveUser(dekeralutiveUser);
//                transaction.setAmount(recordDto.getAmount());
//              //  transaction.setDescription(recordDto.getProductDescription());
//              //  transaction.setTransactionStatus(transactionDto.getTransactionStatus());
//               // transaction.setDescriptionCode(recordDto.getProductDescriptionCode());
//               // transaction.setProductCategoryCode(recordDto.getProductCategoryCode());
////                transaction.setProductCode(recordDto.getProductCode());
////                transaction.setImageUrl(transactionDto.getImageUrl());
////                transaction.setImageList(newImage);
//                transactionRepository.save(transaction);
//
//            }
//        }
//
//        catch (Exception e){
//            e.printStackTrace();
//        }
//
//        return true;
//    }


    private boolean processTransaction(TransactionDto transactionDto){
        DekeralutiveUser dekeralutiveUser= userRepository.findByUserName(transactionDto.getUserName());
        Transaction transaction=new Transaction();
        CardResponse cardResponse=verifyService.verifyTransaction(transactionDto.getReference());
        if(cardResponse.getResponseDto().getCode().equals(messageSource.getMessage("dk.success",null, Locale.ENGLISH))) {
            for(TransactionRecordDto recordDto:transactionDto.getDescriptionCodeList()){
         //       transaction.setTransactionDate(LocalDateTime.now());
                transaction.setCurrency(transactionDto.getCurrency());
        //        transaction.setDekeralutiveUser(dekeralutiveUser);
                transaction.setAmount(recordDto.getAmount());
        //        transaction.setDescription(recordDto.getProductDescription());
//                transaction.setTransactionStatus(transactionDto.getTransactionStatus());
//                transaction.setDescriptionCode(recordDto.getProductDescriptionCode());
//                transaction.setProductCategoryCode(recordDto.getProductCategoryCode());
//                transaction.setProductCode(recordDto.getProductCode());
//                transaction.setImageUrl(transactionDto.getImageUrl());
//                transaction.setStockId(recordDto.getStockId());
                transaction.setTransactionId(transactionDto.getTransactionId());
                transactionRepository.save(transaction);
            }
        }

        return true;
    }

    @Override
    public TransactionResponse processTransactionRecord(TransactionDto transactionDto) {
        TransactionResponse transactionResponse=new TransactionResponse();
        ResponseDto responseDto=new ResponseDto();
        CardResponse cardResponse=verifyService.verifyTransaction(transactionDto.getReference());

        if(cardResponse.getResponseDto().getCode().equals(messageSource.getMessage("dk.success",null, Locale.ENGLISH))){

        Optional<Transaction>  transaction=transactionRepository.findById(transactionDto.getId());

          transaction.get().setStatus("Success");
          EmailDto emailDto=   generateTransactionId();
          transaction.get().setTransactionId(emailDto.getTransactionId());
           transactionRepository.save(transaction.get());
            emailDto.setEmailAddressTo(transactionDto.getUserName());
            emailDto.setTransactionId(transaction.get().getTransactionId());
            emailDto.setTemplateName("SUCCESSFULTRANSACTION");
            templateEmailService.sendEmail(emailDto);
        }

        responseDto.setCode(messageSource.getMessage("dk.success",null, Locale.ENGLISH));
        responseDto.setMessage(messageSource.getMessage("transaction.process.success",null,Locale.ENGLISH));
        transactionResponse.setResponseDto(responseDto);
        return transactionResponse;
    }


    public EmailDto generateTransactionId() {
        EmailDto emailDto=new EmailDto();
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        String value=String.format("%12d", number);
        emailDto.setTransactionId(value);
        return emailDto;
    }


}
