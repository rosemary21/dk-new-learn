package com.example.dklearn.admin.staff.service.serviceimpl;


import com.example.dklearn.admin.auth.StaffUserDetails;
import com.example.dklearn.admin.staff.dto.AccountDetailDto;
import com.example.dklearn.admin.staff.dto.StaffDto;
import com.example.dklearn.admin.staff.model.AccountDetails;
import com.example.dklearn.admin.staff.model.Staff;
import com.example.dklearn.admin.staff.repository.AccountRepository;
import com.example.dklearn.admin.staff.repository.StaffRepository;
import com.example.dklearn.admin.staff.resp.StaffResponse;
import com.example.dklearn.admin.staff.service.StaffService;
import com.example.dklearn.admin.user.dto.ChangePasswordDto;
import com.example.dklearn.admin.user.model.DekeralutiveUser;
import com.example.dklearn.admin.user.resp.UserResponse;
import com.example.dklearn.otp.dto.OtpDto;
import com.example.dklearn.otp.dto.OtpResponseData;
import com.example.dklearn.otp.service.OtpService;
import com.example.dklearn.response.ResponseDto;
import com.example.dklearn.util.PasswordUtil;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
@Slf4j
public class StaffServiceImpl implements StaffService {

    @Autowired
    StaffRepository staffRepository;
    @Autowired
    MessageSource messageSource;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    OtpService otpService;
    @Autowired
    AccountRepository accountRepository;


    @Override
    public StaffResponse addStaff(StaffDto staffDto) {
        StaffResponse userResponse=new StaffResponse();
        ResponseDto responseDto=new ResponseDto();
        staffDto.setUserName(staffDto.getEmail());
        Staff emailuser=  staffRepository.findByEmail(staffDto.getEmail());
        if(emailuser!=null){
            responseDto.setCode(messageSource.getMessage("dk.error",null, Locale.ENGLISH));
            responseDto.setMessage(messageSource.getMessage("user.email.exist",null,Locale.ENGLISH));
            userResponse.setResponseDto(responseDto);
            return userResponse;
        }
        Staff oldUser= staffRepository.findByUserName(staffDto.getUserName());
        if(oldUser!=null){
            responseDto.setCode(messageSource.getMessage("dk.error",null, Locale.ENGLISH));
            responseDto.setMessage(messageSource.getMessage("user.name.exist",null,Locale.ENGLISH));
            userResponse.setResponseDto(responseDto);
            return userResponse;
        }
        if(!(staffDto.getPassword().equalsIgnoreCase(staffDto.getConfirmPassword()))){
            responseDto.setCode(messageSource.getMessage("dk.error",null, Locale.ENGLISH));
            responseDto.setMessage(messageSource.getMessage("user.password.mismatch",null,Locale.ENGLISH));
            userResponse.setResponseDto(responseDto);
            return userResponse;
        }
        Staff dekeralutiveUser=modelMapper.map(staffDto, Staff.class);
        dekeralutiveUser.setPasswordhash(passwordEncoder.encode(staffDto.getPassword()));
        dekeralutiveUser.setUserName(staffDto.getEmail());
        staffRepository.save(dekeralutiveUser);
        responseDto.setCode(messageSource.getMessage("dk.success",null, Locale.ENGLISH));
        responseDto.setMessage(messageSource.getMessage("user.add.success",null,Locale.ENGLISH));
        userResponse.setResponseDto(responseDto);
        return userResponse;
    }

    @Override
    public StaffResponse updateStaff(StaffDto staffDto) {
        StaffResponse userResponse=new StaffResponse();
        ResponseDto responseDto=new ResponseDto();
        String useName= getCurrentuser();
        Staff dekeralutiveUser= staffRepository.findByUserName(useName);
        dekeralutiveUser.setFullName(staffDto.getFullName());
        dekeralutiveUser.setGender(staffDto.getGender());
        dekeralutiveUser.setAge(staffDto.getAge());
        dekeralutiveUser.setCerticateUrl(staffDto.getCerticateUrl());
      //  Staff staff=modelMapper.map(staffDto,Staff.class);
        Long id=dekeralutiveUser.getId();
        List<AccountDetails> accountDetailsList=  addAccountDetails(staffDto.getAccountDetailDtoList());
        dekeralutiveUser.setAccountDetailsList(accountDetailsList);
        staffRepository.save(dekeralutiveUser);
        responseDto.setCode(messageSource.getMessage("dk.success",null, Locale.ENGLISH));
        responseDto.setMessage(messageSource.getMessage("user.update.success",null,Locale.ENGLISH));
        userResponse.setResponseDto(responseDto);
        return userResponse;
    }


    public String getCurrentuser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof StaffUserDetails) {
            String username = ((StaffUserDetails)principal).getUsername();
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

    @Override
    public StaffResponse deleteStaff(StaffDto staffDto) {
        StaffResponse userResponse=new StaffResponse();
        ResponseDto responseDto=new ResponseDto();
        Staff dekeralutiveUser= staffRepository.findByUserName(staffDto.getUserName());
        dekeralutiveUser.setDelFlag("Y");
        staffRepository.save(dekeralutiveUser);
        responseDto.setCode(messageSource.getMessage("dk.success",null, Locale.ENGLISH));
        responseDto.setMessage(messageSource.getMessage("user.delete.success",null,Locale.ENGLISH));
        userResponse.setResponseDto(responseDto);
        return userResponse;
    }

    private List<AccountDetails> addAccountDetails(List<AccountDetailDto> accountDetailDtos){
        List<AccountDetails> accountDetailsList=new ArrayList<>();
        for(AccountDetailDto accountDetailDto:accountDetailDtos){
           AccountDetails accountDetails= modelMapper.map(accountDetailDto,AccountDetails.class);
            accountDetails= accountRepository.save(accountDetails);
           accountDetailsList.add(accountDetails);
        }
        return accountDetailsList;
    }

    @Override
    public StaffResponse allStaff() {
        StaffResponse userResponse=new StaffResponse();
        ResponseDto responseDto=new ResponseDto();
        List<Staff> dekeralutiveUser= staffRepository.findAll();
        userResponse.setStaffDtoList(convertDekeralutiveModelToDto(dekeralutiveUser));
        responseDto.setCode(messageSource.getMessage("dk.success",null, Locale.ENGLISH));
        responseDto.setMessage(messageSource.getMessage("user.delete.success",null,Locale.ENGLISH));
        userResponse.setResponseDto(responseDto);
        return userResponse;
    }


    @Override
    public StaffResponse resetPassword(ChangePasswordDto resetPasswordParam) {
        StaffResponse lrData = new StaffResponse();
        ResponseDto respDto = new ResponseDto();
        Staff user= staffRepository.findByUserName(resetPasswordParam.getEmailAddress());
        if(user==null){
            respDto.setCode("dk96");
            respDto.setMessage("The Email Address Does Not Exist");
            lrData.setResponseDto(respDto);
            return lrData;
        }
        OtpDto otpDto=new OtpDto();
        otpDto.setOtpId(resetPasswordParam.getEmailAddress());
        otpDto.setOtp(resetPasswordParam.getOtp());
        OtpResponseData otpResponseData= otpService.validateOtp(otpDto);
        if(otpResponseData.getResp().getCode().equals("dk96")){
            respDto.setCode("dk96");
            respDto.setMessage(otpResponseData.getResp().getMessage());
            lrData.setResponseDto(respDto);
            return lrData;
        }
        if(!PasswordUtil.checkPassword(resetPasswordParam.getNewPassword())){
            respDto.setCode("dk96");
            respDto.setMessage("Password must begin with uppercase, must contain symbol, and must contain at least one number");
            lrData.setResponseDto(respDto);
            return lrData;
        }
        user.setPasswordhash(passwordEncoder.encode(resetPasswordParam.getNewPassword()));
        user.setPassword(passwordEncoder.encode(resetPasswordParam.getNewPassword()));
        staffRepository.save(user);
        respDto.setCode("dk00");
        respDto.setMessage("Password Reset Successfully");
        lrData.setResponseDto(respDto);
        return lrData;
    }

    @Override
    public StaffResponse changePassword(ChangePasswordDto resetPasswordParam){
        StaffResponse lrData = new StaffResponse();
        ResponseDto respDto = new ResponseDto();
        String value=getCurrentuser();
        Staff user=staffRepository.findByUserName(value);
        if(!resetPasswordParam.getNewPassword().equals(resetPasswordParam.getConfirmPassword())){
            respDto.setCode("dk96");
            respDto.setMessage("Passwords entered do not match. Try again.");
            lrData.setResponseDto(respDto);

            return lrData;
        }
        if(!PasswordUtil.checkPassword(resetPasswordParam.getNewPassword())){
            respDto.setCode("dk96");
            respDto.setMessage("Password must begin with uppercase, must contain symbol, and must contain at least one number");
            lrData.setResponseDto(respDto);

            return lrData;
        }
        if(null == user){
            respDto.setCode("dk96");
            respDto.setMessage("Invalid user");
            lrData.setResponseDto(respDto);

            return lrData;

        }
        if(!(passwordEncoder.matches(resetPasswordParam.getOldPassword(),user.getPasswordhash()))){
            respDto.setCode("dk96");
            respDto.setMessage("Old Password is Wrong");
            lrData.setResponseDto(respDto);

            return lrData;
        }
        if(passwordEncoder.matches(resetPasswordParam.getNewPassword(),user.getPasswordhash())){
            respDto.setCode("dk96");
            respDto.setMessage("Oops! Password did not change. Please use a new password");
            lrData.setResponseDto(respDto);

            return lrData;
        }
        user.setPasswordhash(passwordEncoder.encode(resetPasswordParam.getNewPassword()));
        staffRepository.save(user);

        respDto.setCode("dk00");
        respDto.setMessage("Password Change Successful");
        lrData.setResponseDto(respDto);

        //  lrData = convertLoginUserToLoginResponseData(user);
        return lrData;
    }


    @Override
    public StaffResponse getStaff() {
        StaffResponse userResponse=new StaffResponse();
        ResponseDto responseDto=new ResponseDto();

        String userName= getCurrentuser();
       log.info("Entering getting the username {}",userName);
       Staff staff=staffRepository.findByUserName(userName);
        StaffDto staffDto=modelMapper.map(staff,StaffDto.class);
        List<AccountDetailDto> accountDetailDtos=  getAccount(staff.getAccountDetailsList());
        staffDto.setAccountDetailDtoList(accountDetailDtos);
        userResponse.setStaffDto(staffDto);

        responseDto.setCode(messageSource.getMessage("dk.success",null, Locale.ENGLISH));
        responseDto.setMessage(messageSource.getMessage("user.get.success",null,Locale.ENGLISH));
        userResponse.setResponseDto(responseDto);
        return userResponse;
    }

    List<StaffDto> convertDekeralutiveModelToDto(List<Staff> dekeralutiveUsers){
        List<StaffDto> dekeralutiveUserDtos=new ArrayList<>();
        for(Staff dekeralutiveUse:dekeralutiveUsers){
            StaffDto dto=modelMapper.map(dekeralutiveUse,StaffDto.class);
            dekeralutiveUserDtos.add(dto);
        }
        return dekeralutiveUserDtos;
    }

    private List<AccountDetailDto> getAccount(List<AccountDetails> accountDetails){
        List<AccountDetailDto> accountDetailDtos=new ArrayList<>();
        for(AccountDetails accountDetails1:accountDetails){
           AccountDetailDto accountDetailDto= modelMapper.map(accountDetails1,AccountDetailDto.class);
           accountDetailDtos.add(accountDetailDto);
        }
        return accountDetailDtos;
    }
}
