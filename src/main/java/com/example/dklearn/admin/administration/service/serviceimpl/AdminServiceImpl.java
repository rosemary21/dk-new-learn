package com.example.dklearn.admin.administration.service.serviceimpl;


import com.example.dklearn.admin.administration.dto.AdminDto;
import com.example.dklearn.admin.administration.model.Admin;
import com.example.dklearn.admin.administration.repository.AdminRepository;
import com.example.dklearn.admin.administration.resp.AdminResponse;
import com.example.dklearn.admin.administration.service.AdminService;
import com.example.dklearn.admin.auth.StaffUserDetails;
import com.example.dklearn.admin.staff.dto.AccountDetailDto;
import com.example.dklearn.admin.staff.model.AccountDetails;
import com.example.dklearn.admin.staff.repository.AccountRepository;
import com.example.dklearn.admin.user.dto.ChangePasswordDto;
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
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminRepository adminRepository;
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
    public AdminResponse addAdmin(AdminDto adminDto) {
        AdminResponse userResponse=new AdminResponse();
        ResponseDto responseDto=new ResponseDto();
        adminDto.setUserName(adminDto.getEmail());
        Admin emailuser=  adminRepository.findByEmail(adminDto.getEmail());
        if(emailuser!=null){
            responseDto.setCode(messageSource.getMessage("dk.error",null, Locale.ENGLISH));
            responseDto.setMessage(messageSource.getMessage("user.email.exist",null,Locale.ENGLISH));
            userResponse.setResponseDto(responseDto);
            return userResponse;
        }
        Admin oldUser= adminRepository.findByUserName(adminDto.getUserName());
        if(oldUser!=null){
            responseDto.setCode(messageSource.getMessage("dk.error",null, Locale.ENGLISH));
            responseDto.setMessage(messageSource.getMessage("user.name.exist",null,Locale.ENGLISH));
            userResponse.setResponseDto(responseDto);
            return userResponse;
        }
        if(!(adminDto.getPassword().equalsIgnoreCase(adminDto.getConfirmPassword()))){
            responseDto.setCode(messageSource.getMessage("dk.error",null, Locale.ENGLISH));
            responseDto.setMessage(messageSource.getMessage("user.password.mismatch",null,Locale.ENGLISH));
            userResponse.setResponseDto(responseDto);
            return userResponse;
        }
        Admin dekeralutiveUser=modelMapper.map(adminDto, Admin.class);
        dekeralutiveUser.setPasswordhash(passwordEncoder.encode(adminDto.getPassword()));
        dekeralutiveUser.setUserName(adminDto.getEmail());
        adminRepository.save(dekeralutiveUser);
        responseDto.setCode(messageSource.getMessage("dk.success",null, Locale.ENGLISH));
        responseDto.setMessage(messageSource.getMessage("user.add.success",null,Locale.ENGLISH));
        userResponse.setResponseDto(responseDto);
        return userResponse;
    }

    @Override
    public AdminResponse updateAdmin(AdminDto adminDto) {
        AdminResponse userResponse=new AdminResponse();
        ResponseDto responseDto=new ResponseDto();
        String useName= getCurrentuser();
        Admin dekeralutiveUser= adminRepository.findByUserName(useName);
        Long id=dekeralutiveUser.getId();
        List<AccountDetails> accountDetailsList=  addAccountDetails(adminDto.getAccountDetailDtoList());
      //  dekeralutiveUser.setAccountDetailsList(accountDetailsList);
        adminRepository.save(dekeralutiveUser);
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
    public AdminResponse deleteAdmin(AdminDto adminDto) {
        AdminResponse userResponse=new AdminResponse();
        ResponseDto responseDto=new ResponseDto();
        Admin dekeralutiveUser= adminRepository.findByUserName(adminDto.getUserName());
        dekeralutiveUser.setDelFlag("Y");
        adminRepository.save(dekeralutiveUser);
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
    public AdminResponse allAdmin() {
        AdminResponse userResponse=new AdminResponse();
        ResponseDto responseDto=new ResponseDto();
        List<Admin> dekeralutiveUser= adminRepository.findAll();
        userResponse.setStaffDtoList(convertDekeralutiveModelToDto(dekeralutiveUser));
        responseDto.setCode(messageSource.getMessage("dk.success",null, Locale.ENGLISH));
        responseDto.setMessage(messageSource.getMessage("user.delete.success",null,Locale.ENGLISH));
        userResponse.setResponseDto(responseDto);
        return userResponse;
    }


    @Override
    public AdminResponse resetPassword(ChangePasswordDto resetPasswordParam) {
        AdminResponse lrData = new AdminResponse();
        ResponseDto respDto = new ResponseDto();
        Admin user= adminRepository.findByUserName(resetPasswordParam.getEmailAddress());
        if(user==null){
            respDto.setCode("dk96");
            respDto.setMessage("The Email Address Does Not Exist");
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
        adminRepository.save(user);
        respDto.setCode("dk00");
        respDto.setMessage("Password Reset Successfully");
        lrData.setResponseDto(respDto);
        return lrData;
    }

    @Override
    public AdminResponse changePassword(ChangePasswordDto resetPasswordParam){
        AdminResponse lrData = new AdminResponse();
        ResponseDto respDto = new ResponseDto();
        String value=getCurrentuser();
        Admin user=adminRepository.findByUserName(value);
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
        adminRepository.save(user);

        respDto.setCode("dk00");
        respDto.setMessage("Password Change Successful");
        lrData.setResponseDto(respDto);

        //  lrData = convertLoginUserToLoginResponseData(user);
        return lrData;
    }


    @Override
    public AdminResponse getAdmin() {
        AdminResponse userResponse=new AdminResponse();
        ResponseDto responseDto=new ResponseDto();

        String userName= getCurrentuser();
       log.info("Entering getting the username {}",userName);
       Admin staff=adminRepository.findByUserName(userName);
        AdminDto staffDto=modelMapper.map(staff,AdminDto.class);
      //  List<AccountDetailDto> accountDetailDtos=  getAccount(staff.getAccountDetailsList());
   //     staffDto.setAccountDetailDtoList(accountDetailDtos);
        userResponse.setStaffDto(staffDto);

        responseDto.setCode(messageSource.getMessage("dk.success",null, Locale.ENGLISH));
        responseDto.setMessage(messageSource.getMessage("admin.get.success",null,Locale.ENGLISH));
        userResponse.setResponseDto(responseDto);
        return userResponse;
    }

    List<AdminDto> convertDekeralutiveModelToDto(List<Admin> dekeralutiveUsers){
        List<AdminDto> dekeralutiveUserDtos=new ArrayList<>();
        for(Admin dekeralutiveUse:dekeralutiveUsers){
            AdminDto dto=modelMapper.map(dekeralutiveUse,AdminDto.class);
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
