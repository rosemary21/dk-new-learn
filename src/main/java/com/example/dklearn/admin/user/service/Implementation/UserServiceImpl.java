package com.example.dklearn.admin.user.service.Implementation;


import com.example.dklearn.admin.administration.model.Admin;
import com.example.dklearn.admin.administration.resp.AdminResponse;
import com.example.dklearn.admin.auth.CustomerUserDetails;
import com.example.dklearn.admin.user.dto.ChangePasswordDto;
import com.example.dklearn.admin.user.dto.DekeralutiveUserDto;
import com.example.dklearn.admin.user.model.DekeralutiveUser;
import com.example.dklearn.admin.user.repository.UserRepository;
import com.example.dklearn.admin.user.resp.UserResponse;
import com.example.dklearn.admin.user.service.UserService;
import com.example.dklearn.courses.dto.CoursesDto;
import com.example.dklearn.courses.model.Courses;
import com.example.dklearn.courses.services.CoursesService;
import com.example.dklearn.otp.dto.OtpDto;
import com.example.dklearn.otp.dto.OtpResponseData;
import com.example.dklearn.otp.service.OtpService;
import com.example.dklearn.response.ResponseDto;
import com.example.dklearn.util.PasswordUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CoursesService coursesService;

    @Autowired
    MessageSource messageSource;

    @Autowired
    OtpService otpService;

    @Override
    public UserResponse addUser(DekeralutiveUserDto dto) {
        UserResponse userResponse=new UserResponse();
        ResponseDto responseDto=new ResponseDto();
         DekeralutiveUser emailuser=  userRepository.findByEmail(dto.getEmail());
         if(emailuser!=null){
             responseDto.setCode(messageSource.getMessage("dk.error",null, Locale.ENGLISH));
             responseDto.setMessage(messageSource.getMessage("user.email.exist",null,Locale.ENGLISH));
             userResponse.setResponseDto(responseDto);
             return userResponse;
         }
         dto.setUserName(dto.getEmail());
        DekeralutiveUser oldUser= userRepository.findByUserName(dto.getUserName());
        if(oldUser!=null){
            responseDto.setCode(messageSource.getMessage("dk.error",null, Locale.ENGLISH));
            responseDto.setMessage(messageSource.getMessage("user.name.exist",null,Locale.ENGLISH));
            userResponse.setResponseDto(responseDto);
            return userResponse;
        }
        if(!(dto.getPassword().equalsIgnoreCase(dto.getConfirmPassword()))){
            responseDto.setCode(messageSource.getMessage("dk.error",null, Locale.ENGLISH));
            responseDto.setMessage(messageSource.getMessage("user.password.mismatch",null,Locale.ENGLISH));
            userResponse.setResponseDto(responseDto);
            return userResponse;
        }
        DekeralutiveUser dekeralutiveUser=modelMapper.map(dto, DekeralutiveUser.class);
        dekeralutiveUser.setPasswordhash(passwordEncoder.encode(dto.getPassword()));
        dekeralutiveUser.setUserName(dto.getEmail());
        userRepository.save(dekeralutiveUser);
        responseDto.setCode(messageSource.getMessage("dk.success",null, Locale.ENGLISH));
        responseDto.setMessage(messageSource.getMessage("user.add.success",null,Locale.ENGLISH));
        userResponse.setResponseDto(responseDto);
        return userResponse;
    }

    @Override
    public UserResponse updateUser(DekeralutiveUserDto dto) {
        UserResponse userResponse=new UserResponse();
        ResponseDto responseDto=new ResponseDto();
        DekeralutiveUser dekeralutiveUser= userRepository.findByUserName(dto.getUserName());
        Long id=dekeralutiveUser.getId();
        dto.setId(id);

        DekeralutiveUser dekeralutiveUser1=modelMapper.map(dto,DekeralutiveUser.class);
        dekeralutiveUser1.setVersion(dekeralutiveUser.getVersion());
        dekeralutiveUser1.setPasswordhash(dekeralutiveUser.getPasswordhash());
        userRepository.save(dekeralutiveUser1);
        responseDto.setCode(messageSource.getMessage("dk.success",null, Locale.ENGLISH));
        responseDto.setMessage(messageSource.getMessage("user.update.success",null,Locale.ENGLISH));
        userResponse.setResponseDto(responseDto);
        return userResponse;
    }


    @Override
    public UserResponse changePassword(ChangePasswordDto resetPasswordParam) {
        UserResponse lrData = new UserResponse();
        ResponseDto respDto = new ResponseDto();
        String value=getCurrentuser();
        DekeralutiveUser user=userRepository.findByUserName(value);
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
        userRepository.save(user);

        respDto.setCode("dk00");
        respDto.setMessage("Password Change Successful");
        lrData.setResponseDto(respDto);

        //  lrData = convertLoginUserToLoginResponseData(user);
        return lrData;
    }


    @Override
    public UserResponse resetPassword(ChangePasswordDto resetPasswordParam) {
        UserResponse lrData = new UserResponse();
        ResponseDto respDto = new ResponseDto();
        DekeralutiveUser user= userRepository.findByUserName(resetPasswordParam.getEmailAddress());

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
        userRepository.save(user);

        respDto.setCode("dk00");
        respDto.setMessage("Password Reset Successfully");
        lrData.setResponseDto(respDto);
        return lrData;
    }

    public String getCurrentuser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof CustomerUserDetails) {
            String username = ((CustomerUserDetails)principal).getUsername();
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
    public UserResponse getUserCourses() {
        ResponseDto responseDto=new ResponseDto();
        UserResponse userResponse=new UserResponse();
        String userName= getCurrentuser();
        List<CoursesDto> coursesDtos=new ArrayList<>();
        DekeralutiveUser user= userRepository.findByUserName(userName);
        DekeralutiveUserDto userDto=modelMapper.map(user,DekeralutiveUserDto.class);
        if(user.getCoursesList().size()>0){
            coursesDtos  = coursesService.getCourses(user.getCoursesList());

        }
        responseDto.setCode(messageSource.getMessage("dk.success",null, Locale.ENGLISH));
        responseDto.setMessage(messageSource.getMessage("user.all.courses",null,Locale.ENGLISH));
        userResponse.setResponseDto(responseDto);
        userResponse.setUserDto(userDto);
        userResponse.setCourses(coursesDtos);
        return userResponse;

    }

    @Override
    public UserResponse deleteUser(DekeralutiveUserDto dto) {
        UserResponse userResponse=new UserResponse();
        ResponseDto responseDto=new ResponseDto();
        DekeralutiveUser dekeralutiveUser= userRepository.findByUserName(dto.getUserName());
        if(dekeralutiveUser==null){
            responseDto.setCode(messageSource.getMessage("dk.error",null, Locale.ENGLISH));
            responseDto.setMessage(messageSource.getMessage("user.delete.no.exist",null,Locale.ENGLISH));
            userResponse.setResponseDto(responseDto);
            return userResponse;
        }
        dekeralutiveUser.setDelFlag("Y");
        userRepository.save(dekeralutiveUser);
        responseDto.setCode(messageSource.getMessage("dk.success",null, Locale.ENGLISH));
        responseDto.setMessage(messageSource.getMessage("user.delete.success",null,Locale.ENGLISH));
        userResponse.setResponseDto(responseDto);
        return userResponse;
    }

    @Override
    public UserResponse getAllUser() {
        UserResponse userResponse=new UserResponse();
        ResponseDto responseDto=new ResponseDto();
        List<DekeralutiveUser> dekeralutiveUser= userRepository.findAll();
        userResponse.setUserDtoList(convertDekeralutiveModelToDto(dekeralutiveUser));
        responseDto.setCode(messageSource.getMessage("dk.success",null, Locale.ENGLISH));
        responseDto.setMessage(messageSource.getMessage("user.all.success",null,Locale.ENGLISH));
        userResponse.setResponseDto(responseDto);
        return userResponse;
    }

    List<DekeralutiveUserDto> convertDekeralutiveModelToDto(List<DekeralutiveUser> dekeralutiveUsers){
        List<DekeralutiveUserDto> dekeralutiveUserDtos=new ArrayList<>();
        for(DekeralutiveUser dekeralutiveUse:dekeralutiveUsers){
         DekeralutiveUserDto dto=modelMapper.map(dekeralutiveUse,DekeralutiveUserDto.class);
            dekeralutiveUserDtos.add(dto);
        }
        return dekeralutiveUserDtos;
    }


//    @Override
//    public UserResponse getAllUsersByPage(PageDescription pageDescription) {
//        ResponseDto responseDto=new ResponseDto();
//        UserResponse userResponse=new UserResponse();
//        Page<DekeralutiveUser> dekeralutiveUsers=  userRepository.findAll(PageRequest.of(pageDescription.getPageNo(), pageDescription.getPageSize()));
//        userResponse.setUserDtoList(convertDekeralutiveModelToDto(dekeralutiveUsers.toList()));
//        responseDto.setCode(messageSource.getMessage("dk.success",null, Locale.ENGLISH));
//        responseDto.setMessage(messageSource.getMessage("user.all.success",null,Locale.ENGLISH));
//        userResponse.setResponseDto(responseDto);
//        return userResponse;
//    }
}
