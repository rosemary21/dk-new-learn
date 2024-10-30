package com.example.dklearn.customertransaction.service.ServiceImpl;

import com.example.dklearn.admin.auth.CustomerUserDetails;
import com.example.dklearn.admin.user.model.DekeralutiveUser;
import com.example.dklearn.admin.user.repository.UserRepository;
import com.example.dklearn.card.dto.CardResponse;
import com.example.dklearn.card.service.CardService;
import com.example.dklearn.card.service.serviceimpl.VerifyTranServiceImpl;
import com.example.dklearn.courses.model.Courses;
import com.example.dklearn.courses.repository.CourseRepository;
import com.example.dklearn.customertransaction.dto.CustomerTransactionDto;
import com.example.dklearn.customertransaction.dto.TransactionDto;
import com.example.dklearn.customertransaction.model.Transaction;
import com.example.dklearn.customertransaction.resp.TransactionResponse;
import com.example.dklearn.customertransaction.service.PaymentMethodService;
import com.example.dklearn.response.ResponseDto;
import com.example.dklearn.transaction.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@Slf4j
public class PaymentMethodServiceImpl implements PaymentMethodService {

    @Autowired
    CardService cardService;

    @Autowired
    VerifyTranServiceImpl verifyTranService;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MessageSource messageSource;

    @Override
    public TransactionResponse processTransactionRecord(CustomerTransactionDto transactionDto) {
        TransactionResponse transactionResponse=new TransactionResponse();
        ResponseDto responseDto=new ResponseDto();
      //  List<Courses> courses=new ArrayList<>();
        String userName=  getCurrentuser();
        log.info("Entry getting the user {}",userName);

        DekeralutiveUser user= userRepository.findByUserName(userName);
        List<Courses> courses=user.getCoursesList();

        CardResponse cardResponse=verifyTranService.verifyTransaction(transactionDto.getReference());
        if(!(cardResponse.getResponseDto().getCode().equals(messageSource.getMessage("dk.success",null, Locale.ENGLISH)))) {
            responseDto.setCode(messageSource.getMessage("dk.error",null, Locale.ENGLISH));
            responseDto.setMessage(messageSource.getMessage("transaction.failed.success",null,Locale.ENGLISH));
            transactionResponse.setResponseDto(responseDto);
            return transactionResponse;
        }
        for(TransactionDto transactionDto1 :transactionDto.getTransactionDtoList()){
           Optional<Courses> courses1=courseRepository.findById(transactionDto1.getCourseId());
           courses.add(courses1.get());
            if(cardResponse.getResponseDto().getCode().equals(messageSource.getMessage("dk.success",null, Locale.ENGLISH))){
                Transaction transaction=new Transaction();
                transaction.setTransactionId(transactionDto.getReference());
                transaction.setAmount(transactionDto1.getAmount());
                transaction.setCurrency(transactionDto1.getCurrency());
                transaction.setEmailAddress(user.getEmail());
                transaction.setCourseId(String.valueOf(transactionDto1.getCourseId()));
                transactionRepository.save(transaction);
            }
        }
        user.setCoursesList(courses);
        userRepository.save(user);
        responseDto.setCode(messageSource.getMessage("dk.success",null, Locale.ENGLISH));
        responseDto.setMessage(messageSource.getMessage("transaction.process.success",null,Locale.ENGLISH));
        transactionResponse.setResponseDto(responseDto);
        return transactionResponse;
    }

    public String getCurrentuser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        log.info("principal value {}",principal);

        if (principal instanceof CustomerUserDetails) {
            String username = ((CustomerUserDetails)principal).getUsername();
            log.info("gettimg the user {}",username);
            if(username.contains("_")){
                String value []=username.split("_");
                return  value[0];
            }
            return  username;
//            return username;
        } else {
            //  String username = principal.toString();
            return null;
        }
    }

}
